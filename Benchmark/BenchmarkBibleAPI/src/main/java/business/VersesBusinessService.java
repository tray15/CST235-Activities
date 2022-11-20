package business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Verse;
import data.VersesDAO;

/** @author tanner ray
 *  business layer class to access data layer
 */
@Stateless
@Local(VersesBusinessInterface.class)
@LocalBean
@Alternative
public class VersesBusinessService implements VersesBusinessInterface {
	
	@EJB
	VersesDAO service;
	
	@Override
	public Verse getVerseById(int id) {
		return service.getVerseById(id);
	}

	@Override
	public List<Verse> getAllVerses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Verse getFirstOccurence(String s) {
		return service.getFirstOccurence(s);
	}

	@Override
	public int countWords(String s) {
		return service.countWords(s);
	}

	@Override
	public Verse getVerse(Verse v) {
		return service.getVerse(v);
	}
	
}
