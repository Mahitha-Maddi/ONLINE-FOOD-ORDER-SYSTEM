/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.google.gson.Gson;
import com.mycompany.dao.MenuItemDao;
import com.mycompany.pojo.MenuItem;
import com.mycompany.pojo.OrderItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mahit
 */
@Controller
public class MenuController extends HttpServlet {

    @GetMapping(value = "menu.htm")
    public String doGet(ModelMap model, MenuItemDao menuDao) {
        List<MenuItem> soupItemsList = menuDao.getMenuItems("soup");
        List<MenuItem> appetizerItemsList = menuDao.getMenuItems("appetizer");
        List<MenuItem> dessertItemsList = menuDao.getMenuItems("dessert");
        List<MenuItem> pizzaItemsList = menuDao.getMenuItems("pizza");
        List<MenuItem> burgerItemsList = menuDao.getMenuItems("burger");
        List<MenuItem> snackItemsList = menuDao.getMenuItems("snack");
        List<MenuItem> drinkItemsList = menuDao.getMenuItems("drink");

        model.addAttribute("soupItemsList", soupItemsList);
        model.addAttribute("appetizerItemsList", appetizerItemsList);
        model.addAttribute("dessertItemsList", dessertItemsList);
        model.addAttribute("pizzaItemsList", pizzaItemsList);
        model.addAttribute("burgerItemsList", burgerItemsList);
        model.addAttribute("snackItemsList", snackItemsList);
        model.addAttribute("drinkItemsList", drinkItemsList);
        return "menu";
    }

    //@PostMapping(value = "addCart.htm")
    @RequestMapping(value = "addCart.htm", method = RequestMethod.POST)
    @ResponseBody
    public String doPost(@RequestBody String validationResponse,HttpServletRequest request, HttpSession session, MenuItemDao menuDao, HttpServletResponse response) throws IOException {

        ArrayList<OrderItem> items;
        boolean flag = false;
       /** String selectedItemName = "item1";
        String selectedItemDescription = "My appetizer1";
        int qty = (4);
        **/
        String[] arrOfStr = validationResponse.split("&", 5); 
        String[] itemNameArray=arrOfStr[0].split("=",2);
        String[] QtyArray=arrOfStr[1].split("=",2);
        String[] descriptionArray=arrOfStr[2].split("=",2);
        String selectedItemName=itemNameArray[1].replace("+"," ");
        int qty=Integer.parseInt(QtyArray[1].replace("+"," "));
        String selectedItemDescription=descriptionArray[1].replace("+"," ");
        
        
       // System.out.println(validationResponse);
       // System.out.println(validationResponse.getItemName());
        /**String selectedItemName = request.getParameter("itemName");
        String selectedItemDescription = request.getParameter("description");
        int qty = Integer.parseInt(request.getParameter("itemQty"));
        //////////////////////////////////////////////
        if (qty == 0) {
            response.sendRedirect("menu.htm");
            return;
        }

        if (session == null) {
            response.sendRedirect("login.htm");
            return;
        }**/
        
        double itemPrice = menuDao.getItemPrice(selectedItemName);
        if (session.getAttribute("items") != null) {
            items = (ArrayList<OrderItem>) session.getAttribute("items");
            for (OrderItem item : items) {
                if (item.getItemName().equals(selectedItemName)) {
                    flag = true;
                    item.setItemName(selectedItemName);
                    item.setItemPrice(itemPrice);
                    int qty1 = item.getQuantity() + qty;
                    item.setQuantity(qty1);
                    item.setTotalPrice(itemPrice * qty1);

                    break;
                }
            }

            if (flag == false) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItemName(selectedItemName);
                orderItem.setItemPrice(itemPrice);
                orderItem.setQuantity(qty);
                orderItem.setTotalPrice(itemPrice * qty);
                orderItem.setDescription(selectedItemDescription);
                items.add(orderItem);
            }

        } else {
            items = new ArrayList<>();
            OrderItem orderItem = new OrderItem();
            orderItem.setItemName(selectedItemName);
            orderItem.setItemPrice(itemPrice);
            orderItem.setQuantity(qty);
            orderItem.setTotalPrice(itemPrice * qty);
            orderItem.setDescription(selectedItemDescription);

            items.add(orderItem);
        }

        session.setAttribute("items", items);
        //int x = 0;
        //response.sendRedirect("menu.htm");
        //return "success";
        String string = "successful"; // The String which Need To Be Converted
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
       return string;

    }

    @GetMapping(value = "viewCart.htm")
    public String doGetViewCart(HttpServletRequest request, HttpSession session) {
        ArrayList<OrderItem> itemlist = (ArrayList<OrderItem>) session.getAttribute("items");

        request.setAttribute("items1", itemlist);

        ArrayList<OrderItem> list = (ArrayList<OrderItem>) session.getAttribute("items");
        if (list == null || list.size() == 0) {
            request.setAttribute("error", "Cart is empty!");

        }
        return "viewCart";
    }

    @PostMapping(value = "viewCart.htm")
    public String doPostViewCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        String optionValue = (String) request.getParameter("optionValue");
        String ItemName = request.getParameter("itemName");
        ArrayList<OrderItem> itemlist = (ArrayList<OrderItem>) session.getAttribute("items");
        if (optionValue.equals("editQty")) {
            int qty = Integer.parseInt(request.getParameter("itemQty"));
            if (itemlist != null) {
                for (OrderItem orderItem : itemlist) {
                    if (orderItem.getItemName().equals(ItemName)) {
                        orderItem.setQuantity(qty);
                        break;

                    }
                }
                session.setAttribute("items", itemlist);
            }

            return "viewCart";
        }

        if (itemlist != null) {
            for (OrderItem orderItem : itemlist) {
                if (orderItem.getItemName().equals(ItemName)) {
                    itemlist.remove(orderItem);
                    break;

                }
            }
            session.setAttribute("items", itemlist);
        }
        ArrayList<OrderItem> list = (ArrayList<OrderItem>) session.getAttribute("items");
        if (list == null || list.size() == 0) {
            request.setAttribute("error", "Cart is empty!");

        }

        return "viewCart";

    }
    
    
    @GetMapping(value = "searchItems.htm")
    public String doGetsearchItems(HttpServletRequest request, HttpSession session) {
      
        return "searchItems";
    }
    
    
    @RequestMapping(value = "/search.htm", method = RequestMethod.GET)
    public @ResponseBody
    String doGetsearchItemsResults(HttpServletRequest request, HttpSession session,MenuItemDao menuItemDao) {
      
        Gson gson=new Gson();
        List<String> list=menuItemDao.search(request.getParameter("term"));
        return gson.toJson(list);
    }
    
    @PostMapping(value = "search.htm")
    public String doPostsearchItemsResults(HttpServletRequest request, HttpSession session,MenuItemDao menuItemDao ) {
        String itemName=(String)request.getParameter("itemName");
        List<MenuItem> menuItemList=menuItemDao.search1(itemName);
        request.setAttribute("menuItemList",menuItemList);
        return "searchResults";
    }
    
    
    
}
