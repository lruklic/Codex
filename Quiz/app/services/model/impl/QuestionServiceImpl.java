package services.model.impl;

import models.Question;
import play.db.jpa.Transactional;
import services.model.QuestionService;

@Transactional
public class QuestionServiceImpl extends BaseModelServiceImpl<Question> implements QuestionService {

	protected QuestionServiceImpl() {
		super(Question.class);
	}

}
