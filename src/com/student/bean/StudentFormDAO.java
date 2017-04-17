package com.student.bean;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * StudentForm entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.student.bean.StudentForm
 * @author MyEclipse Persistence Tools
 */

public class StudentFormDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(StudentFormDAO.class);
	// property constants
	public static final String STUDENT_NAME = "studentName";
	public static final String STUDENT_SEX = "studentSex";
	public static final String STUDENT_CLA = "studentCla";
	public static final String STUDENT_TEL = "studentTel";
	public static final String STUDENT_ADD = "studentAdd";
	public static final String STUDENT_REM = "studentRem";

	protected void initDao() {
		// do nothing
	}

	public void save(StudentForm transientInstance) {
		log.debug("saving StudentForm instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StudentForm persistentInstance) {
		log.debug("deleting StudentForm instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StudentForm findById(java.lang.String id) {
		log.debug("getting StudentForm instance with id: " + id);
		try {
			StudentForm instance = (StudentForm) getHibernateTemplate().get(
					"com.student.bean.StudentForm", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StudentForm instance) {
		log.debug("finding StudentForm instance by example");
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
		log.debug("finding StudentForm instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from StudentForm as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStudentName(Object studentName) {
		return findByProperty(STUDENT_NAME, studentName);
	}

	public List findByStudentSex(Object studentSex) {
		return findByProperty(STUDENT_SEX, studentSex);
	}

	public List findByStudentCla(Object studentCla) {
		return findByProperty(STUDENT_CLA, studentCla);
	}

	public List findByStudentTel(Object studentTel) {
		return findByProperty(STUDENT_TEL, studentTel);
	}

	public List findByStudentAdd(Object studentAdd) {
		return findByProperty(STUDENT_ADD, studentAdd);
	}

	public List findByStudentRem(Object studentRem) {
		return findByProperty(STUDENT_REM, studentRem);
	}

	public List findAll() {
		log.debug("finding all StudentForm instances");
		try {
			String queryString = "from StudentForm";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StudentForm merge(StudentForm detachedInstance) {
		log.debug("merging StudentForm instance");
		try {
			StudentForm result = (StudentForm) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StudentForm instance) {
		log.debug("attaching dirty StudentForm instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StudentForm instance) {
		log.debug("attaching clean StudentForm instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StudentFormDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (StudentFormDAO) ctx.getBean("StudentFormDAO");
	}
}