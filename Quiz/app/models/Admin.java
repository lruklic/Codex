package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "questions_added")
	public int questionsAdded;

}
