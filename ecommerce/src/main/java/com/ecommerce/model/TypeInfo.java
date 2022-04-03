package com.ecommerce.model;

public class TypeInfo {

	private String id;
	
	private String typename;
	
	public TypeInfo() {
		super();
	}

	public TypeInfo(String id) {
		super();
		this.id = id;
	}


	public TypeInfo(String id, String typename) {
		super();
		this.id = id;
		this.typename = typename;
	}

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
