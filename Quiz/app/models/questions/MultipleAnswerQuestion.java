package models.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
 * Question with multiple answers and random number of correct ones.
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
	
	/**
	 * String containing correct answers, separated with semicolon.
	 */
	@Column(name = "correctAnswer")
	public String correctAnswers;
	/**
	 * String containing incorrect answers, separated with semicolon.
	 */
	@Column(name = "incorrectAnswers")
	public String incorrectAnswers;

	public MultipleAnswerQuestion() {
	}
	
	public MultipleAnswerQuestion(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, String specialTags, int difficulty, String explanation, Admin admin, String correctAnswers, String incorrectAnswers) {
		
		super(questionText, questionType, grade, subject, chapters, subjectContent, specialTags, difficulty, explanation, admin);
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
	
	/**
	 * Method that returns all answers shuffled.
	 * 
	 * @return list with shuffled answers, both correct and incorrect
	 */
	
	public List<String> getAllAnswersMixed() {
		List<String> allAnswers = new ArrayList<String>(getCorrectAnswers());
		allAnswers.addAll(getIncorrectAnswers());
		
		long seed = System.nanoTime();
		Collections.shuffle(allAnswers, new Random(seed));
		
		return allAnswers;
	}
	
	public int getNumberOfAnswers() {
		return getIncorrectAnswers().size() + getCorrectAnswers().size();
	}

	
}
