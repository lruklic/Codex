package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Model that serves as mark for question image stored on file server.
 * 
 * @author Luka Ruklic
 *
 */

@Entity
@Table(name = "image")
public class Image extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Image() {		
	}
	
	public Image(String filePath) {
		super();
		this.filePath = filePath;
	}

	@Column(name = "filePath")
	public String filePath;
	
	
	
}
