package org.kyerp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.kyerp.dao.GenericDAO;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDAOImpl<T> extends JpaDaoSupport implements GenericDAO<T> {
	private final Class<T> clz;

	public GenericDAOImpl(Class<T> clz) {
		this.clz = clz;
	}

	@Override
	public T find(Long id) {
		return this.getJpaTemplate().find(clz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getByName(final String propertyName, final Object value) {
		List<T> ret = (List<T>) this.getJpaTemplate().execute(
				new JpaCallback() {
					public Object doInJpa(EntityManager em)
							throws PersistenceException {
						StringBuffer sb = new StringBuffer(
								"select object form ");
						sb.append(clz.getName());
						sb.append("obj").append("where").append("obj.").append(
								propertyName).append("=:value");
						Query query = em.createQuery(sb.toString());
						query.setParameter("value", value);
						return query.getResultList();
					}
				});
		if (ret != null && ret.size() == 1) {
			return ret.get(0);
		} else if (ret != null && ret.size() > 1) {
			throw new java.lang.IllegalStateException("返回多个对象");
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(final String sql, final Object[] paras,
			final int begin, final int count) {
		List<T> list = new ArrayList<T>();
		list = (List<T>) this.getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				StringBuffer str = new StringBuffer("select obj from ");
				str.append(clz.getName() + " obj where ");
				str.append(sql);
				Query query = em.createQuery(str.toString());
				if (begin >= 0 && count >= 0) {
					query.setFirstResult(begin);
					query.setMaxResults(count);
				}
				int index = 1;
				if (paras != null && paras.length > 0) {
					for (Object para : paras) {
						query.setParameter(index, para);
						index++;
					}
				}
				return query.getResultList();
			}
		});
		return list;
	}

	public void remove(T t) {
		this.getJpaTemplate().remove(this.getJpaTemplate().merge(t));
	}

	@Override
	public void save(T t) {
		this.getJpaTemplate().persist(t);
	}

	@Override
	public void update(T t) {
		this.getJpaTemplate().merge(t);
	}
}
