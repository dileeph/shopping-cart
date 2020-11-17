package org.ejb.session.model;

import org.ejb.session.entity.User;
import org.ejb.session.model.core.BaseDao;

import javax.ejb.Remote;

/**
 * This remote session bean is a DAO class describing User entity access
 * methods.
 * <p>
 * 
 *
 */
@Remote
public interface UserDao extends BaseDao<User> {

}
