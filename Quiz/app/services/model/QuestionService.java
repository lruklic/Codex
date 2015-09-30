package services.model;

import java.util.List;

import models.Question;
import models.Subject;

public interface QuestionService extends BaseModelService<Question> {
	
	public List<Question> findQuestionsByAdmin(String username);
	
	public long countQuestionsBySubject(Subject subject);
	
	public List<Question> getQuestionsBySubjects(List<Subject> subjects);
	
	public List<Question> getSimilarQuestions(Long id);
	
}
