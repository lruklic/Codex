package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Question;
import models.enums.Subject;
import cache.question.QuestionCache;
import cache.question.QuestionSet;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import forms.LoginForm;
import forms.QuizForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.BodyParser.Json;
import play.mvc.Controller;
import play.mvc.BodyParser;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import quiz.QuizResult;
import quiz.evaluate.QuestionEvaluator;
import services.model.NoveltyService;
import services.model.QuestionService;
import session.Session;
import views.html.quiz.quiz_home;
import views.html.quiz.quiz_start;
import views.html.quiz.quiz_result;
 
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
		
		Form<QuizForm> quizForm = Form.form(QuizForm.class).bindFromRequest();
		Subject subject = quizForm.get().subject;
		
		List<Subject> subjects = new ArrayList<Subject>();
		subjects.add(subject);
		
		// TODO if there is not enough questions
		List<Question> ql = getNRandomQuestions(questionService.getQuestionsBySubjects(subjects), 10);	// set customizable number of questions
		
		QuestionCache.getInstance().addSet(Session.getUsername(), new QuestionSet(ql));; 
		return ok(quiz_start.render(ql));
	}
	 
	@BodyParser.Of(Json.class)
	public static Result evaluateQuiz() {
		// Get JSON array with questions
		RequestBody body = request().body();
		JsonNode js = body.asJson();
		
		// Get all questions from JSON as iterator
		List<JsonNode> jsList = Lists.newArrayList(js.elements());
		
		// Get current questions for current player
		QuestionSet questionSet = QuestionCache.getInstance().getSet(Session.getUsername());
		
		// Iterate over all questions
		QuestionEvaluator qe = new QuestionEvaluator();
		
		QuizResult result = qe.evaulateQuiz(jsList, questionSet);
		
		QuestionCache.getInstance().removeSet(Session.getUsername());
		
		return ok(quiz_result.render(result));
	}
	
	private static List<Question> getNRandomQuestions(List<Question> questionList, int numberOfQuestions) {
		Collections.shuffle(questionList);
		List<Question> picked = questionList.subList(0, numberOfQuestions);
		return picked;
	}

}
