package forms;

import models.enums.Grade;
import models.enums.QuestionType;
import models.enums.Subject;
import play.data.validation.Constraints.Required;

public class QuestionForm {
	
	@Required
	public String questionText;
	
	@Required
	public QuestionType questionType;
	
	@Required
	public Grade grade;
	
	@Required
	public Subject subject;
	
	@Required
	public String subjectContent;
	
	@Required
	public int difficulty;

}
