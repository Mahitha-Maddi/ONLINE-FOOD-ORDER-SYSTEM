/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mahit
 */
@Entity
@Table(name = "items")
public class MenuItem implements Serializable {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long itemId;
   
   private String itemName;
   private double itemPrice;
   private String category;
   private String cuisine;
   private String description;
   @OneToMany(fetch = FetchType.EAGER,mappedBy = "itemId")
   private Set<MenuItemImage> itemImages = new HashSet<>(0);;

    public MenuItem() {
       
    }

    public Set<MenuItemImage> getItemImages() {
        return itemImages;
    }

    public void setItemImages(Set<MenuItemImage> itemImages) {
        this.itemImages = itemImages;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
   
   
}
