package quiz;

import java.util.ArrayList;
import java.util.List;

import enums.AnswerType;
import quiz.evaluate.QuestionResultPair;

/**
 * Class that contains details about quiz that was recently completed.
 * 
 * @author Luka Ruklic
 *
 */

public class QuizResult {
	
	public List<QuestionResultPair> questionResultPairs = new ArrayList<>();
	
	/**
	 * Empty constructor.
	 */
	public QuizResult() {	
	}
	
	public List<QuestionResultPair> getQuestionResultPairs() {
		return questionResultPairs;
	}

	public void addQuestionResultPair(QuestionResultPair qrp) {
		questionResultPairs.add(qrp);
	}

	public int getNumberOfQuestions() {
		return questionResultPairs.size();
	}

	public int getNumberOfCorrectAnswers() {
		return countAnswers(AnswerType.CORRECT);
	}

	public int getNumberOfIncorrectAnswers() {
		return countAnswers(AnswerType.INCORRECT);
	}

	public int getNumberOfUnansweredQuestions() {
		return countAnswers(AnswerType.NOT_ANSWERED);
	}
	
	private int countAnswers(AnswerType at) {
		
		int counter = 0;
		
		for (QuestionResultPair qrp : questionResultPairs) {
			if (qrp.isCorrect().equals(at)) {
				counter++;
			}
		}
		
		return counter;
	}
	
	
}
