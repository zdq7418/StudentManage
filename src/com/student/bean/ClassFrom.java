package com.student.bean;



/**
 * ClassFrom entity. @author MyEclipse Persistence Tools
 */

public class ClassFrom  implements java.io.Serializable {


    // Fields    

     private Integer classNo;
     private String className;
     private String classTeacher;
     private String classRemarks;


    // Constructors

    /** default constructor */
    public ClassFrom() {
    }

    
    /** full constructor */
    public ClassFrom(String className, String classTeacher, String classRemarks) {
        this.className = className;
        this.classTeacher = classTeacher;
        this.classRemarks = classRemarks;
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

    public String getClassTeacher() {
        return this.classTeacher;
    }
    
    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getClassRemarks() {
        return this.classRemarks;
    }
    
    public void setClassRemarks(String classRemarks) {
        this.classRemarks = classRemarks;
    }
   








}