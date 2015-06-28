package models.enums;

import java.util.ArrayList;
import java.util.List;

public enum Chapter {
	
	INTRO_TO_HISTORY(Grade.GIMN_1ST, Subject.HISTORY),
	EASTERN_CIVILIZATIONS(Grade.GIMN_1ST, Subject.HISTORY),
	INTRO_TO_BIOLOGY(Grade.GIMN_1ST, Subject.BIOLOGY);
	
	public Subject subject;
	public Grade grade;

	private Chapter(Grade grade, Subject subject) {
		this.grade = grade;
		this.subject = subject;
	}
	
	/**
	 * Method that gets all Chapter enum elements that contain provided grade and subject.
	 * 
	 * @param grade
	 * @param subject
	 * @return
	 */
	
	public static List<Chapter> getByGradeAndSubject(Grade grade, Subject subject) {
		List<Chapter> chapterList = new ArrayList<>();
		
		for (Chapter c : Chapter.values()) {
			if (c.grade.equals(grade) && c.subject.equals(subject)) {
				chapterList.add(c);
			}
		}
		
		return chapterList;
	}
}
