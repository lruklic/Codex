package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import models.enums.UserType;

/**
 * User entity, superclass for Player and Admin entities.
 *
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "user")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class User extends BaseModel implements Subject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username", unique = true)
	public String username;

	@Column(name = "password_hash")
	public String passwordHash;

	@Column(name = "first_name")
	public String firstName;

	@Column(name = "last_name")
	public String lastName;

	@Column(name = "email", unique = true)
	public String email;

	@Enumerated(EnumType.STRING)
	public UserType userType;
	
	protected User() {
	}
	
	public User(String username, String passwordHash, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	@Override
	public String getIdentifier() {
		return username;
	}

	@Override
	public List<? extends Role> getRoles() {
		List<Role> roles = new ArrayList<>();
		roles.add(userType);
		
		return roles;
	}
	
	@Override
	public List<? extends Permission> getPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

}
