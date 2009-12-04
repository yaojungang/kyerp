package org.kyerp.service;

import org.kyerp.dao.DAO;
import org.kyerp.domain.org.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends DAO<User> {
}
