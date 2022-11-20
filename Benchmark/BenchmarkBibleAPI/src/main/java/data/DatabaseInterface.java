package data;

import java.util.List;

import javax.ejb.Local;

import beans.Verse;

@Local
public interface DatabaseInterface {
	Verse getVerseById(int id);
	List<Verse> getAllVerses();
	Verse getFirstOccurrence(String s);
	int countWords(String s);
	Verse getVerse(Verse v);
}