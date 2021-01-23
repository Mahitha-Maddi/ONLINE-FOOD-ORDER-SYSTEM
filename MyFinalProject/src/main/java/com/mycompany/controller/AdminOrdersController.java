/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.OrderDao;
import com.mycompany.dao.UserDao;
import com.mycompany.pojo.Order;
import com.mycompany.pojo.User;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mahit
 */
@Controller
public class AdminOrdersController {

    @GetMapping(value = "adminOrders.htm")
    public String doGet(ModelMap model, OrderDao orderDao,UserDao userDao) {
        List<Order> placedOrders = orderDao.getOrders("placed");
        List<Order> acceptedOrders= orderDao.getOrders("accepted");
        List<User> deliveryMen=userDao.getDeliveryMen();
        if (placedOrders == null) {
            model.addAttribute("error", "No orders placed!!");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("placedOrders", placedOrders);
        model.addAttribute("acceptedOrders", acceptedOrders);
        model.addAttribute("deliveryMen",deliveryMen);
        return "adminOrders";
    }

    @PostMapping(value = "adminOrders.htm")
    public String doPost(ModelMap model,OrderDao orderDao,UserDao userDao, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        String optionValue = (String) request.getParameter("option");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
      
        if (optionValue.equals("accept")) {
            try {
                orderDao.update(orderId,"accepted");
            } catch (ParseException ex) {
                Logger.getLogger(AdminOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else 
            if (optionValue.equals("reject")){
            try {
                orderDao.update(orderId,"rejected");
            } catch (ParseException ex) {
                Logger.getLogger(AdminOrdersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            int deliveryManId = Integer.parseInt(request.getParameter("deliveryManName"));
           
            orderDao.update(orderId,deliveryManId,"assigned");
        }
        
        List<Order> placedOrders = orderDao.getOrders("placed");
        List<Order> acceptedOrders= orderDao.getOrders("accepted");
        List<User> deliveryMen=userDao.getDeliveryMen();
        if (placedOrders == null) {
            model.addAttribute("error", "No orders placed!!");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("placedOrders", placedOrders);
        model.addAttribute("acceptedOrders", acceptedOrders);
        model.addAttribute("deliveryMen",deliveryMen);

        return "adminOrders";
    }

  
}
