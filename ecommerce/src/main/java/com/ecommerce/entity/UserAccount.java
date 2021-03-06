package com.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserAccount")
public class UserAccount implements Serializable {

	@Id
	@Column(name = "username", length=255, nullable=false)
	String username;
	
	@Column(name = "password", length=255)
	String password;
	
	@Column(name = "id", length=255, nullable = false)
	String id;
	
	@Column(name = "verified_email", length=255)
	boolean verifiedemail;
	
	@Column(name = "name", length=255)
	String name;
	
	@Column(name = "given_name", length=255)
	String givenname;
	
	@Column(name = "family_name", length=255)
	String familyname;
	
	@Column(name = "link", length=255)
	String link;
	
	@Column(name = "picture", length=255)
	String picture;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isVerifiedemail() {
		return verifiedemail;
	}

	public void setVerifiedemail(boolean verifiedemail) {
		this.verifiedemail = verifiedemail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

    
}
