// default package
// Generated 2009-10-23 23:19:36 by Hibernate Tools 3.2.2.GA
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/*
 * Home object for domain model class AfElement.
 * @see .AfElement
 * @author Hibernate Tools
 */
public class AfElementHome {
	private static final Log log = LogFactory.getLog(AfElementHome.class);
	private final SessionFactory sessionFactory = getSessionFactory();
	
	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		}
		catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}
	
	public void persist(AfElement transientInstance) {
		log.debug("persisting AfElement instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		}
		catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void attachDirty(AfElement instance) {
		log.debug("attaching dirty AfElement instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		}
		catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void attachClean(AfElement instance) {
		log.debug("attaching clean AfElement instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		}
		catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void delete(AfElement persistentInstance) {
		log.debug("deleting AfElement instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		}
		catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public AfElement merge(AfElement detachedInstance) {
		log.debug("merging AfElement instance");
		try {
			AfElement result = (AfElement) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		}
		catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public AfElement findById(java.lang.Long id) {
		log.debug("getting AfElement instance with id: " + id);
		try {
			AfElement instance = (AfElement) sessionFactory.getCurrentSession().get("AfElement", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			}
			else {
				log.debug("get successful, instance found");
			}
			return instance;
		}
		catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(AfElement instance) {
		log.debug("finding AfElement instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("AfElement").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		}
		catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
