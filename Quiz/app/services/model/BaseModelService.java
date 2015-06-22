package services.model;

import java.util.List;

import play.db.jpa.Transactional;

/**
 * Base model service interface that defines basic operations that every service must implement.
 * 
 * @author Luka Ruklic
 *
 * @param <T> parameter that defines class
 */

@Transactional
public interface BaseModelService<T> {
	
	/**
	 * Method that finds object T by id.
	 * 
	 * @param id identificator
	 * @return object T if found
	 */
	public T findById(Long id);
	
	public List<T> findAll();
	
	public long count();
	
	public T save(T instance);
	
	public T delete(T instance);
	
}
