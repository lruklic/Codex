package controllers;

import java.io.IOException;
import java.util.List;

import models.Admin;
import models.Question;
import models.User;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import com.google.inject.Inject;

import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.QuestionService;
import services.model.UserService;
import session.Session;
import views.html.admin_question;
import views.html.admin_questionlist;
import factories.export.ExportFactory;
import forms.QuestionForm;

/**
 * <p>Controller that handles operations over questions.</p>
 * 
 * <p>Question operations that this controller handles are following: 
 * submitting new, editing, deleting, exporting </p>
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
	
	private static ExportFactory exportFactory = new ExportFactory();
	
	/**
	 * Mapped to POST method under adminQuestion
	 * @return
	 */
	
	public static Result submit() {
		Form<QuestionForm> questionForm = Form.form(QuestionForm.class).bindFromRequest();
		
		String username = Session.getUsername();
		User user = userService.findByUsernameOrEmail(username);
		
		if(questionForm.hasErrors()) {
			return badRequest(admin_question.render(questionForm, ((Admin)user).subjectPermissions));	// TODO check for admin twice? refactor!
		}
		
		Question question = null;
		if (user instanceof Admin) {
			 question = questionForm.get().createQuestion((Admin) user);	// if there are any errors, .get() will throw IllegalStateException: no value
			 question.lastEdited = System.currentTimeMillis();
		}

		questionService.save(question);

		return redirect(routes.AdminController.adminList());
	}

	@Restrict(@Group("ADMIN"))
	public static Result edit(Long id) {
		
		// TODO don't allow editing for user that is not author of question
		
		Question question = questionService.findById(id);
		
		if (!question.author.username.equals(Session.getUsername())) {
			flash("error", Messages.get("error.unauthorized.edit"));
			// TODO error, not allowed to edit question
			return redirect(routes.AdminController.adminList());
		}
		
		QuestionForm qf = new QuestionForm();
		qf.fillForm(question);
		
		qf.id = String.valueOf(question.id);
		
		Form<QuestionForm> form = Form.form(QuestionForm.class).fill(qf);
		
		return ok(admin_question.render(form, question.author.subjectPermissions));
		
	}
	
	public static Result deleteQuestion(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		Question question = questionService.findById(id);
		
		if (question == null) {
			return ok(admin_questionlist.render(questionService.findAll()));
		}
		
		questionService.delete(question);
		
		return redirect(routes.AdminController.adminList());
		
	}
	
	@Restrict(@Group("ADMIN"))
	public static Result export(String exportType) throws IOException {
		
		byte[] output = null;
		
		List<Question> questionList = questionService.findQuestionsByAdmin(Session.getUsername());
		
		// return error if user has no questions
		
		if (exportType.equals("csv")) {
			output = exportFactory.exportAsCSV(questionList);
		} else if (exportType.equals("xls")) {
			output = exportFactory.exportAsXLS(questionList);
		} else {
			return TODO;
		}
		
		response().setContentType("application/x-download");
		response().setHeader("Content-disposition", "attachment; filename=questionList."+exportType);
		return ok(output);
				
	}
	
//	public static Result getChapters(String grade, String subject) {
//		// JsonNode json = Json.toJson(Chapter.getByGradeAndSubject(Grade.valueOf(grade), Subject.valueOf(subject)));
//		return ok();
//	}
	
}
