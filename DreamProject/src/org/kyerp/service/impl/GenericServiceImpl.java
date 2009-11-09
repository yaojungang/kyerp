package org.kyerp.service.impl;

import org.kyerp.dao.impl.GenericDAOImpl;
import org.kyerp.service.GenericService;

public class GenericServiceImpl<T> extends GenericDAOImpl<T> implements
		GenericService<T> {
	private final Class<T> clz;

	public GenericServiceImpl(Class<T> clz) {
		super(clz);
		this.clz = clz;
	}

	public Class<T> getClz() {
		return clz;
	}
}
