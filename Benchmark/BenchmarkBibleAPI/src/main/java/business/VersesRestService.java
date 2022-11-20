package business;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Verse;

@RequestScoped
@Path("/verses")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class VersesRestService {
	@Inject
	VersesBusinessInterface service;
	
	@GET
	@Path("/getbyid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Verse getVerseById(@PathParam("id") int id)  {
		try {
			return service.getVerseById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/getfirst/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public Verse getFirstOccurence(@PathParam("word") String s)  {
		try {
			return service.getFirstOccurence(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/getcount/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCount(@PathParam("word") String s) {
		try {
			return service.countWords(s);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@GET
	@Path("/getverse/{book}/{chapter_num}/{verse_num}")
	@Produces(MediaType.APPLICATION_JSON)
	public Verse getVerse(@PathParam("book") String book, @PathParam("chapter_num") int chapterNum, @PathParam("verse_num") int verseNum) {
		try {
			Verse v = new Verse();
			v.setBook(book);
			v.setChapterNum(chapterNum);
			v.setVerseNum(verseNum);
			
			return service.getVerse(v);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}