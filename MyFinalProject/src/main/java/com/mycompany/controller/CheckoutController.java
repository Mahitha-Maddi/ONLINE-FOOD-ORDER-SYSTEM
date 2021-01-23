/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.View.MyPdfInvoiceView;
import com.mycompany.dao.OrderDao;
import com.mycompany.dao.OrderItemDao;
import com.mycompany.dao.UserDao;
import com.mycompany.pojo.DebitCard;
import com.mycompany.pojo.Order;
import com.mycompany.pojo.OrderItem;
import com.mycompany.pojo.User;
import com.mycompany.validator.CardValidator;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.View;

/**
 *
 * @author mahit
 */
@Controller
public class CheckoutController {
    
    @Autowired
    CardValidator cardValidator;

    @GetMapping(value = "checkout.htm")
    public String doGetCart(HttpServletRequest request, HttpSession session, DebitCard debitCard,UserDao userDao) {
        ArrayList<OrderItem> list = (ArrayList<OrderItem>) session.getAttribute("items");
        
        if (list == null || list.size()==0) {
            request.setAttribute("error", "Cart is empty!");
            return "viewCart";
        }
        
        else{
            User user=(User)session.getAttribute("user");
           String customerAddress = userDao.getCustomerAddress(user.getEmailId());
           if(customerAddress!=null){
               request.setAttribute("customerAddress", customerAddress);
           }
           else{
                request.setAttribute("customerAddress", "no saved address! enter addresss!!");
           }
        return "checkout";
        }
    }
    
    @PostMapping(value = "checkout.htm")
    public String doPostCart(@ModelAttribute("debitCard") DebitCard debitCard, BindingResult result, SessionStatus status,
             Order order, HttpSession session, HttpServletRequest request, OrderDao orderDao, OrderItemDao orderItemDao) throws IOException, ParseException {
        cardValidator.validate(debitCard, result);
        if (result.hasErrors()) {
            return "checkout";
        }
        User user = (User) session.getAttribute("user");
         double total=0;
        ArrayList<OrderItem> orderItemsList = (ArrayList<OrderItem>) session.getAttribute("items");
          if (orderItemsList != null) {
            
           
            for (OrderItem orderItem : orderItemsList) {
                total=total+orderItem.getTotalPrice();
               
            }
          }
        UserDao userDao=new UserDao();
        User user1 = (User)userDao.get(user.getEmailId());
        int customerId=user1.getUserId();
        String orderAddress;
        String opt=(String)request.getParameter("radioBtn");
        if(opt.equals("other")){
            orderAddress=(String)request.getParameter("address1");
         }
        else{
            orderAddress=(String)request.getParameter("address");
        }
        
        order.setCustomerId(customerId);
        order.setOrderAddress(orderAddress);
        order.setOrderStatus("placed");
        order.setOrderTotalPrice(total);
        String pattern = "YYYY-MM-DD hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        Date date1=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse(date);
        order.setOrderDate(date1); /////////////////////////////////////////////////////////////////////////////////
         System.out.println("this is my order date" +date);//date
        System.out.println("this is my order date" +date1);//date
        orderDao.add(order);
        int orderId=order.getOrderId();
        int y=0;
        if (orderItemsList != null) {
            
            
            for (OrderItem orderItem : orderItemsList) {
                orderItem.setOrderId(orderId);
                orderItemDao.add(orderItem);
               
            }
            ArrayList<OrderItem> orderItemsList1=orderItemsList;
            
            session.setAttribute("orderItemsList1", orderItemsList1);
            session.removeAttribute("items");
            
             status.setComplete();
            return "checkoutSuccess";
        }
        //create order record with this customer
        //create items and add order id to their columns
        return "checkout";
    }
    
    @RequestMapping(value = "/invoice.pdf", method = RequestMethod.GET)
    public View doGet(ModelMap modelMap, HttpSession session) {
        
       MyPdfInvoiceView myPdfInvoiceView = new MyPdfInvoiceView();
       
       return myPdfInvoiceView;
       
    }
    
    
}
