package com.student.bean;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for UserForm entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.student.bean.UserForm
  * @author MyEclipse Persistence Tools 
 */

public class UserFormDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(UserFormDAO.class);
		//property constants
	public static final String PASSWD = "passwd";
	public static final String USER_NAME = "userName";
	public static final String DEPART = "depart";
	public static final String CON_PHONE = "conPhone";
	public static final String GRP_SEQ = "grpSeq";
	public static final String GRP_NAME = "grpName";
	public static final String ROLE_SEQ = "roleSeq";
	public static final String ROLE_NAME = "roleName";
	public static final String USER_STATUS = "userStatus";
	public static final String REMARK = "remark";
	public static final String LOGIN_STATUS = "loginStatus";



	protected void initDao() {
		//do nothing
	}
    
    public void save(UserForm transientInstance) {
        log.debug("saving UserForm instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(UserForm persistentInstance) {
        log.debug("deleting UserForm instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public UserForm findById( java.lang.String id) {
        log.debug("getting UserForm instance with id: " + id);
        try {
            UserForm instance = (UserForm) getHibernateTemplate()
                    .get("com.student.bean.UserForm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(UserForm instance) {
        log.debug("finding UserForm instance by example");
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
      log.debug("finding UserForm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from UserForm as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPasswd(Object passwd
	) {
		return findByProperty(PASSWD, passwd
		);
	}
	
	public List findByUserName(Object userName
	) {
		return findByProperty(USER_NAME, userName
		);
	}
	
	public List findByDepart(Object depart
	) {
		return findByProperty(DEPART, depart
		);
	}
	
	public List findByConPhone(Object conPhone
	) {
		return findByProperty(CON_PHONE, conPhone
		);
	}
	
	public List findByGrpSeq(Object grpSeq
	) {
		return findByProperty(GRP_SEQ, grpSeq
		);
	}
	
	public List findByGrpName(Object grpName
	) {
		return findByProperty(GRP_NAME, grpName
		);
	}
	
	public List findByRoleSeq(Object roleSeq
	) {
		return findByProperty(ROLE_SEQ, roleSeq
		);
	}
	
	public List findByRoleName(Object roleName
	) {
		return findByProperty(ROLE_NAME, roleName
		);
	}
	
	public List findByUserStatus(Object userStatus
	) {
		return findByProperty(USER_STATUS, userStatus
		);
	}
	
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	
	public List findByLoginStatus(Object loginStatus
	) {
		return findByProperty(LOGIN_STATUS, loginStatus
		);
	}
	

	public List findAll() {
		log.debug("finding all UserForm instances");
		try {
			String queryString = "from UserForm";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public UserForm merge(UserForm detachedInstance) {
        log.debug("merging UserForm instance");
        try {
            UserForm result = (UserForm) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(UserForm instance) {
        log.debug("attaching dirty UserForm instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(UserForm instance) {
        log.debug("attaching clean UserForm instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static UserFormDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (UserFormDAO) ctx.getBean("UserFormDAO");
	}
}