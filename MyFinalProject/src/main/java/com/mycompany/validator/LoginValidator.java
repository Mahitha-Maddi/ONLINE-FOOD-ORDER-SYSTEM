/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.dao.UserDao;
import com.mycompany.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mahit
 */

@Component
public class LoginValidator implements Validator {
   @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);	
    }

    @Override
    public void validate(Object o, Errors errors) {
        User registerFormUser = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.emailId", "Email is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is Empty");
        UserDao userDao=new UserDao();
      if (userDao.get(registerFormUser.getEmailId())==null) {
              errors.reject("error.emailId", "User doesn't exist! Please enter valid email address!");
       }
      
        if (userDao.getPassword(registerFormUser.getPassword())==null) {
              errors.reject("error.password", "Password is wrong!! Please enter correct password!!");
              return;
       }
       
        
        if (userDao.get(registerFormUser.getEmailId())!=null) {
            User user=userDao.get(registerFormUser.getEmailId());
            String password=userDao.getPassword(registerFormUser.getPassword()).getPassword();
            if(!user.getPassword().equals(password)){
              errors.reject("error.password", "Please enter valid password!!");
            }
       }
        
         
        else if (userDao.getRole(registerFormUser.getEmailId()).equals("pending")) {
              errors.reject("error.emailId", "Your sign up request is not yet been approved!!");
              
       }
        else if (userDao.getRole(registerFormUser.getEmailId()).equals("undefined")) {
              errors.reject("error.emailId", "Your sign up request has been rejected!!");
             
       }

    }
}