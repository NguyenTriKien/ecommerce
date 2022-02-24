package com.ecommerce.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.GoogleAccountDAO;
import com.ecommerce.entity.GoogleAccount;
import com.ecommerce.entity.Product;
import com.ecommerce.model.GooglePojo;

@Repository
@Transactional
public class GoogleAccountDAOImpl implements GoogleAccountDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveGoogleAccount(GooglePojo googlePojo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String id = googlePojo.getId();
		String email = googlePojo.getEmail();
		GoogleAccount googleaccount = getGoogleAccountByEmail(email) ;
		boolean isNew = false;
		
		if(googleaccount != null) {
			isNew = false;
			System.out.println("account existed");
			
		}else if(googleaccount == null) {
			isNew = true;
			googleaccount = new GoogleAccount();
			googleaccount.setId(id);
			googleaccount.setEmail(googlePojo.getEmail());
			googleaccount.setVerifiedemail(googlePojo.isVerified_email());
			googleaccount.setName(googlePojo.getName());
			googleaccount.setGivenname(googlePojo.getGiven_name());
			googleaccount.setFamilyname(googlePojo.getFamily_name());
			googleaccount.setLink(googlePojo.getLink());
			googleaccount.setPicture(googlePojo.getPicture());
			
		}
		if(isNew == true) {
			session.persist(googleaccount);
		}
		
		session.flush();
		
	}

	@Override
	public GoogleAccount getGoogleAccountByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT GA FROM GoogleAccount GA WHERE GA.email = :GAEMAIL";
		Query<GoogleAccount> query = session.createQuery(hql);
		query.setParameter("GAEMAIL", email);
		GoogleAccount googleaccount = (GoogleAccount) query.uniqueResult();
		return googleaccount;
	}

	

}
