package com.ideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ideas.springboot.form.app.services.PaisService;

@Component
public class PaisPropertyEdito extends PropertyEditorSupport {

	@Autowired
	private PaisService service;
	
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		try {
			Integer id = Integer.parseInt(idStr);
			this.setValue(service.obtenerPorId(id));
		} catch(NumberFormatException e) {
			setValue(null);
		}
		
	}
	
	
	
}
