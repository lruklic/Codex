package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "player")
@PrimaryKeyJoinColumn(name="id")
public class Player extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "games_played")
	public int gamesPlayed;

}
