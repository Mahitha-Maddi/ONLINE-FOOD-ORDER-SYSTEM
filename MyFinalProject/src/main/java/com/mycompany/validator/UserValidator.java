/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.dao.UserDao;
import com.mycompany.pojo.User;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mahit
 */

@Component
public class UserValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);	
    }

    @Override
    public void validate(Object o, Errors errors) {
        User registerFormUser = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName", "First name is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.lastName", "Last name is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.emailId", "Email is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNum", "error.phoneNum", "Phone number is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.address", "Address is Empty");
        UserDao userDao=new UserDao();
      if (userDao.get(registerFormUser.getEmailId())!=null) {
              errors.reject("error.emailId", "User already exists! Please enter different email address!");
       }
       if (userDao.getPhoneNum(registerFormUser.getPhoneNum())!=null) {
              errors.reject("error.phoneNum", "Phone Number is already registered!!");
       }
       
        if (userDao.getPassword(registerFormUser.getPassword())!=null) {
              errors.reject("error.password", "Password is already in use!! Please choose different password!!");
       }
        //
        else 
        if (!(registerFormUser.getFirstName().matches("^[a-zA-Z\\s]+$"))) {
              errors.reject("error.firstName", "First Name is invalid!!");
       }
         else 
        if (!(registerFormUser.getLastName().matches("^[a-zA-Z\\s]+$"))) {
              errors.reject("error.lastName", "Last Name is invalid!!");
       }
        else 
        if (!isEmailValid(registerFormUser.getEmailId())) {
              errors.reject("error.emailId", "Email is invalid!!");
       }
        
        else 
        if (registerFormUser.getPassword().length()<7 || registerFormUser.getPassword().length()>32) {
              errors.reject("error.password", "Password should not contain less than 7 characters and more than 32 characters!!");
       }
        
        else 
        if (registerFormUser.getPhoneNum().length()!=10 || Pattern.matches("^0-9*$",registerFormUser.getPhoneNum())) {
              errors.reject("error.phoneNum", "Phone Number is invalid!!");
       }

    }
    public static boolean isEmailValid(String email) {
    final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
    return EMAIL_REGEX.matcher(email).matches();
}
}
