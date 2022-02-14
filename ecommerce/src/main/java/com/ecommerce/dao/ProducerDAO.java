package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.entity.Producer;

public interface ProducerDAO {

	public List<Producer> getAllProducer(String producerid);

	Producer getProducerById(String id);
}
