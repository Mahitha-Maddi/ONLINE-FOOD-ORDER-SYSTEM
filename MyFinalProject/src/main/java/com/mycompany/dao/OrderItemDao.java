/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.pojo.Order;
import com.mycompany.pojo.OrderItem;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author mahit
 */
public class OrderItemDao extends BaseDao {
     public int add(OrderItem orderItem) {
        int result=0;
        try{
            beginTransaction();
            getSession().save(orderItem);
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
     
       public List<OrderItem> getOrderItems(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from OrderItem o where o.orderId =:orderId";
            orderItems = (List) getSession().createQuery(hql).setParameter("orderId", orderId).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return orderItems;
    }
   
}
