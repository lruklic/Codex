package models.questions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import models.Admin;
import models.Question;
import models.enums.Grade;
import models.enums.QuestionType;
import models.enums.Subject;

/**
 * Question with multiple options.
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "connect_correct")
@PrimaryKeyJoinColumn(name="id")
public class ConnectCorrectQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Map that contains term from one column with the term from the second column.
	 */
	@ElementCollection
	public Map<String, String> answerPairs;		// instead of map, how about a custom AnswerPairs class?

	public ConnectCorrectQuestion() {
	}
	
	public ConnectCorrectQuestion(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, String specialTags, int difficulty, String explanation, Admin admin, Map<String, String> answerPairs) {
		
		super(questionText, questionType, grade, subject, chapters, subjectContent, specialTags, difficulty, explanation, admin);
		this.answerPairs = answerPairs;
	}
	
	public List<String> getAllPairKeys() {
		List<String> pairKeys = new ArrayList<>();
		pairKeys.addAll(answerPairs.keySet());
		
		for (Iterator<String> iter = pairKeys.listIterator(); iter.hasNext(); ) {
		    String key = iter.next();
		    if (key.startsWith("EMPTY_STRING")) {
		        iter.remove();
		    }
		}
		
		return pairKeys;
	}
	
	public List<String> getAllPairValues() {
		List<String> pairValues = new ArrayList<>();
		pairValues.addAll(answerPairs.values());
		return pairValues;
	}

	public Map<String, String> getAnswerPairs() {
		return answerPairs;
	}
	
}
