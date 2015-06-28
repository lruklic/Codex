package controllers;

import forms.QuestionForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin_question;

/**
 * Controller that is called when user attempts to access /, top application domain (i.e. localhost:9000/).
 * It redirect him to login if no session attributes are present or to corresponding site if user is 
 * already logged in.
 * 
 * @author Luka Ruklic
 *
 */

public class StartController extends Controller {
	
	public static Result redirect() {
		
		String email = session("email");
		String type = session("type");
		
		if(type == null) {
			return redirect(controllers.routes.LoginController.login());
		} else {
			switch(type) {
			case "ADMIN":
				return ok(admin_question.render(Form.form(QuestionForm.class)));	// TODO send to admin home
			}
		}
		
		return ok();
		
	}
	
}
