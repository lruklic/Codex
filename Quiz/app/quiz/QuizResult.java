package quiz;

public class QuizResult {

	public int numberOfQuestions = 0;
	public int numberOfCorrectAnswers = 0;
	public int numberOfIncorrectAnswers = 0;
	public int numberOfUnansweredQuestions= 0;
	
	public QuizResult() {
		
	}
	
	public QuizResult(int numberOfQuestions, int numberOfCorrectAnswers) {
		this.numberOfQuestions = numberOfQuestions;
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
	}
}
