package com.student.util;

import java.util.List;

import com.student.bean.ClassFrom;
import com.student.service.BaseService;
import com.student.service.impl.BaseServiceImpl;

public class Test {
	public static void main(String[] args) {
		BaseService baseService=new BaseServiceImpl();
		List<ClassFrom> list=baseService.findAll(ClassFrom.class);
		System.out.println(list);
	}

}
