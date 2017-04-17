package com.student.bean;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for PrizeStud entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.student.bean.PrizeStud
  * @author MyEclipse Persistence Tools 
 */

public class PrizeStudDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(PrizeStudDAO.class);
		//property constants
	public static final String PRIZE_STU = "prizeStu";
	public static final String PRIZE_NAM = "prizeNam";



	protected void initDao() {
		//do nothing
	}
    
    public void save(PrizeStud transientInstance) {
        log.debug("saving PrizeStud instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PrizeStud persistentInstance) {
        log.debug("deleting PrizeStud instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PrizeStud findById( java.lang.String id) {
        log.debug("getting PrizeStud instance with id: " + id);
        try {
            PrizeStud instance = (PrizeStud) getHibernateTemplate()
                    .get("com.student.bean.PrizeStud", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PrizeStud instance) {
        log.debug("finding PrizeStud instance by example");
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
      log.debug("finding PrizeStud instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PrizeStud as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPrizeStu(Object prizeStu
	) {
		return findByProperty(PRIZE_STU, prizeStu
		);
	}
	
	public List findByPrizeNam(Object prizeNam
	) {
		return findByProperty(PRIZE_NAM, prizeNam
		);
	}
	

	public List findAll() {
		log.debug("finding all PrizeStud instances");
		try {
			String queryString = "from PrizeStud";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PrizeStud merge(PrizeStud detachedInstance) {
        log.debug("merging PrizeStud instance");
        try {
            PrizeStud result = (PrizeStud) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PrizeStud instance) {
        log.debug("attaching dirty PrizeStud instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PrizeStud instance) {
        log.debug("attaching clean PrizeStud instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static PrizeStudDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (PrizeStudDAO) ctx.getBean("PrizeStudDAO");
	}
}