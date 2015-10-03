package quiz.evaluate;

import enums.AnswerType;
import models.Question;

import play.i18n.Messages;

 /**
 * Key value structure for question and evaluated answer provided by player.
 * 
 * @author Luka Ruklic
 *
 */

public class QuestionResultPair {
	
	/**
	 * Question asked.
	 */
	public Question question;
	
	public String givenAnswer;
	/**
	 * Correct if user answered correctly, incorrect otherwise. // TODO partially correct
	 */
	public AnswerType isCorrect;
	
	/**
	 * Structured HTML string containing correct answer, answer given by user and answer explanation if one is provided.
	 */
	public String answerRecap;
	
	/**
	 * Constructor.
	 * @param question given question
	 */
	public QuestionResultPair(Question question) {
		this.question = question;
	}

	/**
	 * Creates answer recap and saves it to answerRecap field with provided answer, correct answer and explanation if one is provided.
	 */
	public void createAnswerRecap() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html> ");
		sb.append("<div><b>"+Messages.get("question.text")+":</b> " + question.questionText + "</div>");		// TODO escaping!
		sb.append("<br>");
		sb.append("<div><b>"+Messages.get("answer.given")+":</b> " + givenAnswer + "</div>");
		sb.append("<br>");
		sb.append("<div><b>"+Messages.get("answer.correct")+":</b> " + question.getQuestionAnswerText() + "</div>");
		sb.append("<br>");
		if (question.explanation.length() > 0) {
			sb.append("<div><b>"+Messages.get("answer.explanation")+":</b>" + question.explanation + "</div>");		// TODO escaping!
		}
		sb.append("</html>");
		
		answerRecap = sb.toString();
	}

	public String getGivenAnswer() {
		return givenAnswer;
	}

	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}

	public AnswerType isCorrect() {
		return isCorrect;
	}

	public void setCorrect(AnswerType isCorrect) {
		this.isCorrect = isCorrect;
	}

	
	
	
}
