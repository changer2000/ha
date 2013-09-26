package com.etech.system.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class BaseDao<T> {
	
	private Class<T> entityClass;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.entityClass = (Class) params[0];
	}
	
	public T load(Serializable pk) {
		return (T) getHibernateTemplate().load(entityClass, pk);
	}
	
	public T get(Serializable pk) {
		return (T) getHibernateTemplate().get(entityClass, pk);
	}
	
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}
	
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	public List find(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	public List find(String hql, Object... params) {
		return getHibernateTemplate().find(hql, params);
	}
	
	public T findPeer(String hql, Object... params) {
		List list = getHibernateTemplate().find(hql, params);
		if (list!=null && list.size()>0) {
			return (T) list.get(0);
		}
		return null;
	}
	
	public Query createQuery(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		for (int i=0; i<params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query;
	}
	
	public Query createSQLQuery(String sql, Object... params) {
		Query query = getSession().createSQLQuery(sql);
		for (int i=0; i<params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query;
	}

	/**
	 * 对延迟加载的实体PO执行初始化
	 * @param entity
	 */
	public void initialize(Object entity) {
		getHibernateTemplate().initialize(entity);
	}
	
	//TODO 没有加入关于分页的代码，以后需要时，再补充
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public Session getSession() {
		return SessionFactoryUtils.getSession(hibernateTemplate.getSessionFactory(), true);
	}
}
