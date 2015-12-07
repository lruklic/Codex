package quiz;

import models.Question;

/**
 * Wrapper for question with additional details like 
 * 
 * @author Luka Ruklic
 *
 */

public class DetailedQuestion {

	public Question question;
	
	public Float maxPoints;
	
	public ScoringType scoringType;

	public DetailedQuestion(Question question) {
		this(question, null, null);
	}

	public DetailedQuestion(Question question, Float maxPoints, ScoringType scoringType) {
		this.question = question;
		
		if (maxPoints == null) {
			this.maxPoints = 1f;
		} else {
			this.maxPoints = maxPoints;
		}
		
		if (scoringType == null) {
			scoringType = ScoringType.ALL_OR_NOTHING;
		} else {
			this.scoringType = scoringType;
		}
		
	}
	
	
}
