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
 * Question with multiple options.
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "multiple_choice")
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
	
	/**
	 * Method that returns all answers, shuffled. Used for answer display in HTML.
	 * 
	 * @return shuffled list of answers, both correct and incorrect
	 */
	public List<String> getAllAnswersMixed() {
		List<String> allAnswers = new ArrayList<>();
		allAnswers = getIncorrectAnswers();
		allAnswers.add(correctAnswer);
		
		long seed = System.nanoTime();
		
		Collections.shuffle(allAnswers, new Random(seed));
		
		return allAnswers;
	}
	
	public int getNumberOfAnswers() {
		return getIncorrectAnswers().size() + 1;
	}

	
}
