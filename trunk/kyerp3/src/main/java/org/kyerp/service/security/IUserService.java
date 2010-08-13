package org.kyerp.service.security;

import org.kyerp.dao.DAO;
import org.kyerp.domain.security.User;
import org.springframework.stereotype.Service;

/**
 * @author y109 2009-11-30上午02:25:26
 */
@Service
public interface IUserService extends DAO<User>{
	public User getUserByName(String userName);
}
