/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDao;
import com.mycompany.pojo.User;
import com.mycompany.validator.UpdateDelivProfileValidator;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author mahit
 */
@Controller
public class DeliveryManUpdateProfileController {
    @Autowired
    UpdateDelivProfileValidator updateDelivProfileValidator;
    
    @GetMapping(value = "updatedeliv.htm")
    public String doGet(ModelMap model, User user3,HttpSession session) {
        User user1=(User)session.getAttribute("user");
        user3.setAddress(user1.getAddress());
        user3.setPassword(user1.getPassword());
        user3.setPhoneNum(user1.getPhoneNum());
        model.addAttribute("user3",user3);
        return "updatedeliv";
    }

    @PostMapping(value = "updatedeliv.htm")
    public String doPost(@ModelAttribute("user3") User user3, BindingResult result, SessionStatus status,HttpSession session) {
        updateDelivProfileValidator.validate(user3, result);
        if (result.hasErrors()) {
            return "updatedeliv";
        } else {
            UserDao userDao = new UserDao();
            User user1=(User)session.getAttribute("user");
            int result1 = userDao.update(user1.getUserId(),user3);

            if (result1 != 1) {
                return "updatedeliv";
            }
            status.setComplete();
            return "loginSuccessDelivery";
        }
    }
}
