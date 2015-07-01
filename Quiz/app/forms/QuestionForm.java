package forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import factories.QuestionFactory;
import models.Admin;
import models.Question;
import models.enums.Grade;
import models.enums.QuestionType;
import models.enums.Subject;
import models.questions.InputAnswerQuestion;
import models.questions.MultipleChoiceQuestion;
import models.questions.TrueFalseQuestion;
import play.data.validation.Constraints.Required;

/**
 * Form that stores question fields from user input and contains methods that create <code>Question</code> 
 * derivative instance from question fields.
 * 
 * @author Luka Ruklic
 *
 */

public class QuestionForm {
	
	public String id;
	
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
	
	public String multipleCorrect;
	
	public String inputCorrect;
	
	public List<String>	chapters;
	
	public List<String> incorrect;
	
	public String trueFalse;
	
	public Question createQuestion(Admin admin) {
		
		Question question = QuestionFactory.createQuestion(this, admin);
		
		// TODO add try-catch?
		if(id != null && !id.equals("/")) {
			question.id = Long.parseLong(id);
		}

		
		return question;
	}
	
	public void fillForm(Question question) {
		
		this.questionText = question.questionText;
		this.questionType = question.questionType;
		this.grade = question.grade;
		this.subject = question.subject;
		this.subjectContent = question.subjectContent;
		this.difficulty = question.difficulty;
		
		if (question.chapters != null) {
			List<String> chaptersList = Arrays.asList(question.chapters.split(";"));
			this.chapters = chaptersList;
		}

		switch(questionType) {
			case MULTIPLE_CHOICE:
				break;
			case YES_NO:
				if (((TrueFalseQuestion) question).answer) {
					trueFalse = "on";
				} else {
					trueFalse = "off";
				}
				break;
			case INPUT_ANSWER:
				this.inputCorrect = ((InputAnswerQuestion) question).answer;
				break;
		}
		
		
	}

}
