/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.pojo.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

public class UserDao extends BaseDao {

     public List<User> getUsers(String userRoleStatus) {
        List<User> users = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from User o where o.role =:userRoleStatus";
            users = (List) getSession().createQuery(hql).setParameter("userRoleStatus", userRoleStatus).list();

            commit();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return users;
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            beginTransaction();
            Query q = getSession().createQuery("from User", User.class);
            users = q.list();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return users;
    }
    
    public List<User> getDeliveryMen() {
        List<User> users = new ArrayList<>();
        try {
            beginTransaction();
            String hql = "from User u where u.role=:userRole";
             users =  getSession().createQuery(hql).setParameter("userRole", "deliveryMan").list();
            //users = q.list();
        } catch (HibernateException e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return users;
    }

    public User get(String emailId) {
        User user = null;
        try {
            beginTransaction();
            String hql = "from User u where u.emailId =:emailId";
             user = (User) getSession().createQuery(hql).setParameter("emailId", emailId).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return user;
    }
    
    public String getCustomerAddress(String emailId) {
        User user = null;
        String address=null;
        try {
            beginTransaction();
            String hql = "from User u where u.emailId =:emailId";
             user = (User) getSession().createQuery(hql).setParameter("emailId", emailId).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        if(user!=null){
        address=user.getAddress();
        }
        return address;
    }
    
     public User getDeliveryMan(int userId) {
        User user = null;
        try {
            beginTransaction();
            String hql = "from User u where u.userId =:userId";
             user = (User) getSession().createQuery(hql).setParameter("userId", userId).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return user;
    }

    public User getPhoneNum(String phoneNum) {
        User user = null;
        try {
            beginTransaction();
            String hql = "from User u where u.phoneNum =:phoneNum";
             user = (User) getSession().createQuery(hql).setParameter("phoneNum", phoneNum).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return user;
    }
    
    public User getRole(String emailId) {
        User user = null;
        try {
            beginTransaction();
            String hql = "from User u where u.emailId =:emailId";
             user = (User) getSession().createQuery(hql).setParameter("emailId", emailId).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return user;
    }
    public User getPassword(String password) {
        User user = null;
        try {
            beginTransaction();
            String hql = "from User u where u.password =:password";
             user = (User) getSession().createQuery(hql).setParameter("password", password).uniqueResult();
            commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
        return user;
    }
    
    public int add(User user) {
        int result=0;
        try{
            beginTransaction();
            getSession().save(user);
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

    public int delete(Integer userId) {
         int result=0;
        try{
            beginTransaction();
            User user=getSession().find(User.class,userId);
            getSession().delete(user);
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

    public int update(Integer userId, String status) {
       int result=0;
        try{
            beginTransaction();
            User user=getSession().find(User.class,userId);
            user.setRole(status);
            getSession().update(user);
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
    
    public int updatePassword(String emailId, String password) {
       int result=0;
        try{
            beginTransaction();
            String hql = "update User u set password=:password where u.emailId =:emailId";
            Query q= getSession().createQuery(hql).setParameter("emailId",emailId).setParameter("password",password);
           int executeUpdate = q.executeUpdate();
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
    
     public int update(Integer userId, User user1) {
       int result=0;
        try{
            beginTransaction();
            User user=getSession().find(User.class,userId);
            user.setAddress(user1.getAddress());
            user.setPassword(user1.getPassword());
            user.setPhoneNum(user1.getPhoneNum());
            getSession().update(user);
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
