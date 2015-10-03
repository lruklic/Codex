package quiz.evaluate;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;

import quiz.QuizResult;
import cache.question.QuestionSet;
import enums.AnswerType;
import models.Question;
import models.enums.QuestionType;
import models.questions.ConnectCorrectQuestion;
import models.questions.InputAnswerQuestion;
import models.questions.MultipleAnswerQuestion;
import models.questions.MultipleChoiceQuestion;
import models.questions.TrueFalseQuestion;

public class QuestionEvaluator {
	
	/**
	 * Method that receives list of questions and evaluates it.
	 * 
	 * @param questionList list of JSON nodes from HTML that are questions and contain user answers
	 * @param questionSet set of questions for one quiz
	 * @return result of a quiz
	 */
	public QuizResult evaulateQuiz(List<JsonNode> questionList, QuestionSet questionSet) {
		
		QuizResult result = new QuizResult();
		
		for (JsonNode givenAnswer : questionList) {
			Long id = givenAnswer.get("id").asLong();
			Question question = questionSet.getQuestion(id);
//			AnswerType answerResult = evaluateQuestion(question, givenAnswer);
//			
//			QuestionResultPair qrp = new QuestionResultPair(question);
			
			QuestionResultPair qrp = evaluateQuestion(question, givenAnswer);
			
			qrp.createAnswerRecap();	// TODO add answer provided by user
			
			result.addQuestionResultPair(qrp);
			
		}
		
		return result;
		
	}
	
	/**
	 * Method that evaluates answer given by user.
	 * 
	 * @param question given question
	 * @param givenAnswer answer given by user
	 * @return QuestionResultPair that contains all the information about question and given answer
	 */
	private QuestionResultPair evaluateQuestion(Question question, JsonNode givenAnswer) {
		
		QuestionResultPair qrp = new QuestionResultPair(question);
		
		QuestionType qType = question.questionType;
		
		StringBuilder givenAnswerText = new StringBuilder();
		
		JsonNode answersNode = givenAnswer.get("answers");
		
		switch(qType) {
		case CONNECT_CORRECT:
			ConnectCorrectQuestion ccq = (ConnectCorrectQuestion) question;
			
			qrp.isCorrect = AnswerType.CORRECT;
			
			// if no answer is provided, mark as Unanswered
			if (answersNode != null) {
				List<JsonNode> answersNodes = Lists.newArrayList(answersNode.elements());
				for (JsonNode answerNode : answersNodes) {
					List<JsonNode> elements = Lists.newArrayList(answerNode.elements());
					
					String value = elements.get(0).asText();
					String key = elements.get(1).asText();
					
					if (value.equals("")) {
						value = "EMPTY_STRING";
					} else {
						givenAnswerText.append(key + ":" + value);
						givenAnswerText.append(", ");
					}
					
					String correctValueForKey = ccq.getAnswerPairs().get(key);
					
					if (!correctValueForKey.equals(value)) {
						qrp.isCorrect = AnswerType.INCORRECT;
					}
				}
				
			}
			qrp.givenAnswer = givenAnswerText.toString().substring(0, givenAnswerText.length()-2);
			break;
		case INPUT_ANSWER:
			String questionAnswer = ((InputAnswerQuestion) question).answer;
			// check for capital letters
			if (answersNode != null && answersNode.asText().length() > 0) {
				if ((answersNode.asText().equals(questionAnswer))) {
					qrp.isCorrect = AnswerType.CORRECT;
				} else {
					qrp.isCorrect = AnswerType.INCORRECT;
				}
				
				qrp.givenAnswer = answersNode.asText();
				
			} else {
				qrp.isCorrect = AnswerType.NOT_ANSWERED;
			}
			
			break;
		case MULTIPLE_ANSWER:
			qrp.isCorrect = AnswerType.CORRECT;
			
			if (answersNode != null) {
				List<JsonNode> answersNodes = Lists.newArrayList(answersNode.elements());
				
				List<String> givenAnswers = new ArrayList<>();
				for (JsonNode answerNode : answersNodes) {
					String answer = answerNode.asText();
					givenAnswers.add(answer);
					givenAnswerText.append(answer + ", ");
				}
				
				if (givenAnswers.size() != 0) {
					qrp.givenAnswer = givenAnswerText.toString().substring(0, givenAnswerText.length()-2);
				}
				
				MultipleAnswerQuestion maq = (MultipleAnswerQuestion) question;
				for (String correctAnswer : maq.getCorrectAnswers()) {
					if (givenAnswers.contains(correctAnswer)) {
						// do nothing
					} else {
						qrp.isCorrect = AnswerType.INCORRECT;
					}
				}
				
				for (String incorrectAnswer : maq.getIncorrectAnswers()) {
					if (givenAnswers.contains(incorrectAnswer)) {
						qrp.isCorrect = AnswerType.INCORRECT;
					}
				}
				
			}
			break;
		case MULTIPLE_CHOICE:
			String answer = "";
			if (answersNode != null) {
				answer = answersNode.asText();
			} else {
				qrp.isCorrect = AnswerType.NOT_ANSWERED;
			}
			if (answer.equals(((MultipleChoiceQuestion)question).correctAnswer)) {
				qrp.isCorrect = AnswerType.CORRECT;
			} else {
				qrp.isCorrect = AnswerType.INCORRECT;
			}
			
			qrp.givenAnswer = answer;
			
			break;
		case TRUE_FALSE:
			if (answersNode != null) {
				TrueFalseQuestion tfq = (TrueFalseQuestion) question;
				if ((answersNode.asText().equals("true") && tfq.answer) || (answersNode.asText().equals("false") && !tfq.answer)) {
					qrp.isCorrect = AnswerType.CORRECT;
				} else {
					qrp.isCorrect = AnswerType.INCORRECT;
				}
				
				qrp.givenAnswer = answersNode.asText();
				
			} else {
				// this depends on how user answers question in HTML
				qrp.isCorrect = AnswerType.NOT_ANSWERED;
			}
			
			break;
		}
		
		return qrp;
		
	}
	
}
