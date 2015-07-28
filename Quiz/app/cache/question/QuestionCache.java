package cache.question;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class that caches questions.
 * 
 * @author Luka Ruklic
 *
 */

public class QuestionCache {

	private static QuestionCache questionCache;
	
	private static Map<String, QuestionSet> questionSetMap = new HashMap<>();
	
	private QuestionCache() {
	}
	
	/**
	 * Method that returns question cache.
	 * 
	 * @return instance of question cache
	 */
	public static QuestionCache getInstance() {
		
		if (questionCache == null) {
			questionCache = new QuestionCache();
		}
		return questionCache;
		
	}
	
	public QuestionSet getSet(String key) {
		return questionSetMap.get(key);
	}
	
	/**
	 * Add question set to cache. If already exists set with same key, it is replaced.
	 * @param key set key; username of user playing the quiz
	 * @param qs
	 */
	public void addSet(String key, QuestionSet qs) {
		questionSetMap.put(key, qs);
	}
	
	public void removeSet(String key) {
		questionSetMap.remove(key);
	}
}
