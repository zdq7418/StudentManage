package com.student.bean;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for ClassFrom entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.student.bean.ClassFrom
  * @author MyEclipse Persistence Tools 
 */

public class ClassFromDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(ClassFromDAO.class);
		//property constants
	public static final String CLASS_NAME = "className";
	public static final String CLASS_TEACHER = "classTeacher";
	public static final String CLASS_REMARKS = "classRemarks";



	protected void initDao() {
		//do nothing
	}
    
    public void save(ClassFrom transientInstance) {
        log.debug("saving ClassFrom instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ClassFrom persistentInstance) {
        log.debug("deleting ClassFrom instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ClassFrom findById( java.lang.Integer id) {
        log.debug("getting ClassFrom instance with id: " + id);
        try {
            ClassFrom instance = (ClassFrom) getHibernateTemplate()
                    .get("com.student.bean.ClassFrom", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ClassFrom instance) {
        log.debug("finding ClassFrom instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding ClassFrom instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ClassFrom as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByClassName(Object className
	) {
		return findByProperty(CLASS_NAME, className
		);
	}
	
	public List findByClassTeacher(Object classTeacher
	) {
		return findByProperty(CLASS_TEACHER, classTeacher
		);
	}
	
	public List findByClassRemarks(Object classRemarks
	) {
		return findByProperty(CLASS_REMARKS, classRemarks
		);
	}
	

	public List findAll() {
		log.debug("finding all ClassFrom instances");
		try {
			String queryString = "from ClassFrom";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ClassFrom merge(ClassFrom detachedInstance) {
        log.debug("merging ClassFrom instance");
        try {
            ClassFrom result = (ClassFrom) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ClassFrom instance) {
        log.debug("attaching dirty ClassFrom instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ClassFrom instance) {
        log.debug("attaching clean ClassFrom instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ClassFromDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ClassFromDAO) ctx.getBean("ClassFromDAO");
	}
}