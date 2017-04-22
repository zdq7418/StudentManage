package com.student.bean;

import java.sql.Timestamp;

/**
 * PrizeStud entity. @author MyEclipse Persistence Tools
 */

public class PrizeStud implements java.io.Serializable {

	// Fields

	private String prizeNo;
	private String prizeStu;
	private Timestamp prizeDat;
	private Integer prizeId;

	// Constructors

	/** default constructor */
	public PrizeStud() {
	}

	/** full constructor */
	public PrizeStud(String prizeStu, Timestamp prizeDat, Integer prizeId) {
		this.prizeStu = prizeStu;
		this.prizeDat = prizeDat;
		this.prizeId = prizeId;
	}

	// Property accessors

	public String getPrizeNo() {
		return this.prizeNo;
	}

	public void setPrizeNo(String prizeNo) {
		this.prizeNo = prizeNo;
	}

	public String getPrizeStu() {
		return this.prizeStu;
	}

	public void setPrizeStu(String prizeStu) {
		this.prizeStu = prizeStu;
	}

	public Timestamp getPrizeDat() {
		return this.prizeDat;
	}

	public void setPrizeDat(Timestamp prizeDat) {
		this.prizeDat = prizeDat;
	}

	public Integer getPrizeId() {
		return this.prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

}