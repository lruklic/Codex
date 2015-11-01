package controllers;

import java.util.Date;

import models.ActivationLink;
import models.Admin;
import models.enums.UserType;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import security.PasswordHash;
import services.impl.EmailServiceImpl;
import services.model.UserService;
import views.html.register.register;

import com.google.inject.Inject;

import forms.RegisterForm;

/**
 * Controller that handles user registration.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class RegisterController extends Controller {
	
	@Inject
	public static UserService userService;
	
	@Inject
	public static ActivationLink activationLink;

	public static Result register() {
		return ok(register.render(Form.form(RegisterForm.class)));
	}
	
	public static Result submit() {
		Form<RegisterForm> registerForm = Form.form(RegisterForm.class).bindFromRequest();
		
		// Validation
		if (registerForm.hasErrors()) {
			// validate email, validate passwords
			return badRequest(register.render(registerForm));
		}
		
		RegisterForm rf = registerForm.get();
		
		if (userService.findByUsername(rf.username) != null || userService.findByEmail(rf.email) != null) {
			// flash username or email already exists
			return badRequest(register.render(registerForm));
		}
		
		// If user registered as admin, account is created, but SU must activate it manually
		// TODO add preferences field? cv field?
		if (rf.userType.equals(UserType.ADMIN)) {
			Admin admin = new Admin(rf.username, PasswordHash.createHash(rf.password), rf.firstName, rf.lastName, rf.email, new Date(System.currentTimeMillis()));
			userService.save(admin);
			flash("success", Messages.get("register.admin.success"));
			return redirect(controllers.routes.LoginController.login());
		} else if (rf.userType.equals(UserType.PLAYER)) {
			EmailServiceImpl esi = new EmailServiceImpl();
			esi.sendEmail("");
			
//			Player player = new Player(rf.username, PasswordHash.createHash(rf.password), rf.firstName, rf.lastName, rf.email, new Date(System.currentTimeMillis()));
//			userService.save(player);
//			flash("success", Messages.get("register.admin.success"));
//			
//			ActivationLink activationLink = new ActivationLink(UUID.randomUUID().toString(), player);
			
			
		}
		
		return ok();
	}

}
