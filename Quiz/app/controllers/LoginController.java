package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.User;
import models.enums.UserType;

import com.google.inject.Inject;

import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.UserService;
import views.html.login;
import enums.AuthReply;
import forms.LoginForm;

/**
 * Controller that handles login attempts.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class LoginController extends Controller {

	@Inject
	public static UserService userService;

	/**
	 * Login method that renders form for user login. Mapped under GET in <i>routes</i>.
	 * 
	 * @return rendered login.scala.html view
	 */
	public static Result login() {
		return ok(login.render(Form.form(LoginForm.class)));
	}
	
	/**
	 * Logout method that clears current session with current user info. Mapped under GET in <i>routes</i>.
	 * 
	 * @return rendered login.scala.html view
	 */
	public static Result logout() {
		session().clear();
		return ok(login.render(Form.form(LoginForm.class)));
	}

	/**
	 * Authenticate method that checks user credentials after login attempt. Mapped under POST in <i>routes</i>.
	 * 
	 * @return login form is credentials are incorrect, user form otherwise
	 */
	public static Result authenticate() {
		Form<LoginForm> loginForm = Form.form(LoginForm.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			flash("failure", Messages.get("login.error.values"));
			return ok(login.render(Form.form(LoginForm.class)));
		} else {
			User user = userService.findByUsernameOrEmail(loginForm.get().usernameOrEmail);
			AuthReply passwordOk = passwordOk(user, loginForm.get().password);

			switch (passwordOk) {
			case LOGIN_OK:
				session().clear();
				session("credential", loginForm.get().usernameOrEmail);
				session("firstName", user.firstName);
				session("type", user.userType.toString());
				if (user.userType.equals(UserType.ADMIN)) {
					return redirect(controllers.routes.AdminController.adminHome());
				} else {
					// else user.render();
				}
				
			case NO_USER:
				flash("failure", Messages.get("login.error.username"));
				break;
			case WRONG_PASSWORD:
				flash("failure", Messages.get("login.error.password"));
				break;
			}

			return badRequest(login.render(Form.form(LoginForm.class)));

		}
	}

	/**
	 * Password that checks if provided username exists and if <code>inputPassword</code> matches provided password.
	 * 
	 * @param inputUsername
	 *            username input from form
	 * @param inputPassword
	 *            password input from form
	 * @return AuthReply that describes if authentication credentials were correct
	 */
	public static AuthReply passwordOk(User user, String inputPassword) {

		if (user == null) {
			return AuthReply.NO_USER;
		} else {
			String inputPasswordHash = createPasswordHash(inputPassword);

			String correctPasswordHash = user.passwordHash;

			if (inputPasswordHash.equals(correctPasswordHash)) {
				return AuthReply.LOGIN_OK;
			}
		}

		return AuthReply.WRONG_PASSWORD;
	}

	/**
	 * Method that creates password hash using predefined algorithm.
	 * 
	 * @param password
	 *            password string that will be turned into hash
	 * @return password hash
	 */
	public static String createPasswordHash(String password) {

		String generatedPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());

			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return generatedPassword;
	}

}
