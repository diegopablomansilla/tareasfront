package com.ms.front.model;

import com.ms.front.commons.model.Entity;

public class TareaPersona extends Entity {

	private String apellido;
	private String nombre;
	private String tomaTarea;
	private String sueltaTarea;
	private String comentarios;
	private String tareaId;

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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
