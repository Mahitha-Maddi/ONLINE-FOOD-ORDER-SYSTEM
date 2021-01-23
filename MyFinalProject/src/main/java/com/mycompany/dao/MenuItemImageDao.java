/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.pojo.MenuItemImage;
import org.hibernate.HibernateException;

/**
 *
 * @author mahit
 */
public class MenuItemImageDao extends BaseDao{
    
    public int add(MenuItemImage item) {
        int result=0;
        try{
            beginTransaction();
            getSession().save(item);
            commit();
            result=1;
        }
        catch(HibernateException e){
            getSession().getTransaction().rollback();
        }
        finally{
            getSession().close();
        }
        return result;
    }
    
    
    public int deleteMenuItemImage(long itemImagesId ){
         int result=0;
        try{
            beginTransaction();
            MenuItemImage item=getSession().find(MenuItemImage.class,itemImagesId );
            getSession().delete(item);
            commit();
            result=1; 
        }
        catch(HibernateException e){
          getSession().getTransaction().rollback();
      }
        finally{
            getSession().close();
        }
        
        return result;
    }

}
