package com.ecommerce.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.TypeDAO;
import com.ecommerce.entity.Type;
import com.ecommerce.model.TypeInfo;

@Repository
@Transactional
public class TypeDAOImpl implements TypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Type> getAllType(String typeid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + Type.class.getName() + " (T.id) FROM Type T"; //WHERE T.id = :TYPEID ";
		Query<Type> query = session.createQuery(hql);
		//query.setParameter("TYPEID", typeid);
		List<Type> types = query.list();
		return types;
	}
	
	@Override
	public Type getTypeById(String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT (T.id) FROM Type T WHERE T.id = :ID";
		Query<Type> query = session.createQuery(hql);
		query.setParameter("ID", id);
		Type type = (Type) query.uniqueResult();
		return type;
	}

	@Override
	public void saveTypeInfo(TypeInfo typeInfo) {
		Session session = sessionFactory.getCurrentSession();
		String id = typeInfo.getId();
		Type type = null;
		boolean isNew = false;
		
		if(id != null) {
			type = getTypeById(id);
		}
		if(type == null) {
			isNew = true;
			type = new Type();
		}
		type.setId(id);
		if (isNew) {
			session.persist(type);
		}
		session.flush();
		
	}

}
