package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.entity.Type;
import com.ecommerce.model.TypeInfo;

public interface TypeDAO {
	
	public List<Type> getAllType(String typeid);

	Type getTypeById(String id);
	
	public void saveTypeInfo(TypeInfo typeInfo);
}
