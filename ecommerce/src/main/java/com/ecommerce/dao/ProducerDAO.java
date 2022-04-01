package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.entity.Producer;
import com.ecommerce.model.ProducerInfo;

public interface ProducerDAO {

	public List<Producer> getAllProducer(String producerid);

	public Producer getProducerById(String producerid);
	
	public void saveProducerInfo(ProducerInfo producerInfo);
	
	public Producer getProducerById2(String producerid);
}
