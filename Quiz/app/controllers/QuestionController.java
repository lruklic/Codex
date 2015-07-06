package controllers;

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
import views.html.admin_home;
import views.html.admin_question;
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
	
	/**
	 * Mapped to POST method under adminQuestion
	 * @return
	 */
	
	public static Result submit() {
		Form<QuestionForm> questionForm = Form.form(QuestionForm.class).bindFromRequest();
		
		User user = getCurrentUser();
		
		if(questionForm.hasErrors()) {
			return ok(admin_home.render(session().get("firstName"), questionService.findAll()));
		}
		
		Question question = null;
		if (user instanceof Admin) {
			 question = questionForm.get().createQuestion((Admin) user);	// if there are any errors, .get() will throw IllegalStateException: no value
			 question.lastEdited = System.currentTimeMillis();
		}
		if (question.id != null) {
			questionService.delete(questionService.findById(question.id));
		}
		questionService.save(question);

		return ok(admin_home.render(user.firstName, questionService.findAll())); 
	}

	
	public static Result edit(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		User user = getCurrentUser();
		
		Question question = questionService.findById(id);
		
		QuestionForm qf = new QuestionForm();
		qf.fillForm(question);
		
		Form<QuestionForm> form = Form.form(QuestionForm.class).fill(qf);
		
		return ok(admin_question.render(form, user.firstName, question.id));
		
	}
	
	public static Result delete(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		User user = getCurrentUser();
		
		Question question = questionService.findById(id);
		
		if (question == null) {
			return ok(admin_home.render(user.firstName, questionService.findAll()));
		}
		
		questionService.delete(question);
		
		return ok(admin_home.render(user.firstName, questionService.findAll()));
		
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
	
	public static Result getChapters(String grade, String subject) {
		JsonNode json = Json.toJson(Chapter.getByGradeAndSubject(Grade.valueOf(grade), Subject.valueOf(subject)));
		return ok();
	}
	
}
