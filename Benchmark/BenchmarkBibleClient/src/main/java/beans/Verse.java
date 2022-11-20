package beans;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
@Stateless
public class Verse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String book;
	private int chapterNum;
	private int verseNum;
	private String verseContent;
	
	public Verse() {
		
	}
	public Verse(String book, int chapterNum, int verseNum, String verseContent) {
		this.book = book;
		this.chapterNum = chapterNum;
		this.verseNum = verseNum;
		this.verseContent = verseContent;
	}
	
	public Verse(Verse v) {
		this.book = v.getBook();
		this.chapterNum = v.getChapterNum();
		this.verseNum = v.getVerseNum();
		this.verseContent = v.getVerseContent();
	}

	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getChapterNum() {
		return chapterNum;
	}
	public void setChapterNum(int chapterNum) {
		this.chapterNum = chapterNum;
	}
	public int getVerseNum() {
		return verseNum;
	}
	public void setVerseNum(int verseNum) {
		this.verseNum = verseNum;
	}
	public String getVerseContent() {
		return verseContent;
	}
	public void setVerseContent(String verseContent) {
		this.verseContent = verseContent;
	}
}
