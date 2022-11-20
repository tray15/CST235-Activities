package business;

import beans.Verse;

public interface VersesBusinessInterface {
	Verse getFirstOccurence (String s);
	int countWords(String s);
	Verse getVerse(String book, int chapter, int verseNum);
}
