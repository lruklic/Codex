package models.questions;

import java.util.ArrayList;
import java.util.List;

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
 * Question with multiple options.
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "multiplechoicequestion")
@PrimaryKeyJoinColumn(name="id")
public class MultipleChoiceQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "correctAnswer")
	public String correctAnswer;
	
	@Column(name = "incorrectAnswers")
	public String incorrectAnswers;

	public MultipleChoiceQuestion() {
	}
	
	public MultipleChoiceQuestion(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, int difficulty, Admin admin, String correctAnswer, String incorrectAnswers) {
		
		super(questionText, questionType, grade, subject, chapters, subjectContent, difficulty, admin);
		this.correctAnswer = correctAnswer;
		this.incorrectAnswers = incorrectAnswers;
	}
	
	public List<String> getIncorrectAnswers() {
		List<String> incorrectAnswersList = new ArrayList<String>();
		String[] incorrectAnswerArray = incorrectAnswers.split(";");
		for (String incorrectAnswer : incorrectAnswerArray) {
			incorrectAnswersList.add(incorrectAnswer);
		}
		
		return incorrectAnswersList;
	}
	
	public int getNumberOfAnswers() {
		return getIncorrectAnswers().size() + 1;
	}

	
}
