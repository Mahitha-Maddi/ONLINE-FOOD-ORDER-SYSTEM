/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.dao.MenuItemDao;
import com.mycompany.pojo.MenuItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mahit
 */
@Component
public class AddMenuItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return MenuItem.class.isAssignableFrom(type);	
    }

    @Override
    public void validate(Object o, Errors errors) {
        MenuItem menuItem = (MenuItem) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cuisine", "error.cuisine", "Cuisine is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "error.itemName", "Item Name is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemPrice", "error.itemPrice", "Item Price is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "error.category", "Category is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description is Empty");
           MenuItemDao menuItemDao=new MenuItemDao();
      if (menuItemDao.getItem(menuItem.getItemName())!=null) {
              errors.reject("error.itemName", "This item already exists!!Please delete the item or use edit option above!!");
       }
      else if (!(menuItem.getItemName().matches("^[a-zA-Z\\s]+$"))) {
              errors.reject("error.itemName", "Item Name is invalid!!");
       }
    }
    
}
