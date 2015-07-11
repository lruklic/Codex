package forms;

import models.enums.NewsPriority;
import models.enums.NewsType;
import play.data.validation.Constraints.Required;

public class NoveltyForm {
	
	@Required
	public String noveltyTitle;
	
	@Required
	public String noveltyText;
	
	@Required
	public NewsType newsType;
	
	@Required
	public NewsPriority newsPriority;
	

}
