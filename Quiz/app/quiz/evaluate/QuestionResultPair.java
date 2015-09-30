package quiz.evaluate;

import enums.AnswerType;
import models.Question;

import org.apache.commons.lang3.StringEscapeUtils;

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
	 *  
	 * @param question
	 * @param isCorrect
	 */
	public QuestionResultPair(Question question, AnswerType isCorrect) {
		this.question = question;
		this.isCorrect = isCorrect;
		this.answerRecap = new String();
	}
	
	public void createAnswerRecap() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html> ");
		sb.append("<div><b>"+Messages.get("answer.correct")+":</b> " + question.questionText + "</div>");		// TODO escaping!
		sb.append("<br>");
		sb.append("<div>" + question.explanation + "</div>");		// TODO escaping!
		sb.append("</html>");
		
		answerRecap = sb.toString();
	}
	
}
