package com.webtools.eventmanagement.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.webtools.eventmanagement.pojo.Payment;

@Component
public class PaymentDAO extends DAO {

    public void savePayment(Payment payment) {
        try {
            begin();
            getSession().save(payment);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void deletePayment(Payment payment) {
        begin();
        getSession().delete(payment);
        commit();
    }
 
    public void deletePaymentById(int paymentId) {
        begin();
        getSession().delete(getSession().get(Payment.class, paymentId));
        commit();
    }

    public Payment getPayment(int paymentId) {
        Payment payment = getSession().get(Payment.class, paymentId);
        return payment;
    }

    public List<Payment> getAllPayments() {
        String hql = "FROM Payment";
        Query query = getSession().createQuery(hql);
        List<Payment> payments = query.list();
        return payments;
    }
    
    public int getCountAllPayments() {
        String hql = "SELECT COUNT(*) FROM Payment";
        Query query = getSession().createQuery(hql);
        int count = ((Number) query.uniqueResult()).intValue();
        return count;
    }

}

