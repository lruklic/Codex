package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Grade;
import models.Question;
import models.Subject;
import play.data.Form;
import play.db.jpa.Transactional;
import play.i18n.Messages;
import play.mvc.BodyParser;
import play.mvc.BodyParser.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import quiz.QuizResult;
import quiz.evaluate.QuestionEvaluator;
import services.model.NoveltyService;
import services.model.QuestionService;
import session.Session;
import views.html.quiz.quiz_home;
import views.html.quiz.quiz_result;
import views.html.quiz.quiz_start;
import cache.models.ModelCache;
import cache.models.ModelCacheType;
import cache.question.QuestionCache;
import cache.question.QuestionSet;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import forms.QuizForm;
 
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
		return ok(quiz_home.render(ModelCache.getInstance().getAllGrades(), ModelCache.getInstance().getAllSubjects(), ModelCache.getInstance().getAllChapters()));
	}

	public static Result startQuiz() {
		
		Form<QuizForm> quizForm = Form.form(QuizForm.class).bindFromRequest();
		
		if (quizForm.hasErrors()) {
			flash("error", Messages.get("quiz.error.values"));
			return ok();	// TODO redirect
		}
		
		Grade grade = (Grade) ModelCache.getInstance().getSet(ModelCacheType.GRADE, quizForm.get().grade);
		Subject subject = (Subject) ModelCache.getInstance().getSet(ModelCacheType.SUBJECT, quizForm.get().subject);
		
		List<String> chapters = new ArrayList<>();
		if (quizForm.get().chapters != null) {
			for (String chapter : quizForm.get().chapters) { 
				chapters.add(chapter.split("-")[2]);
			}
		}
		
		QuizForm qf = quizForm.get();
		
		long questionsForSubject = questionService.countQuestions(grade, subject, chapters);
		
		// If there is not enough for the given subject and/or chapter, show maximum available
		// If there are no questions, redirect to start page 
		if(questionsForSubject >= qf.numberOfQuestions) {
			questionsForSubject = qf.numberOfQuestions;
		} else if (questionsForSubject == 0) {
			return redirect(routes.StartController.redirect());
		}
		
		List<Question> questionList = questionService.getQuestions(grade, subject, chapters);
		
		List<Question> ql = getNRandomQuestions(questionList, (int) questionsForSubject);
		
		QuestionCache.getInstance().addSet(Session.getUsername(), new QuestionSet(ql));
		
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
	
	/**
	 * Returns list with specified number of random questions from question list.
	 * 
	 * @param questionList input question list
	 * @param numberOfQuestions number of questions that will be randomly chosen from input list
	 * @return output list with specified number of questions picked randomly from input list
	 */
	private static List<Question> getNRandomQuestions(List<Question> questionList, int numberOfQuestions) {
		Collections.shuffle(questionList);
		List<Question> picked = questionList.subList(0, numberOfQuestions);
		return picked;
	}

}
