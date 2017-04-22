package com.student.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.student.bean.ClassFrom;
import com.student.bean.UserForm;
import com.student.service.BaseService;
import com.student.util.JsonTools;

public class BaseAction extends ActionSupport {
	/**
	 * 序号
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseService baseService;
	
	private PrintWriter w = getPrintWriter();


	
	
	public String findAll(){
		List<ClassFrom> list=baseService.findAll(ClassFrom.class);
		System.out.println(baseService.findAll(ClassFrom.class).toString());
		return "s";
	}
	
	
	public PrintWriter getPrintWriter() {
		ActionContext ac = ActionContext.getContext();
		HttpServletResponse res = (HttpServletResponse) ac
				.get(StrutsStatics.HTTP_RESPONSE);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter w = null;
		try {
			w = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w;
	}
	
/*get/set方法*/
	

	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	
	
}
