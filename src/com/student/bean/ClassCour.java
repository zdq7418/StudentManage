package com.student.bean;

/**
 * ClassCour entity. @author MyEclipse Persistence Tools
 */

public class ClassCour implements java.io.Serializable {

	// Fields

	private Integer classNo;
	private String className;

	// Constructors

	/** default constructor */
	public ClassCour() {
	}

	/** full constructor */
	public ClassCour(String className) {
		this.className = className;
	}

	// Property accessors

	public Integer getClassNo() {
		return this.classNo;
	}

	public void setClassNo(Integer classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}