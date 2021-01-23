/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mahit
 */
@Entity
@Table(name = "itemimages")
public class MenuItemImage implements Serializable{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long itemImagesId;
   
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "itemId", nullable = false)
   private MenuItem itemId;
   private String imageName;

    public long getItemImagesId() {
        return itemImagesId;
    }

    public void setItemImagesId(long itemImagesId) {
        this.itemImagesId = itemImagesId;
    }

    public MenuItem getItemId() {
        return itemId;
    }

    public void setItemId(MenuItem itemId) {
        this.itemId = itemId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
   
   
}
