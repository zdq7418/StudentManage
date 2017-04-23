package com.student.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.student.dao.BaseDao;

public class BaseDaoImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements BaseDao<T, PK> {

	static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	public void save(T entity) {
		Transaction tx = getSession().beginTransaction();
		try {
			getHibernateTemplate().save(entity);
			tx.commit();
			getSession().close();
			logger.debug("save successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.error("save failed", re);
			throw re;
		}

	}

	public void delete(T entity) {
		Transaction tx = getSession().beginTransaction();
		try {
			getHibernateTemplate().delete(entity);
			tx.commit();
			logger.debug("delete successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.error("delete failed", re);
			throw re;
		} finally {
			getSession().close();
		}

	}

	public void deleteById(Class<T> entityClass, PK id) {
		Transaction tx = getSession().beginTransaction();
		try {
			getHibernateTemplate().delete(findById(entityClass, id));
			tx.commit();
			logger.debug("deleteById successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.error("deleteById failed", re);
			throw re;
		} finally {
			getSession().close();
		}

	}

	public void saveOrUpdate(T entity) {
		Transaction tx = getSession().beginTransaction();
		try {
			getHibernateTemplate().merge(entity);
			tx.commit();
			logger.debug("saveOrUpdate successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.error("saveOrUpdate failed", re);
			throw re;
		} finally {
			getSession().close();
		}

	}

	public void update(T entity) {
		Transaction tx = getSession().beginTransaction();
		try {
			// Session session = getSession();
			// session.clear();
			getHibernateTemplate().update(entity);
			tx.commit();
			getSession().close();
			logger.debug("update successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.error("update failed", re);
			throw re;
		}

	}

	public List<T> findAll(Class<T> entityClass) {
		try {
			return getHibernateTemplate().loadAll(entityClass);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value, int type) {
		String queryString = "";
		try {
			if (type == 1) {// 
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + " = ?";
				return getHibernateTemplate().find(queryString, value);
			} else if (type == 2) {//
				queryString = "from " + entityClass.getName()
						+ " as model where model." + propertyName + " like ?";
				return getHibernateTemplate().find(queryString, "%"+value+"%");
			}
			return null;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public T findById(Class<T> entityClass, PK id) {
		try {
			return getHibernateTemplate().get(entityClass, id);
		} catch (RuntimeException re) {
			logger.error("findById " + entityClass.getName() + " failed :{}",
					re);
			throw re;
		}
	}

	public List<T> findByTwoProperty(Class<T> entityClass,
			String propertyName1, Object value1, String propertyName2,
			Object value2, int type) {

		String queryString1 = "";
		String queryString2 = "";
		List<T> list = null;
		try {
			if (type == 1) {// type=1�Ǿ�ȷ����
				queryString1 = "from " + entityClass.getName()
						+ " as model where model." + propertyName1
						+ " = ? and model." + propertyName2 + " = ?";
				list = getHibernateTemplate()
						.find(queryString1, value1, value2);

			} else if (type == 2) {// type=2��ģ�����
				queryString2 = "from " + entityClass.getName()
						+ " as model where model." + propertyName1
						+ " like ? and model." + propertyName2 + " like ?";
				list = getHibernateTemplate()
						.find(queryString2, "%"+value1+"%", "%"+value2+"%");
			}
			return list;

		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List findByHql(String hql) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(hql);
	}

	public List findBySql(String sql) {
		// TODO Auto-generated method stub
		Session session=getSession();
		Query query = session.createSQLQuery(sql);
		List data = query.list();
		return data;
	}

}
