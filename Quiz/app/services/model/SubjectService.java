package services.model;

import models.Subject;

public interface SubjectService extends BaseModelService<Subject> {
	
	public Subject getSubjectByName(String subjectName);

}
