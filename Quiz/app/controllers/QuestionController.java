package controllers;

import java.util.List;

import models.Admin;
import models.Question;
import models.User;
import models.enums.Chapter;
import models.enums.Grade;
import models.enums.Subject;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.QuestionService;
import services.model.UserService;
import forms.QuestionForm;

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
	
	@Inject
	public static QuestionService questionService;

	public static Result submit() {
		Form<QuestionForm> questionForm = Form.form(QuestionForm.class).bindFromRequest();
		
		String email = session().get("email");
		User user = userService.findByEmail(email);
		if (user == null) {
			user = userService.findByUsername(email);
		}
		
		if(questionForm.hasErrors()) {
			// do something
		}
		
		Question question = null;
		if (user instanceof Admin) {
			 question = questionForm.get().createQuestion((Admin) user);	// if there are any errors, .get() will throw IllegalStateException: no value
		}
		
		// List<String> chapters = questionForm.get().chapters;

		return ok(); 
	}

	public static Result getChapters(String grade, String subject) {
		JsonNode json = Json.toJson(Chapter.getByGradeAndSubject(Grade.valueOf(grade), Subject.valueOf(subject)));
		return ok();
	}
	
}
