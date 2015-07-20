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
@Table(name = "multiple_answer")
@PrimaryKeyJoinColumn(name="id")
public class MultipleAnswerQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "correctAnswer")
	public String correctAnswers;
	
	@Column(name = "incorrectAnswers")
	public String incorrectAnswers;

	public MultipleAnswerQuestion() {
	}
	
	public MultipleAnswerQuestion(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, int difficulty, Admin admin, String correctAnswers, String incorrectAnswers) {
		
		super(questionText, questionType, grade, subject, chapters, subjectContent, difficulty, admin);
		this.correctAnswers = correctAnswers;
		this.incorrectAnswers = incorrectAnswers;
	}
	
	public List<String> getCorrectAnswers() {
		return getAnswers(correctAnswers);
	}
	
	public List<String> getIncorrectAnswers() {
		return getAnswers(incorrectAnswers);
	}
	
	private List<String> getAnswers(String list) {
		List<String> answersList = new ArrayList<String>();
		
		if (list.length() > 0) {
			String[] answerArray = list.split(";");
			for (String answer : answerArray) {
				answersList.add(answer);
			}
		}
		
		return answersList;
	}
	
	public int getNumberOfAnswers() {
		return getIncorrectAnswers().size() + getCorrectAnswers().size();
	}

	
}
