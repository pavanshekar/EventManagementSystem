package com.webtools.eventmanagement.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.webtools.eventmanagement.pojo.Address;

@Component
public class AddressDAO extends DAO {
    
    public void saveAddress(Address a) {
        begin();
        getSession().save(a);
        commit();
    }
    
    public void updateAddress(Address a) {
        try {
            begin();
            getSession().update(a);
            commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteAddress(Address a) {
        begin();
        getSession().delete(a);
        commit();
    }
    
    public Address address(int aid) {
        Address address = getSession().get(Address.class, aid);
        return address;
    }
    
    public List<Address> getAllAddresses() {
        String hql = "FROM Address";
        Query query = getSession().createQuery(hql);
        List<Address> addresses = query.list();
        return addresses;
    }
    
}