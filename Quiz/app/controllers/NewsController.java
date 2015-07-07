package controllers;

import models.User;

import com.google.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.QuestionService;
import services.model.UserService;

/**
 * Controller that handles operations over questions.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class NewsController extends Controller {

	@Inject
	public static UserService userService;
	
	@Inject
	public static QuestionService questionService;
	
	public static Result add() {
		
		return ok();
		
	}
	
	public static Result submit() {

		return ok(); 
	}
	
	public static Result delete(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		
		return ok();
		
	}
	
	/**
	 * Method that gets current user for session.
	 * @return current user
	 */
	private static User getCurrentUser() {
		String credential = session().get("credential");
		User user = userService.findByUsernameOrEmail(credential);
		return user;
	}
	
//	public static Result getChapters(String grade, String subject) {
//		// JsonNode json = Json.toJson(Chapter.getByGradeAndSubject(Grade.valueOf(grade), Subject.valueOf(subject)));
//		return ok();
//	}
	
}
