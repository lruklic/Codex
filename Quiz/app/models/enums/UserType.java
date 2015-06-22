package models.enums;

import java.util.List;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;

/**
 * Enum that defines two types of web app users: players and admins.
 * Also acts as a role for Deadbolt authentication.
 * 
 * @author Luka Ruklic
 *
 */

public enum UserType implements Role, Subject {
	/**
	 * Player.
	 */
	PLAYER("PLAYER"),
	/**
	 * Administrator.
	 */
	ADMIN("ADMIN");
	
	/**
	 * Enum value.
	 */
	private String value;
	
	/**
	 * Private constructor.
	 * @param value enum value
	 */
	private UserType(String value) {
		this.value = value;
	}
	
	@Override
	public String getName() {
		return this.value;
	}

	@Override
	public String getIdentifier() {
		return this.value;
	}

	@Override
	public List<? extends Permission> getPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
}
