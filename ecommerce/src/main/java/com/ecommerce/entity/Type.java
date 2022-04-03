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

	public Type(String id, String typename) {
		super();
		this.id = id;
		this.typename = typename;
	}

	@Id
	@Column(name = "typeid", length = 255, nullable = false)
	private String id;
	
	@Column(name = "typename", length = 255, nullable = false)
	private String typename;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	
}
