package controllers;

import java.util.List;

import models.Question;
import cache.question.QuestionCache;
import cache.question.QuestionSet;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

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
		
		List<Question> ql = questionService.findAll();
		
		QuestionCache.getInstance().addSet(Session.getUsername(), new QuestionSet(ql));;
		
		return ok(quiz_start.render(questionService.findAll()));
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

}
