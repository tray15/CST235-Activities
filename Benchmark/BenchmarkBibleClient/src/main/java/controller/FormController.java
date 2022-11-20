package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Verse;
import business.VersesBusinessService;

@ManagedBean
@ViewScoped
public class FormController {
	
	@Inject
	VersesBusinessService service;
	
	public String findVerse(Verse v) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getRequestMap().put("verse", v);

		v = service.getVerse(v.getBook(), v.getChapterNum(), v.getVerseNum());

		return "verse.xhtml";
	}
}
