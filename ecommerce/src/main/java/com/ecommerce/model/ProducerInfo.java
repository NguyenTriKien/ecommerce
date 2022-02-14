package com.ecommerce.model;

public class ProducerInfo {

	String producerid;
	
	String country;

	public ProducerInfo() {
		super();
	}

	public ProducerInfo(String producerid, String country) {
		super();
		this.producerid = producerid;
		this.country = country;
	}

	public String getProducerid() {
		return producerid;
	}

	public void setProducerid(String producerid) {
		this.producerid = producerid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
