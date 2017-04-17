package com.student.bean;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ScoreForm entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.student.bean.ScoreForm
 * @author MyEclipse Persistence Tools
 */

public class ScoreFormDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ScoreFormDAO.class);
	// property constants
	public static final String SCORE_PER = "scorePer";
	public static final String SCORE_CLS = "scoreCls";
	public static final String SCORE_STU = "scoreStu";
	public static final String SCORE_COU = "scoreCou";
	public static final String SCORE_SCO = "scoreSco";

	protected void initDao() {
		// do nothing
	}

	public void save(ScoreForm transientInstance) {
		log.debug("saving ScoreForm instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ScoreForm persistentInstance) {
		log.debug("deleting ScoreForm instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ScoreForm findById(java.lang.String id) {
		log.debug("getting ScoreForm instance with id: " + id);
		try {
			ScoreForm instance = (ScoreForm) getHibernateTemplate().get(
					"com.student.bean.ScoreForm", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ScoreForm instance) {
		log.debug("finding ScoreForm instance by example");
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
		log.debug("finding ScoreForm instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ScoreForm as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByScorePer(Object scorePer) {
		return findByProperty(SCORE_PER, scorePer);
	}

	public List findByScoreCls(Object scoreCls) {
		return findByProperty(SCORE_CLS, scoreCls);
	}

	public List findByScoreStu(Object scoreStu) {
		return findByProperty(SCORE_STU, scoreStu);
	}

	public List findByScoreCou(Object scoreCou) {
		return findByProperty(SCORE_COU, scoreCou);
	}

	public List findByScoreSco(Object scoreSco) {
		return findByProperty(SCORE_SCO, scoreSco);
	}

	public List findAll() {
		log.debug("finding all ScoreForm instances");
		try {
			String queryString = "from ScoreForm";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ScoreForm merge(ScoreForm detachedInstance) {
		log.debug("merging ScoreForm instance");
		try {
			ScoreForm result = (ScoreForm) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ScoreForm instance) {
		log.debug("attaching dirty ScoreForm instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ScoreForm instance) {
		log.debug("attaching clean ScoreForm instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ScoreFormDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ScoreFormDAO) ctx.getBean("ScoreFormDAO");
	}
}