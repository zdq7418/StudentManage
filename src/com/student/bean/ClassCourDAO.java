package com.student.bean;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ClassCour entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.student.bean.ClassCour
 * @author MyEclipse Persistence Tools
 */

public class ClassCourDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ClassCourDAO.class);
	// property constants
	public static final String CLASS_NAME = "className";

	protected void initDao() {
		// do nothing
	}

	public void save(ClassCour transientInstance) {
		log.debug("saving ClassCour instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClassCour persistentInstance) {
		log.debug("deleting ClassCour instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClassCour findById(java.lang.Integer id) {
		log.debug("getting ClassCour instance with id: " + id);
		try {
			ClassCour instance = (ClassCour) getHibernateTemplate().get(
					"com.student.bean.ClassCour", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ClassCour instance) {
		log.debug("finding ClassCour instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ClassCour instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ClassCour as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByClassName(Object className) {
		return findByProperty(CLASS_NAME, className);
	}

	public List findAll() {
		log.debug("finding all ClassCour instances");
		try {
			String queryString = "from ClassCour";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ClassCour merge(ClassCour detachedInstance) {
		log.debug("merging ClassCour instance");
		try {
			ClassCour result = (ClassCour) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClassCour instance) {
		log.debug("attaching dirty ClassCour instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClassCour instance) {
		log.debug("attaching clean ClassCour instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClassCourDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ClassCourDAO) ctx.getBean("ClassCourDAO");
	}
}