package com.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Producer")
public class Producer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "producerid", length = 50, nullable = false)
	private String producerid;
	
	@Column(name = "country", length = 255, nullable = false)
	private String country;

	public Producer() {
		super();
	}
	
	public Producer(String producerid) {
		super();
		this.producerid = producerid;
	}

	public Producer(String producerid, String country) {
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
