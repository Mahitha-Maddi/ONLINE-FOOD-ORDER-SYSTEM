/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.OrderDao;
import com.mycompany.dao.UserDao;
import com.mycompany.pojo.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mahit
 */
@Controller
public class AdminDeliveryManController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping(value = "approveDelivRequests.htm")
    public String doGet(ModelMap model, UserDao userDao) {
        List<User> pendingRequests = userDao.getUsers("pending");
        List<User> acceptedRequests = userDao.getUsers("deliveryMan");
        if (pendingRequests == null) {
            model.addAttribute("error", "No pending requests!!");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("pendingRequests", pendingRequests);
        model.addAttribute("acceptedRequests", acceptedRequests);
        return "approveDelivRequests";
    }

    @PostMapping(value = "approveDelivRequests.htm")
    public String doPost(ModelMap model, OrderDao orderDao, UserDao userDao, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        String optionValue = (String) request.getParameter("option");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String emailId = (String) request.getParameter("emailId");
        //deliveryManName

        SimpleMailMessage email = new SimpleMailMessage();
        if (optionValue.equals("accept")) {
            userDao.update(userId, "deliveryMan");
            // creates a simple e-mail object

            email.setTo(emailId);
            String text = "Hi" + ",\n" + "\n"
                    + "Congratulations!!\n"
                    + "\n"
                    + "Your signup request for delivery man post has been approved!!\n"
                    + "\n"
                    + "We look forward to being part of your next memorable experience.\n"
                    + "\n"
                    + "Spicy Food\n" + "Management team";
            email.setSubject("Get ready to deliver!! Thank you for registering to Spicy Food!! ");
            email.setText(text);

            // sends the e-mail
            mailSender.send(email);

        } else if (optionValue.equals("reject")) {

            userDao.update(userId, "undefined");
            // creates a simple e-mail object

            email.setTo(emailId);
            String text = "Hi" + ",\n" + "\n"
                    + "We are sorry to inform you.!!\n"
                    + "\n"
                    + "Your signup request for delivery man post has been rejected.\n"
                    + "\n"
                    + "We look forward to connecting with you in the future.\n"
                    + "\n"
                    + "Spicy Food\n" + "Management team";
            email.setSubject("Thank you for registering to Spicy Food!! ");
            email.setText(text);

            // sends the e-mail
            mailSender.send(email);
        }

        List<User> pendingRequests = userDao.getUsers("pending");
        List<User> acceptedRequests = userDao.getUsers("deliveryMan");
        if (pendingRequests == null) {
            model.addAttribute("error", "No pending requests!!");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("pendingRequests", pendingRequests);
        model.addAttribute("acceptedRequests", acceptedRequests);
        return "approveDelivRequests";

    }

    @GetMapping(value = "deleteAccounts.htm")
    public String doGetdeleteAccounts(ModelMap model, OrderDao orderDao, UserDao userDao) {
        List<User> deliveryMen = userDao.getUsers("deliveryMan");
        model.addAttribute("deliveryMen", deliveryMen);
        return "deleteAccounts";
    }

    @PostMapping(value = "deleteAccounts.htm")
    public String doPostdeleteAccounts(ModelMap model, OrderDao orderDao, UserDao userDao, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        int result1 = userDao.delete(userId);
        List<User> deliveryMen = userDao.getUsers("deliveryMan");
        model.addAttribute("deliveryMen", deliveryMen);
        return "deleteAccounts";
    }

}
