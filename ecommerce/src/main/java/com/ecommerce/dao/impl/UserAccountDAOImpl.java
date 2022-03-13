package com.ecommerce.dao.impl;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.UserAccountDAO;
import com.ecommerce.entity.UserAccount;
import com.ecommerce.entity.Product;
import com.ecommerce.model.GooglePojo;
import com.ecommerce.model.UserAccountInfo;

@Repository
@Transactional
public class UserAccountDAOImpl implements UserAccountDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveGoogleAccount(GooglePojo googlePojo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String id = googlePojo.getId();
		String email = googlePojo.getEmail();
		UserAccount userAccount = getAccountByUsername(email) ;
		boolean isNew = false;
		
		if(userAccount != null) {
			isNew = false;
			System.out.println("account existed");
			
		}else if(userAccount == null) {
			isNew = true;
			userAccount = new UserAccount();
			userAccount.setId(id);
			userAccount.setUsername(googlePojo.getEmail());
			userAccount.setVerifiedemail(googlePojo.isVerified_email());
			userAccount.setName(googlePojo.getName());
			userAccount.setGivenname(googlePojo.getGiven_name());
			userAccount.setFamilyname(googlePojo.getFamily_name());
			userAccount.setLink(googlePojo.getLink());
			userAccount.setPicture(googlePojo.getPicture());
			
		}
		if(isNew == true) {
			session.persist(userAccount);
		}
		
		session.flush();
		
	}

	@Override
	public UserAccount getGoogleAccountByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT GA FROM UserAccount GA WHERE GA.email = :GAEMAIL";
		Query<UserAccount> query = session.createQuery(hql);
		query.setParameter("GAEMAIL", email);
		UserAccount googleaccount = (UserAccount) query.uniqueResult();
		return googleaccount;
	}

	@Override
	public UserAccount getAccountByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT USER FROM UserAccount USER WHERE USER.username = :USERNAME";
		Query<UserAccount> query = session.createQuery(hql);
		query.setParameter("USERNAME", username);
		UserAccount useraccount = (UserAccount) query.uniqueResult();
		return useraccount;
	}

	@Override
	public void saveUserAccount(UserAccountInfo userAccountInfo) {
		Session session = sessionFactory.getCurrentSession();
		String username = userAccountInfo.getUsername();
		UserAccount userAccount = getAccountByUsername(username);
		boolean isNew = false;
		if(userAccount != null) {
			isNew = false;
		System.out.println("account existed");
		}else if(userAccount == null) {
			isNew = true;
			userAccount = new UserAccount();
			userAccount.setId(UUID.randomUUID().toString());
			userAccount.setUsername(userAccountInfo.getUsername());
			userAccount.setPassword(userAccountInfo.getPassword());
		}
		if(isNew == true) {
			session.persist(userAccount);
		}
		
		session.flush();
	}

	

}
