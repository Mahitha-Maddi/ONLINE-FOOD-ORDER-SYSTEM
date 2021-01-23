/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.pojo.MenuItem;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mahit
 */
public class MenuItemDao extends BaseDao {

    public List<MenuItem> getMenuItems(String cuisine) {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from MenuItem m where m.cuisine =:cuisine";
            menuItems = (List) getSession().createQuery(hql).setParameter("cuisine", cuisine).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return menuItems;
    }
    
        public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from MenuItem";
            menuItems = (List) getSession().createQuery(hql).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return menuItems;
    }

    public double getItemPrice(String itemName) {
        MenuItem item = null;
        try {
            beginTransaction();
            String hql = "from MenuItem m where m.itemName =:itemName";
            item = (MenuItem) getSession().createQuery(hql).setParameter("itemName", itemName).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return item.getItemPrice();
    }
    
    public List<String> search(String itemName){
        Criteria c=getSession().createCriteria(MenuItem.class);
        c.add(Restrictions.like("itemName",itemName,MatchMode.ANYWHERE));
        c.setProjection(Projections.property("itemName"));
        List<String> list=c.list();
        return list;
        
    }
    
     public List<MenuItem> search1(String itemName){
         
        Criteria c=getSession().createCriteria(MenuItem.class);
        c.add(Restrictions.like("itemName",itemName,MatchMode.ANYWHERE));
        List<MenuItem> list=c.list();
        return list;
        
    }
    public MenuItem getItem(String itemName) {
        MenuItem item = null;
        try {
            beginTransaction();
            String hql = "from MenuItem m where m.itemName =:itemName";
            item = (MenuItem) getSession().createQuery(hql).setParameter("itemName", itemName).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return item;
    }
    
    public MenuItem getItem1(long itemId) {
        MenuItem item = null;
        try {
            beginTransaction();
            String hql = "from MenuItem m where m.itemId =:itemId";
            item = (MenuItem) getSession().createQuery(hql).setParameter("itemId", itemId).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return item;
    }
    
    
    public void updateMenuItemPrice(long itemId,double itemPrice){
        try{
            beginTransaction();
            MenuItem item=getSession().find(MenuItem.class,itemId);
            item.setItemPrice(itemPrice);
            getSession().update(item);
            commit();
            
        }
        catch(HibernateException e){
          getSession().getTransaction().rollback();
      }
        finally{
            getSession().close();
        }
    }
    
     public void updateMenuItemDescription(long itemId,String description){
        try{
            beginTransaction();
            MenuItem item=getSession().find(MenuItem.class,itemId);
            item.setDescription(description);
            getSession().update(item);
            commit();
            
        }
        catch(HibernateException e){
          getSession().getTransaction().rollback();
      }
        finally{
            getSession().close();
        }
    }
    
    public int deleteMenuItem(long itemId) {
        int result=0;
        try{
            beginTransaction();
            MenuItem item=getSession().find(MenuItem.class,itemId );
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
    
    
    public int add(MenuItem item) {
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

}
