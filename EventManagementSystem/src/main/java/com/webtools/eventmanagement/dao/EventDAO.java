package com.webtools.eventmanagement.dao;

import java.util.Base64;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.webtools.eventmanagement.pojo.Event;

@Component
public class EventDAO extends DAO {

    public void saveEvent(Event event) {
        begin();
        getSession().save(event);
        commit();
    }

    public Event getEvent(int eventId) {
        Event event = getSession().get(Event.class, eventId);
        if (event != null) {
            byte[] image = event.getImage();
            if (image != null && image.length > 0) {
                event.setBase64Image(Base64.getEncoder().encodeToString(image));
            }
        }
        return event;
    }

    public List<Event> getAllEvents() {
        String hql = "FROM Event";
        Query query = getSession().createQuery(hql);
        List<Event> events = query.list();
        for (Event event : events) {
            byte[] image = event.getImage();
            if (image != null && image.length > 0) {
                event.setBase64Image(Base64.getEncoder().encodeToString(image));
            }
        }
        return events;
    }
    
    public List<Event> getOrganizedEvents(int userid) {
    // Get the user ID from the session
    // Construct the HQL query with a condition for the user ID
    String hql = "FROM Event WHERE userid = :userId";
    Query query = getSession().createQuery(hql);
    query.setParameter("userId", userid);

    // Retrieve the events and update their images as before
    List<Event> events = query.list();
    for (Event event : events) {
        byte[] image = event.getImage();
        if (image != null && image.length > 0) {
            event.setBase64Image(Base64.getEncoder().encodeToString(image));
        }
    }
    return events;
}


    public void updateEvent(Event event) {
        try {
            begin();
            getSession().update(event);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void mergeEvent(Event event) {
        try {
            begin();
            getSession().merge(event);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }


    public void deleteEvent(Event event) {
        begin();
        getSession().delete(event);
        commit();
    }

    public void deleteEventById(int eventid) {
        begin();
        getSession().delete(getSession().get(Event.class, eventid));
        commit();
    }
    
    public int getCountAllEvents() {
        String hql = "SELECT COUNT(*) FROM Event";
        Query query = getSession().createQuery(hql);
        int count = ((Number) query.uniqueResult()).intValue();
        return count;
    }

}