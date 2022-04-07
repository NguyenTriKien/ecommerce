package com.ecommerce.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.ProducerDAO;
import com.ecommerce.entity.Producer;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.ProducerInfo;


@Repository
@Transactional
public class ProducerDAOImpl implements ProducerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Producer> getAllProducer(String producersid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + Producer.class.getName() + " (P.producerid) FROM Producer P"; 
		Query<Producer> query = session.createQuery(hql);
		List<Producer> producers = query.list();
		return producers;
	}

	@Override
	public Producer getProducerById(String producerid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT (P.producerid, P.producername, P.country) FROM Producer P WHERE P.producerid = :PRODUCERID";
		Query<Producer> query = session.createQuery(hql);
		query.setParameter("PRODUCERID", producerid);
		Producer producer = (Producer) query.uniqueResult();
		return producer;
	}
	
	@Override
	public Producer getProducerById2(String producerid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT PROD FROM Producer PROD WHERE PROD.producerid = :PRODUCERID";
		Query<Producer> query = session.createQuery(hql);
		query.setParameter("PRODUCERID", producerid);
		Producer producer = (Producer) query.uniqueResult();
		return producer;
	}

	@Override
	public void saveProducerInfo(ProducerInfo producerInfo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String producerid = producerInfo.getProducerid();
		Producer producer = null;
		boolean isNew = false;
		
		if(producerid != null) {
			producer = getProducerById2(producerid);
		}
		
		if(producer == null) {
			isNew = true;
			producer = new Producer();
		}
		producer.setProducerid(producerid);
		producer.setProducername(producerInfo.getProducername());
		producer.setCountry(producerInfo.getCountry());
		if (isNew) {
			session.persist(producer);
		}
		session.flush();
	}

	@Override
	public PaginationResult<ProducerInfo> getAllProducerInfos(int page, int maxResult, int maxNavigationPage,
			String likeName, String likeCountry) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " SELECT NEW " + ProducerInfo.class.getName() + " (PROD.producerid, PROD.producername, PROD.country) FROM Producer PROD ";
		if (likeName != null && likeName.length() > 0 && likeCountry != null && likeCountry.length() > 0 ) {
			hql += " WHERE LOWER(PROD.producername) like :LIKENAME AND LOWER(PROD.country) like :LIKECOUNTRY";
		} else if(likeName != null && likeName.length() > 0 && likeCountry == null) {
			hql += " WHERE LOWER(PROD.producername) like :LIKENAME";
		} else if(likeName == null && likeName.length() == 0 && likeCountry != null && likeCountry.length() > 0) {
			hql += " WHERE LOWER(PROD.country) like :LIKECOUNTRY";
		} else if(likeName != null && likeName.length() > 0) {
			hql += " WHERE LOWER(PROD.producername) like :LIKENAME";
		} else if(likeCountry != null && likeCountry.length() > 0) {
			hql += " WHERE LOWER(PROD.country) like :LIKECOUNTRY";
		}
		hql += " ORDER BY PROD.producerid DESC ";
		
		Query<ProducerInfo> query = session.createQuery(hql);

		if (likeName != null && likeName.length() > 0) {
			query.setParameter("LIKENAME", "%" + likeName.toLowerCase() + "%");
		}
		if (likeCountry != null && likeCountry.length() > 0) {
			query.setParameter("LIKECOUNTRY", "%" + likeCountry.toLowerCase() + "%");
		}
		List<ProducerInfo> producerInfos = query.list();
		
		return new PaginationResult<ProducerInfo>(query, page, maxResult, maxNavigationPage);
	}


}
