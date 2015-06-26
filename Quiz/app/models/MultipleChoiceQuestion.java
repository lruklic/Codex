package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Question with multiple options.
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "multiplechoicequestion")
@PrimaryKeyJoinColumn(name="id")
public class MultipleChoiceQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "correctAnswer")
	public String correctAnswer;
	
	@Column(name = "incorrectAnswers")
	public String incorrectAnswers;

}
