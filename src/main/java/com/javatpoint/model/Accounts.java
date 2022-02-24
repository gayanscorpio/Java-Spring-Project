package com.javatpoint.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity 
@Entity
//defining class name as Table name
@Table
public class Accounts {
//Defining book id as primary key

	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountid;

	@Column
	private String bsb;

	public Accounts() {

	}

	@Id
	@Column
	private String identification;

	@Column
	private OffsetDateTime openingDate;

	@Column
	private OffsetDateTime balanceDate;

	@Override
	public String toString() {
		return "Accounts [accountid=" + accountid + ", bsb=" + bsb + ", identification=" + identification
				+ ", openingDate=" + openingDate + ", balanceDate=" + balanceDate + ", balance=" + balance + "]";
	}

	@Column
	private double balance;

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getBsb() {
		return bsb;
	}

	public void setBsb(String bsb) {
		this.bsb = bsb;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public OffsetDateTime getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(OffsetDateTime openingDate) {
		this.openingDate = openingDate;
	}

	public OffsetDateTime getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(OffsetDateTime balanceDate) {
		this.balanceDate = balanceDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}