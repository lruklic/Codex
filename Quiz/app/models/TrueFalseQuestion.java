package models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Question with only two options (TRUE or FALSE). Correct answer is contained in <code>answer</code>
 * field, with 1 symbolizing TRUE and 0 symbolizing FALSE.
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "truefalsequestion")
@PrimaryKeyJoinColumn(name="id")
public class TrueFalseQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  Boolean answer;

}
