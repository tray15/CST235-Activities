package business;

import java.util.List;

import beans.Verse;

public interface VersesBusinessInterface {
	Verse getVerseById(int id);
	List<Verse> getAllVerses();
	Verse getFirstOccurrence (String s);
	int countWords(String s);
	Verse getVerse(Verse v);
}
