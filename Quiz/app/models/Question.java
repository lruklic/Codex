package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.enums.Chapter;
import models.enums.Grade;
import models.enums.QuestionType;
import models.enums.Subject;

/**
 * <p>Question <code>class</code> represents quiz questions contained in the application.</p>
 * 
 * <p>Every class has inherited <code>long</code> identificator from BaseModel, <code>questionText</code>
 * that contains question text, <code>questionType</code> that determines type of the question, grade that 
 * specifies at which level of school curriculum can answer to this question be found, <code>subject</code> 
 * ,<code>chapter</code> and <code>subjectContent</code> which define which subject content question references 
 * and <code>difficulty</code> which is a somewhat subjective rank how difficult the question is. Also,
 * each question can have optional <code>explanation</code> that closely explains correct answer and special
 * tags that note that same question already appeared on competition or nationwide leaving exam. </p> 
 * 
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "question")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Question extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="lastEdited")
	public Long lastEdited;
	
	@Column(name = "questionText", length = 500)
	public String questionText;
	
	@Enumerated(EnumType.STRING)
	public QuestionType questionType;
	
	@Enumerated(EnumType.STRING)
	public Grade grade;
	
	@Enumerated(EnumType.STRING)
	public Subject subject;
	
	@Column(name = "subjectChapter")
	public String chapters;
	
	@Column(name = "subjectContent")
	public String subjectContent;	// maybe Enum?
	
	@Column(name = "specialTags")
	public String specialTags;
	
	@Column(name = "difficulty")
	public int difficulty;	// maybe Enum?
	
	@Column(name = "explanation")
	public String explanation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	public Admin author;
	
	/**
	 * Empty contructor.
	 */
	public Question() {
	}
	/**
	 * Constructor containing all question parameters. 
	 * 
	 * @param questionText
	 * @param questionType
	 * @param grade
	 * @param subject
	 * @param chapters
	 * @param subjectContent
	 * @param difficulty
	 * @param admin
	 */
	public Question(String questionText, QuestionType questionType, Grade grade, Subject subject, String chapters,
			String subjectContent, String specialTags, int difficulty, String explanation, Admin author) {
		this.lastEdited = System.currentTimeMillis();
		this.questionText = questionText;
		this.questionType = questionType;
		this.grade = grade;
		this.subject = subject;
		this.chapters = chapters;
		this.subjectContent = subjectContent;
		this.specialTags = specialTags;
		this.difficulty = difficulty;
		this.explanation = explanation;
		this.author = author;
	}
	
	/**
	 * Getter for chapters that turns string into list of Chapters.
	 * 
	 * @return Chapter list
	 */
	public List<Chapter> getChapters() {
		List<Chapter> chaptersList = new ArrayList<Chapter>(); 
		
		String[] chaptersArray = chapters.split(";");
		for (String chapter : chaptersArray) {
			chaptersList.add(Chapter.valueOf(chapter));
		}
		
		return chaptersList;
	}
	
	/**
	 * Setter for chapters.
	 * @param chaptersList list of Chapter
	 */
	public void setChapters(List<Chapter> chaptersList) {
		StringBuilder sb = new StringBuilder();
		for (Chapter c : chaptersList) {
			sb.append(c.toString());
			sb.append(";");
		}
		
		this.chapters = sb.toString();
	}
	
}
