package controllers;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin;
import forms.QuestionForm;

/**
 * Controller that handles admin page.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class AdminController extends Controller {

	/**
	 * Login method that renders form for user login. Mapped under GET in <i>routes</i>.
	 * 
	 * @return rendered login.scala.html view
	 */
	public static Result adminPage() {
		return ok(admin.render(Form.form(QuestionForm.class)));
	}

}
