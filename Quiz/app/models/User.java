package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

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
public class User extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	public String username;

	@Column(name = "password_hash")
	public String passwordHash;

	@Column(name = "first_name")
	public String firstName;

	@Column(name = "last_name")
	public String lastName;

	@Column(name = "email")
	public String email;

	@Enumerated(EnumType.STRING)
	public UserType userType;

}
