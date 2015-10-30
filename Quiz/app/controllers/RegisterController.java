package controllers;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.NoveltyService;
import services.model.QuestionService;
import views.html.register.register;

import com.google.inject.Inject;

import forms.RegisterForm;

/**
 * Controller that handles player page.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class RegisterController extends Controller {

	@Inject
	public static QuestionService questionService;
	
	@Inject
	public static NoveltyService noveltyService;

	public static Result register() {
		return ok(register.render(Form.form(RegisterForm.class)));
	}
	
	public static Result submit() {
		Form<RegisterForm> registerForm = Form.form(RegisterForm.class).bindFromRequest();
		
		// Validation
		if(registerForm.hasErrors()) {
			return badRequest(register.render(registerForm));	// TODO check for admin twice? refactor!
		}
		
		return ok();
	}

}
