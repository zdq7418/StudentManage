package com.student.bean;

/**
 * CourseForm entity. @author MyEclipse Persistence Tools
 */

public class CourseForm implements java.io.Serializable {

	// Fields

	private String courseName;
	private String courseRem;

	// Constructors

	/** default constructor */
	public CourseForm() {
	}

	/** full constructor */
	public CourseForm(String courseRem) {
		this.courseRem = courseRem;
	}

	// Property accessors

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseRem() {
		return this.courseRem;
	}

	public void setCourseRem(String courseRem) {
		this.courseRem = courseRem;
	}

}