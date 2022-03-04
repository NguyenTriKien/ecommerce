package com.ecommerce.dao.impl;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.entity.User;
import com.ecommerce.model.OrderInfo;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.UserInfo;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User getUserById(String userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT USER FROM User USER WHERE USER.userid = :USERID";
		Query<User> query = session.createQuery(hql);
		query.setParameter("USERID", userId);
		User user = (User) query.uniqueResult();
		return user;
	}
	
	@Override
	public User getUserByName(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT USER FROM User USER WHERE USER.username = :USERNAME";
		Query<User> query = session.createQuery(hql);
		query.setParameter("USERNAME", username);
		User user = (User) query.uniqueResult();
		return user;	
	}

	@Override
	public void saveUserInfo(UserInfo userInfo) {
		Session session = sessionFactory.getCurrentSession();
		String username = userInfo.getUsername();
		User user = null;
		boolean isNew = false;
		
		if(username != null) {
			user = getUserByName(username);
		}
		
		if(username == null) {
			isNew = true;
			user = new User();
		}
		
		user.setUserid(UUID.randomUUID().toString());
		user.setUsername(username);
		String encodedPassword = bCryptPasswordEncoder.encode(userInfo.getPassword());
		user.setPassword(encodedPassword);
		user.setUserRole(userInfo.getUserRole());
		
		if (isNew) {
			session.persist(user);
		}
		session.flush();
	}

	@Override
	public PaginationResult<OrderInfo> getAllOrderInfos(int page, int maxResult, int maxNavigationPage) {
		// TODO Auto-generated method stub
		return null;
	}
}
