package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Verse;

@Stateless
@Local(DatabaseInterface.class)
@LocalBean
@Alternative
public class VersesDAO implements DatabaseInterface {
	private static final String URL = "jdbc:mysql://localhost:3307/cst235benchmark?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "root";
	
	private static final String VERSE_BY_ID = "SELECT * FROM `verses` WHERE `id`=?";
	private static final String GET_FIRST_OCCURENCE = "SELECT * FROM `verses` WHERE `book` LIKE ? OR `verse_content` LIKE ? ORDER BY `id` LIMIT 1";
	private static final String COUNT = "SELECT COUNT(*) as Occurences FROM verses WHERE verse_content LIKE ?";
	private static final String GET_VERSE = "SELECT `verse_content` FROM `verses` WHERE `book`=? AND `chapter_number`=? AND `verse_number`=?";
	
	@Override
	public Verse getVerseById(int id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			
			//Prepare sql statement with variable ID
			PreparedStatement stmt = conn.prepareStatement(VERSE_BY_ID, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			//If our query returned anything, get the data
			if (rs.next()) {
				String book = rs.getString("book");
				int chapterNum = rs.getInt("chapter_number");
				int verseNum = rs.getInt("verse_number");
				String verseContent = rs.getString("verse_content");
				
				Verse found = new Verse(book, chapterNum, verseNum, verseContent);
				System.out.println("Book: " + book + " Chapter: " + chapterNum + " VerseNumber: " + verseNum + " VerseContent: " + verseContent);
				return found;
			}
			
		} catch (SQLException e) {
			System.out.println("Connection to database failed.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Verse> getAllVerses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Verse getFirstOccurrence(String s) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			
			//Prepare sql statement with given word
			PreparedStatement stmt = conn.prepareStatement(GET_FIRST_OCCURENCE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "%" + s + "%");
			stmt.setString(2, "%" + s + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			//If our query returned anything, get the data
			if (rs.next()) {
				String book = rs.getString("book");
				int chapterNum = rs.getInt("chapter_number");
				int verseNum = rs.getInt("verse_number");
				String verseContent = rs.getString("verse_content");
				
				Verse found = new Verse(book, chapterNum, verseNum, verseContent);
				System.out.println("Getting first occurence...");
				System.out.println("Book: " + book + " Chapter: " + chapterNum + " VerseNumber: " + verseNum + " VerseContent: " + verseContent);
				return found;
			}
			
		} catch (SQLException e) {
			System.out.println("Connection to database failed.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countWords (String count) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement(COUNT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "%" + count + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			int counter = 0;
			while (rs.next()) {
				counter = rs.getInt("Occurences");
			}
			
			return counter;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Verse getVerse(Verse v) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement stmt = conn.prepareStatement(GET_VERSE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, v.getBook());
			stmt.setInt(2, v.getChapterNum());
			stmt.setInt(3, v.getVerseNum());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String verseContent = rs.getString("verse_content");
				
				System.out.println("The verse we found is: " + verseContent);
				
				v.setVerse(verseContent);
				return v;
			}
		} catch (Exception e) {
			System.out.println("Unable to find a verse with given parameters");
			e.printStackTrace();
		}
		return null;
	}
	
}
