package com.ideyatech.allowance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.opentides.bean.BaseEntity;
import org.opentides.web.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

@Table(name = "RECEIPT")
@Entity
public class Receipt extends BaseEntity {
	
	private static final long serialVersionUID = -6459089008308291475L;
	
	public Receipt() {
		setSkipAudit(true);
	}

	@JsonView(Views.SearchView.class)
	@Column(name = "TIN", unique = true)
	private String tin;
	
	@JsonView(Views.SearchView.class)
	@Column(name = "NAME_")
	private String name;
	
	@JsonView(Views.SearchView.class)
	@Column(name = "ADDRESS1")
	private String address1;
	
	@JsonView(Views.SearchView.class)
	@Column(name = "ADDRESS2")
	private String address2;

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@JsonView(Views.SearchView.class)
	public String getDisplay() {
		return tin + "-" + name;
	}
	
}
