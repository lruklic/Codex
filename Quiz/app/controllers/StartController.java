package controllers;

import com.google.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.QuestionService;
import views.html.admin_home;

/**
 * Controller that is called when user attempts to access /, top application domain (i.e. localhost:9000/).
 * It redirect him to login if no session attributes are present or to corresponding site if user is 
 * already logged in.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class StartController extends Controller {
	
	@Inject
	public static QuestionService questionService;
	
	public static Result redirect() {
		
		String type = session("type");
		String firstName = session("firstName");
		
		if(type == null) {
			return redirect(controllers.routes.LoginController.login());
		} else {
			switch(type) {
			case "ADMIN":
				return ok(admin_home.render(firstName, questionService.findAll()));
			}
		}
		
		return ok();
		
	}
	
}
