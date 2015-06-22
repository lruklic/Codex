package services;

import models.User;

public interface AuthenticationService {
	
	public User authenticate(String username, String password);
	
}
