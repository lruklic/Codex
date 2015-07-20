package models.questions;

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
	public Map<String, String> answerPairs;

	public ConnectCorrectQuestion() {
	}
	
	public ConnectCorrectQuestion(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, int difficulty, Admin admin, Map<String, String> answerPairs) {
		
		super(questionText, questionType, grade, subject, chapters, subjectContent, difficulty, admin);
		this.answerPairs = answerPairs;
	}
	
}
