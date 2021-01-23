/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.MenuItemDao;
import com.mycompany.dao.MenuItemImageDao;
import com.mycompany.pojo.MenuItem;
import com.mycompany.pojo.MenuItemImage;
import com.mycompany.validator.AddMenuItemValidator;
import com.mycompany.validator.EditMenuItemValidator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mahit
 */
@Controller
public class AdminMenuController {

    @Autowired
    ServletContext context;

    @Autowired
    AddMenuItemValidator addMenuItemValidator;
    //editMenuItemValidator
    @Autowired
    EditMenuItemValidator editMenuItemValidator;

    @GetMapping(value = "addMenuItem.htm")
    public String doGetAdd(ModelMap model, MenuItem menuItem) {
        //form-backing object
        menuItem.setCuisine("Soup");
        menuItem.setCategory("Veg");
        menuItem.setDescription("");
        menuItem.setItemName("");
        menuItem.setItemPrice(1);

        model.addAttribute("menuItem", menuItem);
        return "addMenuItem";
    }

    @GetMapping(value = "deleteMenuItem.htm")
    public String doGetDelete(ModelMap model, MenuItem menuItem) {
        MenuItemDao menuItemDao = new MenuItemDao();
        List<MenuItem> menuItemsList = menuItemDao.getMenuItems();
        model.addAttribute("menuItemsList", menuItemsList);;
        return "deleteMenuItem";
    }

    @GetMapping(value = "viewAdminMenu.htm")
    public String doGetView(ModelMap model, MenuItem menuItem) {
        MenuItemDao menuItemDao = new MenuItemDao();
        List<MenuItem> menuItemsList = menuItemDao.getMenuItems();
        model.addAttribute("menuItemsList", menuItemsList);
        return "viewAdminMenu";
    }

    @PostMapping(value = "deleteMenuItem.htm")
    public String doPostDelete(MenuItemDao menuItemDao, HttpServletRequest request, MenuItemImageDao menuItemImageDao) {
        // MenuItemDao menuItemDao = new MenuItemDao();
        Long itemId = Long.parseLong(request.getParameter("itemId"));
        MenuItem menuItem = menuItemDao.getItem1(itemId);
        for (MenuItemImage menuItemImage : menuItem.getItemImages()) {
            int res = menuItemImageDao.deleteMenuItemImage(menuItemImage.getItemImagesId());
        }
        int result1 = menuItemDao.deleteMenuItem(itemId);
        List<MenuItem> menuItemsList = menuItemDao.getMenuItems();
        request.setAttribute("menuItemsList", menuItemsList);
        return "deleteMenuItem";

    }

    @PostMapping(value = "viewAdminMenu.htm")
    public String doPostView(@ModelAttribute("menuItem") MenuItem menuItem, BindingResult result, SessionStatus status,
            HttpServletRequest request) {
        editMenuItemValidator.validate(menuItem, result);
        if (result.hasErrors()) {
            return "viewAdminMenu";
        } else {
            MenuItemDao menuItemDao = new MenuItemDao();
            Long itemId = Long.parseLong(request.getParameter("itemId"));
            // double itemPrice = Double.parseDouble(request.getParameter("price"));//price
            menuItemDao.updateMenuItemPrice(itemId, menuItem.getItemPrice());
            menuItemDao.updateMenuItemDescription(itemId, menuItem.getDescription());
            status.setComplete();
            List<MenuItem> menuItemsList = menuItemDao.getMenuItems();
            request.setAttribute("menuItemsList", menuItemsList);
            return "viewAdminMenu";
        }
    }

    @PostMapping(value = "addMenuItem.htm")
    public String doPostAdd(@RequestParam("file") ArrayList<MultipartFile> files, @ModelAttribute("menuItem") MenuItem menuItem, BindingResult result, SessionStatus status,
            ModelMap model, HttpServletRequest request, MenuItemImageDao menuItemImageDao) throws IOException {
        addMenuItemValidator.validate(menuItem, result);
        if (result.hasErrors()) {
            return "addMenuItem";
        } else {
            MenuItemDao menuItemDao = new MenuItemDao();

            int result2 = menuItemDao.add(menuItem);
            MenuItem mi = menuItemDao.getItem(menuItem.getItemName());
            for (MultipartFile file : files) {

                String fileName = file.getOriginalFilename();
                File imageFile = new File(request.getRealPath("/"), fileName);
                try {
                    file.transferTo(imageFile);
                    MenuItemImage menuItemImage = new MenuItemImage();
                    menuItemImage.setImageName(file.getOriginalFilename());

                    menuItemImage.setItemId(mi);
                    mi.getItemImages().add(menuItemImage);

                    menuItemImageDao.add(menuItemImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // String fileLocation= Paths.get(System.getProperty("java.io.tmpdir")).toString();
            //  String tempFileLocation = Paths.get(System.getProperty("java.io.tmpdir"), file.getOriginalFilename()).toString();

            // file.transferTo(new File(tempFileLocation));
            status.setComplete();
            menuItem.setCuisine("Soup");
            menuItem.setCategory("Veg");
            menuItem.setDescription("");
            menuItem.setItemName("");
            menuItem.setItemPrice(1);
            model.addAttribute("menuItem", menuItem);
            return "addMenuItem";
        }
    }
}
