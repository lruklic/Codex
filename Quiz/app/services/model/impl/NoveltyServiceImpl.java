package services.model.impl;

import models.Novelty;
import play.db.jpa.Transactional;
import services.model.NoveltyService;

@Transactional
public class NoveltyServiceImpl extends BaseModelServiceImpl<Novelty> implements NoveltyService {

	protected NoveltyServiceImpl() {
		super(Novelty.class);
	}

}
