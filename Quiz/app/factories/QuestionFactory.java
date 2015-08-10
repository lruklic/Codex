package factories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import constants.Constants;
import enums.AnswerType;
import forms.QuestionForm;
import models.Admin;
import models.Question;
import models.questions.ConnectCorrectQuestion;
import models.questions.InputAnswerQuestion;
import models.questions.MultipleAnswerQuestion;
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
		
		String specialTags = createSpecialTags(form);
		
		switch(form.questionType) {
			case MULTIPLE_CHOICE:
				String incorrect = createIncorrectAnswers(form.incorrect);
				return new MultipleChoiceQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						specialTags, form.difficulty, form.explanation, currentAdmin, form.multipleCorrect, incorrect);
			case MULTIPLE_ANSWER:
				String multipleCorrect = createMultipleAnswersString(form.multiple, form.multipleTrue, AnswerType.CORRECT);
				String multipleIncorrect = createMultipleAnswersString(form.multiple, form.multipleTrue, AnswerType.INCORRECT);
				return new MultipleAnswerQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						specialTags, form.difficulty, form.explanation, currentAdmin, multipleCorrect, multipleIncorrect);
			case TRUE_FALSE:
				Boolean trueFalse = false;
				if (form.trueFalse != null) {
					trueFalse = true;
				}
				return new TrueFalseQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						specialTags, form.difficulty, form.explanation, currentAdmin, trueFalse);
			case INPUT_ANSWER:
				return new InputAnswerQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						specialTags, form.difficulty, form.explanation, currentAdmin, form.inputCorrect);
			case CONNECT_CORRECT:
				// prevent creating questions with unpaired left or more left than right
				Map<String, String> answerPairs = createAnswerPairs(form.termColumn1, form.termColumn2);
				return new ConnectCorrectQuestion(form.questionText, form.questionType, form.grade, form.subject, chapters, form.subjectContent,
						specialTags, form.difficulty, form.explanation, currentAdmin, answerPairs);
		}
		
		return null;
	}
	
	/**
	 * Method that creates special tags from user inputs in HTML.
	 * @param form form containing relevant fields
	 * @return specialTags String
	 */
	private static String createSpecialTags(QuestionForm form) {
		StringBuilder sb = new StringBuilder();
		
		// TODO check if some value is not set
		if (form.competition != null && form.competition.equals("true")) {
			// Chosen question has previously appeared on competition
			sb.append(Constants.COMPETITION_ABBR+"-");
			sb.append(form.competitionLevel+"-");
			sb.append(form.competitionYear);
		} else if (form.finalExam != null && form.finalExam.equals("true")) {
			sb.append(Constants.LEAVING_EXAM_ABBR+"-");
			sb.append(form.finalsYear);
		}
		
		return sb.toString();
	}
	
	private static Map<String, String> createAnswerPairs(List<String> termColumn1, List<String> termColumn2) {
		Map<String, String> answerPairs = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < termColumn2.size(); i++) {
			String term1 = termColumn1.get(i);
			String term2 = termColumn2.get(i);
			
			if (term2.length() > 0) {
				if (term1.length() > 0) {
					answerPairs.put(term1, term2);
				} else {
					answerPairs.put("EMPTY_STRING"+i, term2);	// what if someone enters EMPTY_STRING as an answer?
				}
			}
		}
		
		return answerPairs;
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
	
	private static String createMultipleAnswersString(List<String> multiple, List<String> multipleTrue, AnswerType at) {
		
		StringBuilder sb = new StringBuilder();
		
		List<String> multipleTrueNoSlash = new ArrayList<>();
		
		if (multipleTrue != null) {
			for (String mt : multipleTrue) {
				multipleTrueNoSlash.add(mt.replaceAll("/", ""));		// why are / on the end?
			}
		}

		for (int i = 0; i < multiple.size(); i++) {
			if (at == AnswerType.CORRECT) {
				if (multipleTrueNoSlash.contains(String.valueOf(i))) {
					sb.append(multiple.get(i));
					sb.append(";");
				}
			} else {
				if (!multipleTrueNoSlash.contains(String.valueOf(i))) {
					sb.append(multiple.get(i));
					sb.append(";");
				}
			}
		}
		if (sb.length() == 0) {
			return sb.toString();
		} else {
			return sb.toString().substring(0, sb.length()-1);
		}

	}
	
}
