package forms;

import java.util.List;

/**
 * Form that is instatiated and populated when user chooses a quiz to partake.
 * 
 * @author Luka Ruklic
 *
 */

public class QuizForm {
	/**
	 * Grade which user chose.
	 */
	public String grade;
	
	/**
	 * Subject which user chose.
	 */
	public String subject;
	
	/**
	 * Chapter(s) of a subject that user chose.
	 */
	public List<String> chapters;

}
