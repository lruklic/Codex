package models.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeration that contains all chapters for all subjects that application
 * supports.
 * 
 * @author Luka Ruklic
 *
 */

public enum Chapter {
	/**
	 * Enums for subject History.
	 */
	INTRO_TO_HISTORY(Grade.GIMN_1ST, Subject.HISTORY),
	ANCIENT_HISTORY(Grade.GIMN_1ST, Subject.HISTORY),
	EASTERN_CIVILIZATIONS(Grade.GIMN_1ST, Subject.HISTORY),
	OLD_GREECE(Grade.GIMN_1ST, Subject.HISTORY),
	ROME_KINGS(Grade.GIMN_1ST, Subject.HISTORY),
	ROME_EMPIRE(Grade.GIMN_1ST, Subject.HISTORY),
	
	/**
	 * Enums for subject Geography.
	 */
	INTRO_TO_GEOGRAPHY(Grade.GIMN_1ST, Subject.GEOGRAPHY),
	EARTH_IN_SOLAR_SYSTEM(Grade.GIMN_1ST, Subject.GEOGRAPHY),
	ORIENTATION_AND_POSITIONING(Grade.GIMN_1ST, Subject.GEOGRAPHY),
	GEOLOGICAL_FEATURES(Grade.GIMN_1ST, Subject.GEOGRAPHY),
	CLIMATE_ON_EARTH(Grade.GIMN_1ST, Subject.GEOGRAPHY),
	WATER_GROUND_LIFE(Grade.GIMN_1ST, Subject.GEOGRAPHY),
	
	/**
	 * Enums for subject Croatian Language - Literature
	 */
	LYRICS(Grade.GIMN_1ST, Subject.CROATIAN_LITERATURE),
	EPICS(Grade.GIMN_1ST, Subject.CROATIAN_LITERATURE),
	DRAMA(Grade.GIMN_1ST, Subject.CROATIAN_LITERATURE),
	CLASSICAL_LITERATURE(Grade.GIMN_1ST, Subject.CROATIAN_LITERATURE),
	MEDIEVAL_LITERATURE(Grade.GIMN_1ST, Subject.CROATIAN_LITERATURE),
	
	EARLY_RENAISSANCE(Grade.GIMN_2ND, Subject.CROATIAN_LITERATURE),
	RENAISSANCE(Grade.GIMN_2ND, Subject.CROATIAN_LITERATURE),
	BAROQUE(Grade.GIMN_2ND, Subject.CROATIAN_LITERATURE),
	CLASSICISM(Grade.GIMN_2ND, Subject.CROATIAN_LITERATURE),
	ROMANTICISM(Grade.GIMN_2ND, Subject.CROATIAN_LITERATURE),
	
	PROTOREALISM(Grade.GIMN_3RD, Subject.CROATIAN_LITERATURE),
	REALISM(Grade.GIMN_3RD, Subject.CROATIAN_LITERATURE),
	MODERNISM(Grade.GIMN_3RD, Subject.CROATIAN_LITERATURE),
	MODERN(Grade.GIMN_3RD, Subject.CROATIAN_LITERATURE),
	
	VANGUARD(Grade.GIMN_4TH, Subject.CROATIAN_LITERATURE),
	FIRST_PERIOD(Grade.GIMN_4TH, Subject.CROATIAN_LITERATURE),
	EXPRESSIONISM(Grade.GIMN_4TH, Subject.CROATIAN_LITERATURE),
	SECOND_PERIOD(Grade.GIMN_4TH, Subject.CROATIAN_LITERATURE),
	SECOND_MODERN(Grade.GIMN_4TH, Subject.CROATIAN_LITERATURE),
	CONTEMPORARY_LITERATURE(Grade.GIMN_4TH, Subject.CROATIAN_LITERATURE),
	
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
