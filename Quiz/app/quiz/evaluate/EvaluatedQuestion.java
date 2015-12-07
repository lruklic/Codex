package quiz.evaluate;

import enums.AnswerType;

public class EvaluatedQuestion {

	public QuestionResult questionResult;
	
	public QuestionScore questionScore;

	public EvaluatedQuestion(QuestionResult questionResult, QuestionScore questionScore) {
		this.questionResult = questionResult;
		this.questionScore = questionScore;
	}
	
	/**
	 * Checks if the user playing the quiz has answered the question correctly.
	 *
	 * @return AnswerType.CORRECT if the provided answer was correct, AnswerType.INCORRECT if it was incorrect 
	 */
	public AnswerType isCorrect() {
		return questionResult.isCorrect();
	}
	
}
