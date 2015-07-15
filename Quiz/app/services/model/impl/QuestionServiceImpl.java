package services.model.impl;

import java.util.List;

import javax.persistence.Query;

import models.Question;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import services.model.QuestionService;

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

}
