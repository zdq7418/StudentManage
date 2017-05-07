package com.student.util;

import java.util.List;

import com.student.bean.ClassFrom;
import com.student.bean.StudentForm;
import com.student.service.BaseService;
import com.student.service.impl.BaseServiceImpl;

public class Test {
	public static void main(String[] args) {
		BaseService baseService=new BaseServiceImpl();
//		List<StudentForm> list=baseService.findAll(StudentForm.class);
		List<StudentForm> list=baseService.findByProperty(StudentForm.class, "studentName", "zhan",2);
		System.out.println(list);
	}

}
