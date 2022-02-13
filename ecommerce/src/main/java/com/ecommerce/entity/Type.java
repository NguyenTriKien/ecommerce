package com.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Type")
public class Type implements Serializable {

	private static final long serialVersionUID = 1L;

	public Type() {
		super();
	}

	public Type(String id) {
		super();
		this.id = id;
	}

	@Id
	@Column(name = "typeid", length = 50, nullable = false)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
