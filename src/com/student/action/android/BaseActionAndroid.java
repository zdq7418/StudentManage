package com.student.action.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.student.bean.ClassFrom;
import com.student.bean.StudentForm;
import com.student.service.BaseService;
import com.student.util.JsonTools;

public class BaseActionAndroid extends ActionSupport {
	private BaseService baseService;
	private String serachkey;
	
	private PrintWriter w = getPrintWriter();


	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public void findStudentByKey(){
		List<StudentForm> lists=baseService.findByProperty(StudentForm.class, "studentName", serachkey, 1);
		w.write(JsonTools.createJsonString(lists));
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

	public String getSerachkey() {
		return serachkey;
	}

	public void setSerachkey(String serachkey) {
		this.serachkey = serachkey;
	}
	

}
