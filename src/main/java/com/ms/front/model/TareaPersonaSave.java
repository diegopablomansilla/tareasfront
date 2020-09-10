package com.ms.front.model;

import com.ms.front.commons.model.Entity;

public class TareaPersonaSave extends Entity {

	private String personaId;
	private String tomaTarea;
	private String sueltaTarea;
	private String tareaId;

	public String getPersonaId() {
		return personaId;
	}

	public void setPersonaId(String personaId) {
		this.personaId = personaId;
	}

	public String getTomaTarea() {
		return tomaTarea;
	}

	public void setTomaTarea(String tomaTarea) {
		this.tomaTarea = tomaTarea;
	}

	public String getSueltaTarea() {
		return sueltaTarea;
	}

	public void setSueltaTarea(String sueltaTarea) {
		this.sueltaTarea = sueltaTarea;
	}

	public String getTareaId() {
		return tareaId;
	}

	public void setTareaId(String tareaId) {
		this.tareaId = tareaId;
	}

//	@Override
//	public String toString() {
//		return "(" + this.getId() + ") " + this.getNombre() + "";
//	}

}
