package com.student.service.impl;

import java.io.Serializable;
import java.util.List;

import com.student.bean.UserForm;
import com.student.dao.BaseDao;
import com.student.service.BaseService;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK>  {
	private BaseDao<T, PK> baseDao;
	
	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void save(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
		
	}

	public void delete(T entity) {
		// TODO Auto-generated method stub
		baseDao.delete(entity);
		
	}

	public void deleteById(Class<T> entityClass, PK id) {
		// TODO Auto-generated method stub
		baseDao.deleteById(entityClass, id);
		
	}

	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(entity);
		
	}

	public void update(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
		
	}

	public List<T> findAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return baseDao.findAll(entityClass);
	}

	public T findById(Class<T> entityClass, PK id) {
		// TODO Auto-generated method stub
		return baseDao.findById(entityClass, id);
	}

	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value, int type) {
		// TODO Auto-generated method stub
		return baseDao.findByProperty(entityClass, propertyName, value, type);
	}

	public List<T> findByTwoProperty(Class<T> entityClass,
			String propertyName1, Object value1, String propertyName2,
			Object value2, int type) {
		// TODO Auto-generated method stub
		return baseDao.findByTwoProperty(entityClass, propertyName1, value1, propertyName2, value2, type);
	}
//用户登录 
	public UserForm userFormLogin(UserForm userForm) {
		
		return null;
	}

	public List findByHql(String hql) {
		// TODO Auto-generated method stub
		return baseDao.findByHql(hql);
	}

	public List findBySql(String sql) {
		// TODO Auto-generated method stub
		return baseDao.findBySql(sql);
	}


	

}
