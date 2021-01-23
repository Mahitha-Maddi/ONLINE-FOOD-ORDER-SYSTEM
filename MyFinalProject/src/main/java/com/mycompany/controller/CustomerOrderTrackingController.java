/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.OrderDao;
import com.mycompany.pojo.Order;
import com.mycompany.pojo.User;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mahit
 */
@Controller
public class CustomerOrderTrackingController extends HttpServlet {

    @GetMapping(value = "trackOrders.htm")
    public String doGet(OrderDao orderDao, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        List<Order> customerOrders = orderDao.getcustomerOrders(user.getUserId());
        if (customerOrders == null) {
            request.setAttribute("error", "You have no current orders placed!!");
        } else {
            request.setAttribute("customerOrders", customerOrders);
        }
        return "trackOrders";
    }
    
    @PostMapping(value = "trackOrders.htm")
    public String doPost(OrderDao orderDao, HttpSession session, HttpServletRequest request) {
        String orderId1=(String)request.getParameter("orderId");
        if(orderId1!=null){
            int orderId=Integer.parseInt(orderId1);
            try {
                orderDao.update(orderId,"canceled");
            } catch (ParseException ex) {
                Logger.getLogger(CustomerOrderTrackingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        User user = (User) session.getAttribute("user");
        List<Order> customerOrders = orderDao.getcustomerOrders(user.getUserId());
        if (customerOrders == null) {
            request.setAttribute("error", "You have no current orders placed!!");
        } else {
            request.setAttribute("customerOrders", customerOrders);
        }
        return "trackOrders";
    }
    
    @GetMapping(value = "rejectedOrders.htm")
    public String doGet1(OrderDao orderDao, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        List<Order> customerRejectedOrders = orderDao.getcustomerRejectedOrders(user.getUserId());
        if (customerRejectedOrders == null) {
            request.setAttribute("error", "You have no rejected/canceled orders placed!!");
        } else {
            request.setAttribute("customerRejectedOrders", customerRejectedOrders);
        }
        return "rejectedOrdersCustomers";
    }
    
    @GetMapping(value = "provideFeedback.htm")
    public String doGet2(OrderDao orderDao, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        List<Order> deliveredCustomerOrders = orderDao.getdeliveredCustomerOrders(user.getUserId());
        if (deliveredCustomerOrders == null) {
            request.setAttribute("error", "You have no orders delivered!!");
        } else {
            request.setAttribute("deliveredCustomerOrders", deliveredCustomerOrders);
        }
        return "provideFeedbackCustomers";
    }
    
    @PostMapping(value = "provideFeedback.htm")
    public String doPost2(OrderDao orderDao, HttpSession session, HttpServletRequest request) {
        String orderId1=(String)request.getParameter("orderId");
        String feedback=(String)request.getParameter("feedback");
        if(orderId1!=null){
            int orderId=Integer.parseInt(orderId1);
            orderDao.updateFeedback(orderId,feedback);
            
        }
        User user = (User) session.getAttribute("user");
        List<Order> deliveredCustomerOrders = orderDao.getdeliveredCustomerOrders(user.getUserId());
        if (deliveredCustomerOrders == null) {
            request.setAttribute("error", "You have no orders delivered!!");
        } else {
            request.setAttribute("deliveredCustomerOrders", deliveredCustomerOrders);
        }
        return "provideFeedbackCustomers";
    }
    

}
