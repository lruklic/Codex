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
 * Question
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "input_answer")
@PrimaryKeyJoinColumn(name="id")
public class InputAnswerQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "answer")
	public String answer;

	public InputAnswerQuestion() {
	}
	
	public InputAnswerQuestion(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, int difficulty, Admin admin, String answer) {
		
		super(questionText, questionType, grade, subject, chapters, subjectContent, difficulty, admin);
		this.answer = answer;
	}

	
}
