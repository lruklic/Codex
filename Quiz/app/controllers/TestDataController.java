package controllers;

import models.Admin;
import models.enums.UserType;

import com.google.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.UserService;

@Transactional
public class TestDataController extends Controller {

	@Inject
	private static UserService userService;
	
	public static Result fill() {
		
		// Test admin data
		Admin admin = new Admin();
		
		admin.firstName = "Luka";
		admin.lastName = "Ruklić";
		admin.username = "lruklic";
		admin.passwordHash = "81dc9bdb52d04dc20036dbd8313ed055";
		admin.email = "ruklic.luka@gmail.com";
		admin.userType = UserType.ADMIN;
		admin.questionsAdded = 0;
		
		userService.save(admin);
		
		admin = new Admin();
		admin.firstName = "Kruno";
		admin.lastName = "Kolak";
		admin.username = "kkolak";
		admin.passwordHash = "81dc9bdb52d04dc20036dbd8313ed055";
		admin.email = "kolak.kruno@gmail.com";
		admin.userType = UserType.ADMIN;
		admin.questionsAdded = 0;
		
		userService.save(admin);
		
		return ok();
	}
	
}
