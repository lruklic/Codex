package controllers;

import com.google.inject.Inject;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.NoveltyService;
import services.model.QuestionService;
import views.html.admin_question;
import views.html.admin_home;
import views.html.admin_questionlist;
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
	
	@Inject
	public static NoveltyService noveltyService;
	
	public static Result adminList() {
		return ok(admin_questionlist.render(questionService.findAll()));
	}
	
	/**
	 * Login method that renders form for user login. Mapped under GET in <i>routes</i>.
	 * 
	 * @return rendered login.scala.html view
	 */
	public static Result adminQuestion() {
		return ok(admin_question.render(Form.form(QuestionForm.class)));
	}
	
	/**
	 * Login method that renders html for admin home page. Mapped under GET in <i>routes</i>
	 * @return rendered admin_home.scala.html view
	 */
	public static Result adminHome() {
		String clearanceString = session().get("clearance");
		Integer clearance = 0;
		try {
			clearance = Integer.parseInt(clearanceString);
		} catch (NumberFormatException ex) {
			// no session clearance
		}
		return ok(admin_home.render(clearance, noveltyService.findAll())); // TODO instead of findAll 
	}

}
