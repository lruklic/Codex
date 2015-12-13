package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import services.EmailService;
import services.PortService;
import services.model.UserService;
import session.Session;
import views.html.profile.profile;

import java.io.File;

import com.google.inject.Inject;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.User;

/**
 * Controller that handles question import and export.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class PortController extends Controller {

	@Inject
	public static UserService userService;

	@Inject
	public static EmailService emailService;
	
	@Inject
	public static PortService portService;
	
	@Restrict(@Group("ADMIN"))
	public static Result portHome() {
		
		User currentUser = userService.findByUsernameOrEmail(Session.getUsername());
		
		return ok(profile.render(currentUser));
	}
	
	public static Result importQuestions() {
		
		// Get uploaded file
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart file = body.getFile("import-input");
		
		portService.importQuestions(file.getFile());
		
		return TODO;
		
	}

}
