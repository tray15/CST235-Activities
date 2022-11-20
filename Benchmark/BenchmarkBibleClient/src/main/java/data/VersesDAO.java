package data;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Verse;

/** @author tanner ray
 * 
 * data access object that interacts with the api
 * 
 */

@Stateless
@Local(DatabaseInterface.class)
@LocalBean
@Alternative
public class VersesDAO implements DatabaseInterface {

	@Override
	public int countWords(String s) {
		HttpURLConnection conn = null;
		try {
			
			//URL with parameters
			URL url = new URL("http://localhost:8080/BenchmarkBibleAPI/rest/verses/getcount/" + s);
			conn = (HttpURLConnection)url.openConnection();
			
			try {
				//Let's get the data from our request
				Scanner scanner = new Scanner(url.openStream());
				String result = "";
				
				while (scanner.hasNext()) {
					result+= scanner.nextLine();
				}
				//all finished reading, let's avoid a resource leak.
				scanner.close();
				
				//add the verse content into a new verse object and return it
				return Integer.parseInt(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Verse getFirstOccurrence(String word) {
		HttpURLConnection conn = null;
		try {
			
			//URL with parameters
			URL url = new URL("http://localhost:8080/BenchmarkBibleAPI/rest/verses/getfirst/" + word);
			conn = (HttpURLConnection)url.openConnection();
			
			try {
				//Let's get the data from our request
				Scanner scanner = new Scanner(url.openStream());
				String result = "";
				//get every line from json formatted text
				while (scanner.hasNext()) {
					result+= scanner.nextLine();
				}
				//all finished reading, let's avoid a resource leak.
				scanner.close();
				
				//take json formatted text and parse it
				JSONParser parse = new JSONParser();
				//turn json parsed text into an actual json object
				JSONObject jsonObj = (JSONObject) parse.parse(result);
								
				//get the value of the verse key and convert it into a string
				String book = jsonObj.get("book").toString();
				int chapter = (int)(long) jsonObj.get("chapterNum");
				int verseNum = (int)(long) jsonObj.get("verseNum");
				String content = jsonObj.get("verse").toString();
				
				//add the verse content into a new verse object and return it
				return new Verse(book, chapter, verseNum, content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Verse getVerse(String book, int chapter, int verseNum) {
		HttpURLConnection conn = null;
		try {
			
			//URL with parameters
			URL url = new URL("http://localhost:8080/BenchmarkBibleAPI/rest/verses/getverse/" + book + "/" + chapter + "/" + verseNum);
			conn = (HttpURLConnection)url.openConnection();
			
			try {
				//Let's get the data from our request
				Scanner scanner = new Scanner(url.openStream());
				String result = "";
				//get every line from json formatted text
				while (scanner.hasNext()) {
					result+= scanner.nextLine();
				}
				//all finished reading, let's avoid a resource leak.
				scanner.close();
				
				//take json formatted text and parse it
				JSONParser parse = new JSONParser();
				//turn json parsed text into an actual json object
				JSONObject jsonObj = (JSONObject) parse.parse(result);
				
				System.out.println("From the Web Client: " + jsonObj.get("verse"));
				//get the value of the verse key and convert it into a string
				String content = jsonObj.get("verse").toString();
				
				//add the verse content into a new verse object and return it
				return new Verse(book, chapter, verseNum, content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
