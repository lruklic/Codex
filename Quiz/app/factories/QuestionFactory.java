package factories;

import java.util.List;

import forms.QuestionForm;
import models.Admin;
import models.Question;
import models.questions.InputAnswerQuestion;
import models.questions.MultipleChoiceQuestion;
import models.questions.TrueFalseQuestion;

/**
 * Factory for creating questions.
 * 
 * @author Luka Ruklic
 *
 */

public class QuestionFactory {

	/**
	 * Static method that creates question instance from data received in question form.
	 * 
	 * @param form form containing data for new question that is created
	 * @param currentAdmin administrator that is entering the question
	 * @return newly created question subclass
	 */
	
	public static Question createQuestion(QuestionForm form, Admin currentAdmin) {
		
		String chapters = createChapterString(form.chapters);
		
		switch(form.questionType) {
			case MULTIPLE_CHOICE:
				String incorrect = createIncorrectAnswers(form.incorrect);
				return new MultipleChoiceQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						form.difficulty, currentAdmin, form.multipleCorrect, incorrect);
			case YES_NO:
				Boolean trueFalse = false;
				if (form.trueFalse != null) {
					trueFalse = true;
				}
				return new TrueFalseQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						form.difficulty, currentAdmin, trueFalse);
			case INPUT_ANSWER:
				return new InputAnswerQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						form.difficulty, currentAdmin, form.inputCorrect);
		}
		
		return null;
	}
	
	/**
	 * Method that receives list with chapters from form, removes unnecessary grade and subject
	 * tags from every chapter string and concatenates that in one string.
	 * 
	 * @param chapters list with Grade-Subject-Chapter string triplets
	 * @return string with chapter string where chapters are separated with semicolon ;
	 */
	
	private static String createChapterString(List<String> chapters) {
		
		if (chapters != null) {
			StringBuilder sb = new StringBuilder();
			for (String c : chapters) {
				sb.append(c.split("-")[2]);
				sb.append(";");
			}
			return sb.toString().substring(0, sb.length()-1);
		} else {
			return null;
		}

	}
	
	private static String createIncorrectAnswers(List<String> incorrectAnswers) {
		
		StringBuilder sb = new StringBuilder();
		for (String incorrectAnswer : incorrectAnswers) {
			if (incorrectAnswer.length() == 0) {
				break;
			}
			sb.append(incorrectAnswer);
			sb.append(";");
		}
		
		return sb.toString();
	}
	
	private static boolean getTrueFalse() {
		return true;
	}
	
}
