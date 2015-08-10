package services.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import models.Question;
import models.enums.Subject;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import services.model.QuestionService;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

/**
 * Implementation of question service.
 * 
 * @author Luka Ruklic
 * @author Ivan Weber
 *
 */

@Transactional
public class QuestionServiceImpl extends BaseModelServiceImpl<Question> implements QuestionService {

	protected QuestionServiceImpl() {
		super(Question.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findQuestionsByAdmin(String username) {
		Query query = JPA.em().createQuery("SELECT q FROM Question q WHERE q.admin.username = :username", Question.class);
		query.setParameter("username", username);
		return query.getResultList();
	}
	
	@Override
	public long countQuestionsBySubject(Subject subject) {
		Query query = JPA.em().createQuery("SELECT COUNT(*) FROM Question q WHERE q.subject = :subject");
		query.setParameter("subject", subject);
		
		return (long) query.getSingleResult();
	}
	
	@Override
	public List<Question> getSimilarQuestions(Long id) {
		Question current = findById(id);
		List<Question> others = findAll();
		
		
		List<Question> similar = new ArrayList<Question>();
		JaroWinkler jaroWinkler = new JaroWinkler();
		for(Question question : others) {
			if(jaroWinkler.getSimilarity(current.questionText, question.questionText) > 0.5) {
				similar.add(question);
			}
		}
		
		return similar;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionsBySubjects(List<Subject> subjects) {
		List<Question> questions = new ArrayList<>();
		
		for (Subject subject : subjects) {
			Query query = JPA.em().createQuery("SELECT q FROM Question q WHERE q.subject = :subject", Question.class);
			query.setParameter("subject", subject);
			
			questions.addAll(query.getResultList());
		}
		
		return questions;
	}

}
