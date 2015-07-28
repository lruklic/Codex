package cache.question;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.Question;

/**
 * Set of questions for single quiz.
 * 
 * @author Luka Ruklic
 *
 */

public class QuestionSet {
	
	/**
	 * Question set id.
	 */
	public long id;
	
	/**
	 * Map with questions with question id as key.
	 */
	public Map<Long, Question> questionMap = new HashMap<Long, Question>();
	
	/**
	 * Constructor that receives collection of question and saves them to map with id as key.
	 * 
	 * @param questionCollection collection of questions
	 */
	public QuestionSet(Collection<Question> questionCollection) {
		for (Question question : questionCollection) {
			questionMap.put(question.id, question);
		}
	}
	
	public Question getQuestion(Long id) {
		return questionMap.get(id);
	}
}
