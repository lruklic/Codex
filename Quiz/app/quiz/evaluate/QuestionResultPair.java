package quiz.evaluate;

import enums.AnswerType;
import models.Question;

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
	 * Constructor. 
	 * @param question
	 * @param isCorrect
	 */
	public QuestionResultPair(Question question, AnswerType isCorrect) {
		this.question = question;
		this.isCorrect = isCorrect;
	}
	
	
}
