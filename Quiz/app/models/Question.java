package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import models.enums.Grade;
import models.enums.QuestionType;
import models.enums.Subject;

/**
 * <p>Question <code>class</code> represents quiz questions contained in the application.</p>
 * 
 * <p>Every class has inherited <code>long</code> identificator from BaseModel, <code>questionText</code>
 * that contains question text, <code>questionType</code> that determines type of the question, grade that 
 * specifies at which level of school curriculum can answer to this question be found, <code>subject</code> 
 * and <code>subjectContent</code> which define which subject content question references and <code>difficulty
 * </code> which is a somewhat subjective rank how difficult the question is.</p>
 * 
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "question")
@Inheritance(strategy=InheritanceType.JOINED)
public class Question extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "questionText")
	public String questionText;
	
	@Enumerated(EnumType.STRING)
	public QuestionType questionType;
	
	@Enumerated(EnumType.STRING)
	public Grade grade;
	
	@Enumerated(EnumType.STRING)
	public Subject subject;
	
	@Column(name = "subjectContent")
	public String subjectContent;	// maybe Enum?
	
	@Column(name = "difficulty")
	public int difficulty;	// maybe Enum?
	
}
