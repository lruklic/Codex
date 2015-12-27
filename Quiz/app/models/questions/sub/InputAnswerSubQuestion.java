package models.questions.sub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "sub_input_answer")
@PrimaryKeyJoinColumn(name="id")
public class InputAnswerSubQuestion extends SubQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "answer")
	public String answer;
}
