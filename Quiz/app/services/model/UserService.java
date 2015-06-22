package services.model;

import models.User;

/**
 * Interface that defines methods for CRUD (Create, Read, Update, Delete) operations on user data in database.
 * 
 * @author Luka Ruklic
 *
 */

public interface UserService extends BaseModelService<User> {

	public User findByEmail(String email);
	
	public User findByUsername(String username);
	
	public User findByUsernameOrPassword(String credential);
}
