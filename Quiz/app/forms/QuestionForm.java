package forms;

import java.util.List;

import models.Admin;
import models.Question;
import models.enums.Grade;
import models.enums.QuestionType;
import models.enums.Subject;
import play.data.validation.Constraints.Required;

/**
 * Form that stores question fields from user input and contains methods that create <code>Question</code> 
 * derivative instance from question fields.
 * 
 * @author Luka Ruklic
 *
 */

public class QuestionForm {
	
	@Required
	public String questionText;
	
	@Required
	public QuestionType questionType;
	
	@Required
	public Grade grade;
	
	@Required
	public Subject subject;
	
	public String subjectContent;
	
	@Required
	public int difficulty;
	
	public int numberOfAnswers;
	
	public String correctAnswer; 
	
	public List<String>	chapters;
	
	public List<String> incorrectAnswers;
	
	public void setIncorrectAnswers(String ... incorrectAnswersForm) {
		for (String incorrect : incorrectAnswersForm) {
			incorrectAnswers.add(incorrect);
		}
	}
	
	public Question createQuestion(Admin admin) {
		Question question = null;
		
		switch(questionType) {
			case MULTIPLE_CHOICE:
				 
		}
		question = new Question(questionText, questionType, grade, subject, chapters, subjectContent, difficulty, admin);
		
		
		
		return question;
	}

}
