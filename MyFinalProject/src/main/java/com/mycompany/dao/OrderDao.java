/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.pojo.Order;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author mahit
 */
public class OrderDao extends BaseDao {

    public int add(Order order) {
        int result = 0;
        try {
            beginTransaction();
            getSession().save(order);
            commit();
            result = 1;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return result;
    }

    public List<Order> getDeliveryManOrders(int deliveryManId, String orderStatus) {
        List<Order> deliveryManOrders = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from Order o where o.deliveryManId =:deliveryManId and o.orderStatus =:orderStatus";
            deliveryManOrders = (List) getSession().createQuery(hql).setParameter("deliveryManId", deliveryManId).setParameter("orderStatus", orderStatus).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return deliveryManOrders;
    }

    public List<Order> getcustomerOrders(int customerId) {
        List<Order> custOrders = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from Order o where o.customerId =:customerId and o.orderStatus in ('placed','accepted','assigned')";
            custOrders = (List) getSession().createQuery(hql).setParameter("customerId", customerId).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return custOrders;
    }
    
     public List<Order> getcustomerRejectedOrders(int customerId) {
        List<Order> custOrders = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from Order o where o.customerId =:customerId and o.orderStatus in ('rejected','canceled')";
            custOrders = (List) getSession().createQuery(hql).setParameter("customerId", customerId).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return custOrders;
    }
     
     public List<Order> getdeliveredCustomerOrders(int customerId) {
        List<Order> custOrders = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from Order o where o.customerId =:customerId and o.orderStatus in ('delivered')";
            custOrders = (List) getSession().createQuery(hql).setParameter("customerId", customerId).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return custOrders;
    }

    public List<Order> getOrders(String orderStatus) {
        List<Order> custOrders = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from Order o where o.orderStatus =:orderStatus";
            custOrders = (List) getSession().createQuery(hql).setParameter("orderStatus", orderStatus).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return custOrders;
    }

    public Order getOrder(Integer orderId) {
        //Write code here
        Order order = new Order();
        try {
            beginTransaction();
            order = getSession().find(Order.class, orderId);

            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }

        return order;
    }

    public int update(Integer orderId, String status) throws ParseException {
        //Write code here
        int result = 0;
        try {
            beginTransaction();
            Order order = getSession().find(Order.class, orderId);
            order.setOrderStatus(status);
            if (status.equals("delivered")) {
                String pattern = "YYYY-MM-DD hh:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                Date date1 = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss").parse(date); /////////////////////////////////////////////////////////////////////////////////
                order.setDeliveredDate(date1);
            }
            getSession().update(order);
            commit();
            result = 1;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }

        return result;
    }

    public int update(Integer orderId, int deliveryManId, String status) {
        //Write code here
        int result = 0;
        try {
            beginTransaction();
            Order order = getSession().find(Order.class, orderId);
            order.setOrderStatus(status);
            order.setDeliveryManId(deliveryManId);
            getSession().update(order);
            commit();
            result = 1;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }

        return result;
    }
    
    public int updateFeedback(Integer orderId, String feedback) {
        //Write code here
        int result = 0;
        try {
            beginTransaction();
            Order order = getSession().find(Order.class, orderId);
            order.setFeedback(feedback);
            getSession().update(order);
            commit();
            result = 1;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }

        return result;
    }

    public int delete(Integer orderId) {
        //Write code here
        int result = 0;
        try {
            beginTransaction();
            Order order = getSession().find(Order.class, orderId);
            getSession().delete(order);
            commit();
            result = 1;
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }

        return result;
    }

}
