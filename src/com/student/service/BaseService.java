package com.student.service;

import java.io.Serializable;
import java.util.List;

import com.student.bean.UserForm;

public interface BaseService<T, PK extends Serializable> {
	public void save(T entity);

	public void delete(T entity);
	
	public void deleteById(Class<T> entityClass, PK id);

	public void saveOrUpdate(T entity);
	
	public void update(T entity);
	
	public List<T> findAll(Class<T> entityClass);
	
	public T findById(Class<T> entityClass, PK id);
	
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value, int type);
	
	public List<T> findByTwoProperty(Class<T> entityClass, String propertyName1,
			Object value1, String propertyName2, Object value2, int type);
  //用户登录
	public UserForm userFormLogin(UserForm userForm);
}
