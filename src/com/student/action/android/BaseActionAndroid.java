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
import com.student.bean.UserForm;
import com.student.service.BaseService;
import com.student.util.JsonTools;
import com.student.util.MD5;

import freemarker.template.utility.StringUtil;

public class BaseActionAndroid extends ActionSupport {
	private BaseService baseService;
	private String serachkey;
	private String account;
	private String password;
	private String role;
	
	private PrintWriter w = getPrintWriter();


	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public void findStudentByKey(){
		List<StudentForm> lists=baseService.findByProperty(StudentForm.class, "studentName", serachkey, 1);
		w.write(JsonTools.createJsonString(lists));
	}
	
	public void login(){
		password=MD5.getMd5(password);
		String hql="from UserForm where userAcct="+account+" and passwd="+password;
		List list=baseService.findByHql(hql);
		w.write(JsonTools.createJsonString(list));
	}
	
	public void register(){
		UserForm form=new UserForm();
		form.setUserAcct(account);
		form.setPasswd(MD5.getMd5(password));
		form.setRoleSeq(1);
		baseService.save(form);
		w.write("1");
		
		
	}
	
	public void checkAccout(){
		String hql="from UserForm where userAcct='"+account+"'";
		List list=baseService.findByHql(hql);
		System.out.println(list.size());
		w.write(StringUtil.tryToString(list.size()));
		
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
