package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Connectioninfo {
   @Id
	private String uid;
	private String datasources_uid;
	private String conectionURL;
	private String username;
	private String password;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDatasources_uid() {
		return datasources_uid;
	}
	public void setDatasources_uid(String datasources_uid) {
		this.datasources_uid = datasources_uid;
	}
	public String getConectionURL() {
		return conectionURL;
	}
	public void setConectionURL(String conectionURL) {
		this.conectionURL = conectionURL;
	}
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
	@Override
	public String toString() {
		return "Connectioninfo [uid=" + uid + ", datasources_uid=" + datasources_uid + ", conectionURL=" + conectionURL
				+ ", username=" + username + ", password=" + password + "]";
	}
	public Connectioninfo(String uid, String datasources_uid, String conectionURL, String username, String password) {
		super();
		this.uid = uid;
		this.datasources_uid = datasources_uid;
		this.conectionURL = conectionURL;
		this.username = username;
		this.password = password;
	}
		
}
