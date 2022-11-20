package data;

import javax.ejb.Local;

import beans.Verse;

@Local
public interface DatabaseInterface {
	Verse getFirstOccurrence(String s);
	int countWords(String s);
	Verse getVerse(String book, int chapter, int verseNum);
}