package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Verse;
import data.DatabaseInterface;

/** @author tanner ray
 *  business layer class to access data layer
 */
@Stateless
@Local(VersesBusinessInterface.class)
@LocalBean
@Alternative
public class VersesBusinessService implements VersesBusinessInterface {
	
	@EJB
	DatabaseInterface service;
	
	@Override
	public Verse getFirstOccurrence(String word) {
		return service.getFirstOccurrence(word);
	}

	@Override
	public int countWords(String count) {
		System.out.println(service.countWords(count));
		return service.countWords(count);
	}

	@Override
	public Verse getVerse(String book, int chapter, int verseNum) {		
		return service.getVerse(book, chapter, verseNum);
	}
	
}
