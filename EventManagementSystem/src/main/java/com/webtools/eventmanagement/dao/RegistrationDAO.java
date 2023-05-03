package com.webtools.eventmanagement.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.webtools.eventmanagement.pojo.Event;
import com.webtools.eventmanagement.pojo.Registration;
import com.webtools.eventmanagement.pojo.User;
import com.webtools.eventmanagement.pojo.Payment;

import java.util.List;
import java.util.Set;



import java.util.HashSet;


@Component
public class RegistrationDAO extends DAO {

	public void saveRegistration(Registration registration) {
	    begin();
	    getSession().save(registration);
	    commit();
	}
	
	public Registration getRegistrationById(int registrationId) {
	    return getSession().get(Registration.class, registrationId);
	}

	public Set<Registration> getAllRegistrations() {
	    String hql = "FROM Registration";
	    Query query = getSession().createQuery(hql);
	    Set<Registration> registrations = new HashSet<>(query.list());
	    return registrations;
	}
    
	public Set<Registration> getRegistrationsByUser(User user) {
	    String hql = "FROM Registration WHERE user = :user";
	    Query query = getSession().createQuery(hql);
	    query.setParameter("user", user);
	    Set<Registration> registrations = new HashSet<>(query.list());
	    return registrations;
	}
    
	public Set<Registration> getRegistrationsByEvent(Event event) {
	    String hql = "FROM Registration WHERE event = :event";
	    Query query = getSession().createQuery(hql);
	    query.setParameter("event", event);
	    Set<Registration> registrations = new HashSet<>(query.list());
	    return registrations;
	}
	
	public Registration getRegistrationByUserAndEvent(User user, Event event) {
	    String hql = "FROM Registration WHERE user = :user AND event = :event";
	    Query query = getSession().createQuery(hql);
	    query.setParameter("user", user);
	    query.setParameter("event", event);
	    return (Registration) query.uniqueResult();
	}


    public void updateRegistration(Registration registration) {
        begin();
        getSession().update(registration);
        commit();
    }

    public void deleteRegistration(Registration registration) {
        begin();
        getSession().delete(registration);
        commit();
    }

    public void deleteRegistrationById(int registrationId) {
        begin();
        getSession().delete(getSession().get(Registration.class, registrationId));
        commit();
    }

    public double getTotalRevenue() {
        String hql = "SELECT SUM(r.totalPrice) FROM Registration r";
        Query query = getSession().createQuery(hql);
        Double result = (Double) query.uniqueResult();
        return result != null ? result : 0.0;
    }


}
