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
public class UpdateDelivProfileValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);	
    }

    @Override
    public void validate(Object o, Errors errors) {
       User registerFormUser = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNum", "error.phoneNum", "Phone number is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.address", "Address is Empty");
        UserDao userDao=new UserDao();
        if (registerFormUser.getPassword().length()<7 || registerFormUser.getPassword().length()>32) {
              errors.reject("error.password", "Password should not contain less than 7 characters and more than 32 characters!!");
       }
        if (registerFormUser.getPhoneNum().length()!=10 || Pattern.matches("^0-9*$",registerFormUser.getPhoneNum())) {
              errors.reject("error.phoneNum", "Phone Number is invalid!!");
       }

    }
    
}
