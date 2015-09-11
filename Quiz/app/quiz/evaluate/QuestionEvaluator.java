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
	 * @param questionList
	 * @param questionSet
	 * @return
	 */
	public QuizResult evaulateQuiz(List<JsonNode> questionList, QuestionSet questionSet) {
		
		QuizResult result = new QuizResult();
		
		for (JsonNode givenAnswer : questionList) {
			Long id = givenAnswer.get("id").asLong();
			Question question = questionSet.getQuestion(id);
			AnswerType answerResult = evaluateQuestion(question, givenAnswer);
			
			QuestionResultPair qrp = new QuestionResultPair(question, answerResult);
			result.addQuestionResultPair(qrp);
			
		}
		
		return result;
		
	}
	
	private AnswerType evaluateQuestion(Question question, JsonNode givenAnswer) {
		
		QuestionType qType = question.questionType;
		
		JsonNode answersNode = givenAnswer.get("answers");
		
		switch(qType) {
		case CONNECT_CORRECT:
			ConnectCorrectQuestion ccq = (ConnectCorrectQuestion) question;
			
			// if no answer is provided, mark as Unanswered
			if (answersNode != null) {
				List<JsonNode> answersNodes = Lists.newArrayList(answersNode.elements());
				for (JsonNode answerNode : answersNodes) {
					List<JsonNode> elements = Lists.newArrayList(answerNode.elements());
					
					String value = elements.get(0).asText();
					String key = elements.get(1).asText();
					
					if (value.equals("")) {
						value = "EMPTY_STRING";
					}
					
					String correctValueForKey = ccq.getAnswerPairs().get(key);
					
					if (!correctValueForKey.equals(value)) {
						return AnswerType.INCORRECT;
					}
				}
				
				return AnswerType.CORRECT;
			}
			break;
		case INPUT_ANSWER:
			String questionAnswer = ((InputAnswerQuestion) question).answer;
			// check for capital letters
			if (answersNode != null && answersNode.asText().length() > 0) {
				if ((answersNode.asText().equals(questionAnswer))) {
					return AnswerType.CORRECT;
				} else {
					return AnswerType.INCORRECT;
				}
			} else {
				return AnswerType.NOT_ANSWERED;
			}


		case MULTIPLE_ANSWER:
			if (answersNode != null) {
				List<JsonNode> answersNodes = Lists.newArrayList(answersNode.elements());
				
				List<String> givenAnswers = new ArrayList<>();
				for (JsonNode answerNode : answersNodes) {
					givenAnswers.add(answerNode.asText());
				}
				
				MultipleAnswerQuestion maq = (MultipleAnswerQuestion) question;
				for (String correctAnswer : maq.getCorrectAnswers()) {
					if (givenAnswers.contains(correctAnswer)) {
						// do nothing
					} else {
						return AnswerType.INCORRECT;
					}
				}
				
				return AnswerType.CORRECT;
				
			}
			break;
		case MULTIPLE_CHOICE:
			String answer = "";
			if (answersNode != null) {
				answer = answersNode.asText();
			} else {
				return AnswerType.NOT_ANSWERED;
			}
			if (answer.equals(((MultipleChoiceQuestion)question).correctAnswer)) {
				return AnswerType.CORRECT;
			} else {
				return AnswerType.INCORRECT;
			}
		case TRUE_FALSE:
			if (answersNode != null) {
				TrueFalseQuestion tfq = (TrueFalseQuestion) question;
				if ((answersNode.asText().equals("true") && tfq.answer) || (answersNode.asText().equals("false") && !tfq.answer)) {
					return AnswerType.CORRECT;
				} else {
					return AnswerType.INCORRECT;
				}
			} else {
				// this depends on how user answers question in HTML
				return AnswerType.NOT_ANSWERED;
			}
			
		}
		
		return null;
		
	}
	
}
