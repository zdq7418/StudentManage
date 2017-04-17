package com.student.bean;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserMenu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.student.bean.UserMenu
 * @author MyEclipse Persistence Tools
 */

public class UserMenuDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserMenuDAO.class);
	// property constants
	public static final String MENU_NAME = "menuName";
	public static final String FMENU_SEQ = "fmenuSeq";
	public static final String MENU_LAYER = "menuLayer";
	public static final String MENU_PATH = "menuPath";

	protected void initDao() {
		// do nothing
	}

	public void save(UserMenu transientInstance) {
		log.debug("saving UserMenu instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserMenu persistentInstance) {
		log.debug("deleting UserMenu instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserMenu findById(java.lang.Integer id) {
		log.debug("getting UserMenu instance with id: " + id);
		try {
			UserMenu instance = (UserMenu) getHibernateTemplate().get(
					"com.student.bean.UserMenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserMenu instance) {
		log.debug("finding UserMenu instance by example");
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
		log.debug("finding UserMenu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserMenu as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMenuName(Object menuName) {
		return findByProperty(MENU_NAME, menuName);
	}

	public List findByFmenuSeq(Object fmenuSeq) {
		return findByProperty(FMENU_SEQ, fmenuSeq);
	}

	public List findByMenuLayer(Object menuLayer) {
		return findByProperty(MENU_LAYER, menuLayer);
	}

	public List findByMenuPath(Object menuPath) {
		return findByProperty(MENU_PATH, menuPath);
	}

	public List findAll() {
		log.debug("finding all UserMenu instances");
		try {
			String queryString = "from UserMenu";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserMenu merge(UserMenu detachedInstance) {
		log.debug("merging UserMenu instance");
		try {
			UserMenu result = (UserMenu) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserMenu instance) {
		log.debug("attaching dirty UserMenu instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserMenu instance) {
		log.debug("attaching clean UserMenu instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserMenuDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserMenuDAO) ctx.getBean("UserMenuDAO");
	}
}