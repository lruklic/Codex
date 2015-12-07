package quiz.evaluate;

import java.util.ArrayList;
import java.util.List;

import enums.AnswerType;

/**
 * Class that contains details about quiz that was recently completed.
 * 
 * @author Luka Ruklic
 *
 */

public class QuizResult {
	
	public List<EvaluatedQuestion> evaluatedQuestions = new ArrayList<>();
	
	/**
	 * Empty constructor.
	 */
	public QuizResult() {	
	}
	
	public List<EvaluatedQuestion> getEvaluatedQuestions() {
		return evaluatedQuestions;
	}

	public void addEvaluatedQuestion(EvaluatedQuestion evaluatedQuestion) {
		evaluatedQuestions.add(evaluatedQuestion);
	}

	public int getNumberOfQuestions() {
		return evaluatedQuestions.size();
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
	
	private int countAnswers(AnswerType answerType) {
		
		int counter = 0;
		
		for (EvaluatedQuestion evaluatedQuestion : evaluatedQuestions) {
			if (evaluatedQuestion.isCorrect().equals(answerType)) {
				counter++;
			}
		}
		
		return counter;
	}
	
	
}
