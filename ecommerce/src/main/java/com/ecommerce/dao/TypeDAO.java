package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.entity.Type;

public interface TypeDAO {
	
	public List<Type> getAllType(String typeid);

	Type getTypeById(String id);
}
