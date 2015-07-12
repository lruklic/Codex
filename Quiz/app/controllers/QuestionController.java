package controllers;

import models.Admin;
import models.Question;
import models.User;

import com.google.inject.Inject;

import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.QuestionService;
import services.model.UserService;
import views.html.admin_question;
import views.html.admin_questionlist;
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
			return badRequest(admin_question.render(questionForm));
		}
		
		Question question = null;
		if (user instanceof Admin) {
			 question = questionForm.get().createQuestion((Admin) user);	// if there are any errors, .get() will throw IllegalStateException: no value
			 question.lastEdited = System.currentTimeMillis();
		}
//		if (question.id != null) {
//			questionService.delete(questionService.findById(question.id));		// is this necessary?
//		}
		questionService.save(question);

		return redirect(routes.AdminController.adminList());
	}

	
	public static Result edit(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		User user = getCurrentUser();
		
		Question question = questionService.findById(id);
		
		QuestionForm qf = new QuestionForm();
		qf.fillForm(question);
		
		qf.id = String.valueOf(question.id);
		
		Form<QuestionForm> form = Form.form(QuestionForm.class).fill(qf);
		
		return ok(admin_question.render(form));
		
	}
	
	public static Result delete(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		User user = getCurrentUser();
		
		Question question = questionService.findById(id);
		
		if (question == null) {
			return ok(admin_questionlist.render(questionService.findAll()));
		}
		
		questionService.delete(question);
		
		return ok(admin_questionlist.render(questionService.findAll()));
		
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
