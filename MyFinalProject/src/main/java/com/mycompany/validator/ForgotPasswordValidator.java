/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.dao.UserDao;
import com.mycompany.pojo.ForgotPassword;
import com.mycompany.pojo.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mahit
 */
public class ForgotPasswordValidator implements Validator  {

    @Override
    public boolean supports(Class<?> type) {
       return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ForgotPassword registerFormUser = (ForgotPassword) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.emailId", "Email Id is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpassword", "error.cpassword", "Confirm password is Empty");
        UserDao userDao=new UserDao();
        if (userDao.get(registerFormUser.getEmailId())==null) {
              errors.reject("error.emailId", "User doesn't exist! Please enter valid email address!");
       }
        else
        if (registerFormUser.getPassword().length()<7 || registerFormUser.getPassword().length()>32) {
              errors.reject("error.password", "Password should not contain more than 7 characters and less than 32 characters!!");
       }
        else if (!registerFormUser.getPassword().equals(registerFormUser.getCpassword())) {
              errors.reject("error.cpassword", "Passwords are not same!!");
       }
        
        
        
        
    }
    
}
