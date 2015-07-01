package models.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import models.Admin;
import models.Question;
import models.enums.Grade;
import models.enums.QuestionType;
import models.enums.Subject;

/**
 * Question with only two options (TRUE or FALSE). Correct answer is contained in <code>answer</code>
 * field, with 1 symbolizing TRUE and 0 symbolizing FALSE.
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "truefalsequestion")
@PrimaryKeyJoinColumn(name="id")
public class TrueFalseQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "answer")
	public  Boolean answer;

	public TrueFalseQuestion() {
	}
	
	public TrueFalseQuestion(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, int difficulty, Admin admin, Boolean answer) {
		
		super(questionText, questionType, grade, subject, chapters, subjectContent, difficulty, admin);
		this.answer = answer;
	}

	
}
