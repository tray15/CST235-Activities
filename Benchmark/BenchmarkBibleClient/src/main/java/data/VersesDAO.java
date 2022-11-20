package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import beans.Verse;

@Stateless
@Local(DatabaseInterface.class)
@LocalBean
@Alternative
public class VersesDAO implements DatabaseInterface {

	@Override
	public int countWords(String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Verse getFirstOccurence(String s) {
		// TODO Auto-generated method stub
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
				Scanner scanner = new Scanner(url.openStream());
				String result = "";
				while (scanner.hasNext()) {
					result+= scanner.nextLine();
				}
				
				//v.setVerseContent(result);
				
				scanner.close();
				
				JSONParser parse = new JSONParser();
				JSONObject jsonObj = (JSONObject) parse.parse(result);
				
				System.out.println("From the Web Client: " + jsonObj.get("verse"));
				String content = jsonObj.get("verse").toString();
				
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
