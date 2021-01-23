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
public class DeliveryManOrdersController {
    
    @GetMapping(value = "ordersassigneddev.htm")
    public String doGet(ModelMap model,HttpSession session,OrderDao orderDao,UserDao userDao) {
        User deliveryMan=(User)session.getAttribute("user");
        int deliveryManId=deliveryMan.getUserId();
        List<Order> assignedOrders = orderDao.getDeliveryManOrders(deliveryManId,"assigned");
        if (assignedOrders == null) {
            model.addAttribute("error", "No orders assigned!!");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("assignedOrders", assignedOrders);
        return "ordersassigneddev";
    }

    @PostMapping(value = "ordersassigneddev.htm")
    public String doPost(ModelMap model,OrderDao orderDao,UserDao userDao, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        try {
            orderDao.update(orderId,"delivered");
        } catch (ParseException ex) {
            Logger.getLogger(DeliveryManOrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        User deliveryMan=(User)session.getAttribute("user");
        int deliveryManId=deliveryMan.getUserId();
        List<Order> assignedOrders = orderDao.getDeliveryManOrders(deliveryManId,"assigned");
        if (assignedOrders == null) {
            model.addAttribute("error", "No orders assigned!!");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("assignedOrders", assignedOrders);

        return "ordersassigneddev";
    }

    @GetMapping(value = "ordersdelivered.htm")
    public String doGet1(ModelMap model,HttpSession session,OrderDao orderDao,UserDao userDao) {
        User deliveryMan=(User)session.getAttribute("user");
        int deliveryManId=deliveryMan.getUserId();
        List<Order> deliveredOrders = orderDao.getDeliveryManOrders(deliveryManId,"delivered");
        if (deliveredOrders == null) {
            model.addAttribute("error", "No orders delivered!!");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("deliveredOrders", deliveredOrders);
        return "ordersdelivered";
    }

    
}
