package org.kyerp.service.org.impl;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.org.Employee;
import org.kyerp.service.org.IEmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
public class EmployeeService extends DaoSupport<Employee> implements
		IEmployeeService {

}
