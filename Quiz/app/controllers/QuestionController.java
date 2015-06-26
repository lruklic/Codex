package controllers;

import com.google.inject.Inject;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.UserService;
import forms.LoginForm;

/**
 * Controller that handles operations over questions.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class QuestionController extends Controller {

	@Inject
	public static UserService userService;

	public static Result submit() {
		Form<LoginForm> loginForm = Form.form(LoginForm.class).bindFromRequest();
		return TODO;
	}

}
