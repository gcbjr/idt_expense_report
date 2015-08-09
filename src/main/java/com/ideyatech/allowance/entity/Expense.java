package com.ideyatech.allowance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.opentides.bean.BaseEntity;
import org.opentides.web.json.Views;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "EXPENSE")
public class Expense extends BaseEntity {
	
	public Expense() {
		setSkipAudit(true);
	}
	
	public static enum Type {
		MEALS, REPRESENTATION, GAS_OIL, TRANSPO
	}
	
	@JsonView(Views.SearchView.class)
	@ManyToOne
	@JoinColumn(name = "RECEIPT_ID")
	private Receipt receipt;
	
	@JsonView(Views.SearchView.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_PURCHASE")
	private Date date;
	
	@JsonView(Views.SearchView.class)
	@Column(name = "AMOUNT")
	private Double amount;
	
	@JsonView(Views.SearchView.class)
	@Column(name = "LESS_AMOUNT")
	private Double lessAmount;
	
	@JsonView(Views.SearchView.class)
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@JsonView(Views.SearchView.class)
	@Transient
	private Receipt tempReceipt;

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@JsonView(Views.SearchView.class)
	public String getReceiptView() {
		if(receipt != null) {
			return receipt.getTin() + "-" + receipt.getName();
		}
		return "";
	}
	
	public Receipt getTempReceipt() {
		return tempReceipt;
	}
	
	public void setTempReceipt(Receipt tempReceipt) {
		this.tempReceipt = tempReceipt;
	}
	
	public Double getLessAmount() {
		return lessAmount;
	}
	
	public void setLessAmount(Double lessAmount) {
		this.lessAmount = lessAmount;
	}

}
