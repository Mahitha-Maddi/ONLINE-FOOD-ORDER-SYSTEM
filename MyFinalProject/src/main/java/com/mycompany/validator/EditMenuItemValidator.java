/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.dao.MenuItemDao;
import com.mycompany.pojo.MenuItem;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mahit
 */
public class EditMenuItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return MenuItem.class.isAssignableFrom(type);	
    }

    @Override
    public void validate(Object o, Errors errors) {
        MenuItem menuItem = (MenuItem) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemPrice", "error.itemPrice", "Item Price is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description is Empty");
        MenuItemDao menuItemDao=new MenuItemDao();
      /**if (menuItemDao.getItem(menuItem.getItemName())!=null) {
              errors.reject("error.emailId", "This item already exists!!Please delete the item or use edit option above!!");
       }
      **/
    }
    
}
