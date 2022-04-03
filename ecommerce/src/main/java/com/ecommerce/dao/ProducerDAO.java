package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.entity.Producer;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.ProducerInfo;
import com.ecommerce.model.TypeInfo;

public interface ProducerDAO {

	public List<Producer> getAllProducer(String producerid);

	public Producer getProducerById(String producerid);
	
	public void saveProducerInfo(ProducerInfo producerInfo);
	
	public Producer getProducerById2(String producerid);
	
	public  PaginationResult<ProducerInfo> getAllProducerInfos(int page, int maxResult, int maxNavigationPage,
			String likeName, String likeCountry);
}
