package com.student.bean;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserRome entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.student.bean.UserRome
 * @author MyEclipse Persistence Tools
 */

public class UserRomeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserRomeDAO.class);
	// property constants
	public static final String ROLE_SEQ = "roleSeq";
	public static final String MENU_SEQ = "menuSeq";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(UserRome transientInstance) {
		log.debug("saving UserRome instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserRome persistentInstance) {
		log.debug("deleting UserRome instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserRome findById(java.lang.Integer id) {
		log.debug("getting UserRome instance with id: " + id);
		try {
			UserRome instance = (UserRome) getHibernateTemplate().get(
					"com.student.bean.UserRome", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserRome instance) {
		log.debug("finding UserRome instance by example");
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
		log.debug("finding UserRome instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserRome as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRoleSeq(Object roleSeq) {
		return findByProperty(ROLE_SEQ, roleSeq);
	}

	public List findByMenuSeq(Object menuSeq) {
		return findByProperty(MENU_SEQ, menuSeq);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all UserRome instances");
		try {
			String queryString = "from UserRome";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserRome merge(UserRome detachedInstance) {
		log.debug("merging UserRome instance");
		try {
			UserRome result = (UserRome) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserRome instance) {
		log.debug("attaching dirty UserRome instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserRome instance) {
		log.debug("attaching clean UserRome instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserRomeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserRomeDAO) ctx.getBean("UserRomeDAO");
	}
}