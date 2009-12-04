/**
 * 
 */
package org.kyerp.service.impl;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.org.User;
import org.kyerp.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-12-4上午08:37:45
 */
@Service
public class UserService extends DaoSupport<User> implements IUserService {

}
