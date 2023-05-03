package com.webtools.eventmanagement.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int addrid;
    private String streetName;
    private int streetNum;
    private String city;
    private String state;
    private int zip;
    private String country;
    @OneToOne(mappedBy = "address")
    private User user;
    
    public Address() {
        
    }

    public int getAddrid() {
        return addrid;
    }
    
    public void setAddrid(int arrdid) {
        this.addrid = arrdid;
    }
    
    public String getStreetName() {
        return streetName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
    public int getStreetNum() {
        return streetNum;
    }
    
    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public int getZip() {
        return zip;
    }
    
    public void setZip(int zip) {
        this.zip = zip;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}