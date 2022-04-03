package com.ecommerce.model;

public class ProducerInfo {

	private String producerid;
	
	private String producername;
	
	private String country;
	

	public ProducerInfo() {
		super();
	}

	public ProducerInfo(String producerid) {
		super();
		this.producerid = producerid;
	}

	public ProducerInfo(String producerid, String producername, String country) {
		super();
		this.producerid = producerid;
		this.producername = producername;
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

	public String getProducername() {
		return producername;
	}

	public void setProducername(String producername) {
		this.producername = producername;
	}
	
	
}
