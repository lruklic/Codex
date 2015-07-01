package controllers;

import com.google.inject.Inject;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.QuestionService;
import views.html.admin_question;
import views.html.admin_home;
import forms.QuestionForm;

/**
 * Controller that handles admin page.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class AdminController extends Controller {

	@Inject
	public static QuestionService questionService;
	
	public static Result adminHome() {
		return ok(admin_home.render(session().get("firstName"), questionService.findAll()));
	}
	
	/**
	 * Login method that renders form for user login. Mapped under GET in <i>routes</i>.
	 * 
	 * @return rendered login.scala.html view
	 */
	public static Result adminQuestion() {
		return ok(admin_question.render(Form.form(QuestionForm.class), session().get("firstName"), null));
	}

}
