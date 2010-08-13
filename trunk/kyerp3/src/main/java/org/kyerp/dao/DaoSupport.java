package org.kyerp.dao;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.utils.GenericsUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public abstract class DaoSupport<T> implements DAO<T>{
	protected final Log		logger		= LogFactory.getLog(getClass());
	protected Class<T>		entityClass	= (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());
	@PersistenceContext
	protected EntityManager	em;

	public void clear() {
		em.clear();
	}

	// @Transactional(rollbackFor = { RuntimeException.class, Exception.class, Throwable.class })
	public void delete(Serializable... entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(this.entityClass, id));
		}
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		if(entityId == null) {
			throw new RuntimeException(this.entityClass.getName() + ":传入的实体id不能为空");
		}
		return em.find(this.entityClass, entityId);
	}
	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public T findByProperty(String propertyName,Object value) {
		if(null == propertyName || null == value) {
			throw new RuntimeException(this.entityClass.getName() + ":传入的实体属性："+propertyName+"与值"+value+"不能为空");
		}
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" 1=?").append((queryParams.size() + 1));
		queryParams.add(1);
		// set parent id
		if(null != propertyName && null != value) {
			wherejpql.append(" and "+propertyName+"=?").append(queryParams.size() + 1);
			queryParams.add("%" + value + "%");
		}else {
			throw new RuntimeException(this.entityClass.getName() + ":传入的实体属性："+propertyName+"与值"+value+"不能为空");
		}
		QueryResult<T> queryResult = getScrollData(wherejpql.toString(), queryParams.toArray(),null);
		if (queryResult.getTotalrecord() > 0) {
			throw new RuntimeException("结果不唯一");
		}else {
			return queryResult.getResultlist().get(0);
		}
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public T findLast() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		return getScrollData(orderby).getResultlist().get(0);
	}

	// @Transactional(rollbackFor = { RuntimeException.class, Exception.class, Throwable.class })
	// @Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public void save(Object entity) {
		em.persist(entity);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public long getCount() {
		return (Long) em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + getEntityName(this.entityClass) + " o").getSingleResult();
	}

	// @Transactional(rollbackFor = { RuntimeException.class, Exception.class, Throwable.class })
	public void update(Object entity) {
		em.merge(entity);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(firstindex, maxresult, null, null, orderby);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		return getScrollData(firstindex, maxresult, wherejpql, queryParams, null);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult) {
		return getScrollData(firstindex, maxresult, null, null, null);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData() {
		return getScrollData(-1, -1);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(-1, -1, wherejpql, queryParams, orderby);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(LinkedHashMap<String, String> orderby) {
		return getScrollData(-1, -1, orderby);
	}

	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		QueryResult<T> qr = new QueryResult<T>();
		String entityname = getEntityName(this.entityClass);
		Query query = em.createQuery("select o from " + entityname + " o " + (wherejpql == null || "".equals(wherejpql.trim()) ? "" : "where " + wherejpql) + buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if(firstindex != -1 && maxresult != -1) {
			query.setFirstResult(firstindex).setMaxResults(maxresult);
		}
		qr.setResultlist(query.getResultList());
		query = em.createQuery("select count(" + getCountField(this.entityClass) + ") from " + entityname + " o " + (wherejpql == null || "".equals(wherejpql.trim()) ? "" : "where " + wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long) query.getSingleResult());
		return qr;
	}

	protected static void setQueryParams(Query query, Object[] queryParams) {
		if(queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i + 1, queryParams[i]);
			}
		}
	}

	/**
	 * 组装order by语句
	 * 
	 * @param orderby
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}

	/**
	 * 获取实体的名称
	 * 
	 * @param <E>
	 * @param clazz
	 *            实体类
	 */
	protected static <E> String getEntityName(Class<E> clazz) {
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if(entity.name() != null && !"".equals(entity.name())) {
			entityname = entity.name();
		}
		return entityname;
	}

	protected static <E> String getCountField(Class<E> clazz) {
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor propertydesc : propertyDescriptors) {
				Method method = propertydesc.getReadMethod();
				if(method != null && method.isAnnotationPresent(EmbeddedId.class)) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o." + propertydesc.getName() + "." + (!ps[1].getName().equals("class") ? ps[1].getName() : ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
