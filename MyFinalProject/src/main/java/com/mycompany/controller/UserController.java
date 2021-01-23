/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDao;
import com.mycompany.pojo.ForgotPassword;
import com.mycompany.pojo.User;
import com.mycompany.validator.ForgotPasswordValidator;
import com.mycompany.validator.LoginValidator;
import com.mycompany.validator.UserValidator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author mahit
 */
@Controller
public class UserController extends HttpServlet {

    @Autowired
    ForgotPasswordValidator forgotPasswordValidator;
    
    @Autowired
    UserValidator validator;

    @Autowired
    LoginValidator loginValidator;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping(value = "signup.htm")
    public String doGet(ModelMap model, User user) {
        return "signup";
    }

    @PostMapping(value = "signup.htm")
    public String doPost(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) {
        validator.validate(user, result);
        if (result.hasErrors()) {
            return "signup";
        } else {
            user.setRole("customer");
            UserDao userDao = new UserDao();
            int result1 = userDao.add(user);

            if (result1 != 1) {
                return "signup";
            }
            status.setComplete();
            // creates a simple e-mail object
            try{
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmailId());
            String text = "Dear "+user.getFirstName()+" "+user.getLastName()+",\n" + "\n"
                    + "Thank you for registering with us.\n"
                    + "\n"
                    + "Use the promo code INS0412RL to enjoy happy first meal from us!!\n"
                    + "\n"
                    + "We look forward to being part of your next memorable experience.\n"
                    + "\n"
                    + "Spicy Food";
            email.setSubject(" Thank you for registering to Spicy Food!! Your promo code inside!");
            email.setText(text);

            // sends the e-mail
            mailSender.send(email);
            }
            catch(org.springframework.mail.MailAuthenticationException e){
                
            }
            
            return "signupSuccess";
        }
    }
    
    @GetMapping(value = "updatepassword.htm")
    public String doGetUpdatepassword(ModelMap model, ForgotPassword forgotPassword) {
        return "updatepassword";
    }

    @PostMapping(value = "updatepassword.htm")
    public String doPostUpdatepassword(@ModelAttribute("forgotPassword")ForgotPassword forgotPassword, BindingResult result, SessionStatus status) {
        forgotPasswordValidator.validate(forgotPassword, result);
        if (result.hasErrors()) {
            return "updatepassword";
        } else {
            UserDao userDao = new UserDao();
            int result1 = userDao.updatePassword(forgotPassword.getEmailId(),forgotPassword.getPassword());

            if (result1 != 1) {
                return "updatepassword";
            }
            status.setComplete();
          return "updateSuccess";
        }
    }

    @GetMapping(value = "signupDelivery.htm")
    public String doGetsignupDelivery(ModelMap model, User user) {
        return "signupDelivery";
    }

    @PostMapping(value = "signupDelivery.htm")
    public String doPostsignupDelivery(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) {
        validator.validate(user, result);
        if (result.hasErrors()) {
            return "signupDelivery";
        } else {
            user.setRole("pending");
            UserDao userDao = new UserDao();
            int result1 = userDao.add(user);

            if (result1 != 1) {
                return "signupDelivery";
            }
            status.setComplete();
            return "signupDeliverySuccess";
        }
    }

    @GetMapping(value = "login.htm")
    public String doGetLogin(ModelMap model, User user, UserDao userDao, HttpServletRequest request, HttpSession session) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    String cookieValue = cookie.getValue();
                    String[] array = cookieValue.split("%20");
                    String emailId = array[0];
                    String password = array[1];
                    //populateLoggedInUserPage(name, response);
                    User user1 = userDao.get(emailId);
                    session.setAttribute("user", user1);
                    if (user1.getRole().equals("customer")) {
                        return "loginSuccess";
                    } else if (user1.getRole().equals("admin")) {
                        return "loginSuccessAdmin";
                    } else if (user1.getRole().equals("deliveryMan")) {
                        return "loginSuccessDelivery";
                    }
                }
            }
        }

        return "login";
    }

    @PostMapping(value = "login.htm")
    public String doPostLogin(@ModelAttribute("user") User user,
            BindingResult result, SessionStatus status, HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        loginValidator.validate(user, result);
        if (result.hasErrors()) {
            return "login";
        }
        String remember = request.getParameter("remember");
        String userCookie = user.getEmailId() + "%20" + user.getPassword();

        if (remember != null) {
            Cookie userCookie1 = new Cookie("userCookie", userCookie);
            userCookie1.setMaxAge(365 * 24 * 60 * 60);
            response.addCookie(userCookie1);
        }
        UserDao userDao = new UserDao();
        User user1 = userDao.get(user.getEmailId());
        session.setAttribute("user", user1);
        status.setComplete();
        if (user1.getRole().equals("customer")) {
            return "loginSuccess";
        } else if (user1.getRole().equals("admin")) {
            return "loginSuccessAdmin";
        } else {
            return "loginSuccessDelivery";
        }
    }

    @RequestMapping(value = "logout.htm", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpSession session) {
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }

        return "redirect";
    }

}



// return new Gson().toJson(res);
