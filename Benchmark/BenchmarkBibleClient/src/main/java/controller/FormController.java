package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Verse;
import business.VersesBusinessInterface;

/**
 * 
 * @author tanner ray
 * controller class to handle requests on the index page
 *
 */

@ManagedBean
@ViewScoped
public class FormController {
	
	@Inject
	VersesBusinessInterface service;
	
	public String findVerse(Verse v) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getRequestMap().put("verse", v);
		
		//get the content from our service!
		v.setVerseContent(service.getVerse(v.getBook(), v.getChapterNum(), v.getVerseNum()).getVerseContent());
		
		return "verse.xhtml";
	}
	
	public String getFirstOccurrence(String word) {
		//Get Managed bean
		FacesContext context = FacesContext.getCurrentInstance();
		Verse v = context.getApplication().evaluateExpressionGet(context, "#{verse}", Verse.class);
		
		//Set managed bean to results from service
		v = service.getFirstOccurrence(word);
		
		//put data from service in the context
		context.getExternalContext().getRequestMap().put("verse", v);
		
		return "first.xhtml";
	}
	
	public String getCount(String count) {
		FacesContext context = FacesContext.getCurrentInstance();
		//store our result in a variable
		int counter = service.countWords(count);
		//put the variable in context to display on next page
		context.getExternalContext().getRequestMap().put("count", counter);
		
		return "count.xhtml";
	}
	
	public VersesBusinessInterface getService() {
		return service;
	}
}
