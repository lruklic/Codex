package services.model;

import java.util.List;

import models.Question;

public interface QuestionService extends BaseModelService<Question> {
	
	public List<Question> findQuestionsByAdmin(String username);
	
}
