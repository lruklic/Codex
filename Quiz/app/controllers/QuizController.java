package controllers;

import com.google.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.NoveltyService;
import services.model.QuestionService;

import views.html.quiz.quiz_home;
import views.html.quiz.quiz_start;

/**
 * Controller that handles quiz page.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class QuizController extends Controller {

	@Inject
	public static QuestionService questionService;
	
	@Inject
	public static NoveltyService noveltyService;

	public static Result quizHome() {
		return ok(quiz_home.render());
	}
	
	public static Result startQuiz() {
		return ok(quiz_start.render(questionService.findAll()));
	}

}
