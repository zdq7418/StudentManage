package com.student.action;

import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import com.student.bean.UserForm;
import com.student.service.BaseService;
import com.student.util.DataIdentification;
import com.student.util.StringUtil;


public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserForm userForm;//用户表
	private String error;//错误信息提示
	private String verCode;//验证码
	public String Login(){
		
		try {
			// 获取session
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			 if(StringUtil.isEmpty(userForm.getUserAcct()) || StringUtil.isEmpty(userForm.getPasswd())){
				  error="用户名或密码为空!";
				  return ERROR;
		      }
			// 从session获取验证码
			String rand = session.get("rand").toString();
			// 判断验证码是否过期
			if (rand == null || rand.trim().equals("")) {
				error="验证码过期，请刷新验证码.";
				return ERROR;
			}
			
			/* * 刷新时,验证码空指针异常,并且判断登录时验证码是否输入*/
			 
			try {
				if (verCode.equals("")) {
					error="请输入验证码";
					return ERROR;
				}
			} catch (NullPointerException npe) {
				verCode = rand;
			}
			
			/* * 刷新时,用户空指针异常*/
			 
			if (userForm == null) {
				UserForm sysmUser = (UserForm) session
						.get("sysmUser");
				if (sysmUser != null) {
					sysmUser = sysmUser;
				} else {
					error="用户登录过期，请重新登录";
					return ERROR;
				}
			}
			// 判断验证码
			if (!verCode.trim().equals(rand)) {
				error="验证码输入错误";
				return ERROR;
			}
			// 查询用户
		UserForm userForms= baseService.userFormLogin(userForm);
			// 判断用户是否存在
			if (userForms == null) {
				error="登录名或密码有误";
				return ERROR;
			} else if (userForms.getLoginStatus() != null
					&& userForms.getLoginStatus().trim()
							.equals(DataIdentification.USER_STATE_DONOTUSE)) {
				error="当前用户不可用,已禁止登录";
				return ERROR;
			} else {
				/*// 获取用户菜单
				ApfSysmMenu apfSysmMenu = apfSysmMenuService
						.findApfSysmMenuList(sysmUser0.getRolenumber());

				// 保存用户到session
				session.put("sysmUser", userForms);
				session.put("sysmMenu", sysmMenu);

				return this.SUCCESS;*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			error="抱歉，登录失败";
			return ERROR;
		}
		return this.SUCCESS;
	}
	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}
	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}
	public String getVerCode() {
		return verCode;
	}
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
}
