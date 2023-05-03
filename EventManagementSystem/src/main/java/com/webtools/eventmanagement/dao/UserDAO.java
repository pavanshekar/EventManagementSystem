package com.webtools.eventmanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.webtools.eventmanagement.pojo.User;

@Component
public class UserDAO extends DAO {

    public void saveUser(User user) {
        try {
            begin();
            getSession().save(user);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void deleteUser(User user) {
        begin();
        getSession().delete(user);
        commit();
    }
 
    public void deleteUserById(int userid) {
        begin();
        getSession().delete(getSession().get(User.class, userid));
        commit();
    }

    public User getUser(int userid) {
        User user = getSession().get(User.class, userid);
        return user;
    }
    
    public User getUserByEmail(String email) {
        User user = null;
        try {
            begin();
            Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            user = (User) criteria.uniqueResult();
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return user;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            begin();
            Criteria criteria = getSession().createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.eq("password", password));
            user = (User) criteria.uniqueResult();
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return user;
    }
    
    public void updateUser(User u) {
        try {
            begin();
            getSession().update(u);
            commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public List<User> getAllUsers() {
        String hql = "FROM User";
        Query query = getSession().createQuery(hql);
        List<User> users = query.list();
        return users;
    }
    
    public int getCountAllUsers() {
        String hql = "SELECT COUNT(*) FROM User";
        Query query = getSession().createQuery(hql);
        int count = ((Number) query.uniqueResult()).intValue();
        return count;
    }


}