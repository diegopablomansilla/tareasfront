package com.ms.front.model;

import com.ms.front.commons.model.Entity;

public class Puesto extends Entity {

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String value) {
		this.nombre = this.emptyIsNull(value);
	}

	@Override
	public String toString() {
		return "(" + this.getId() + ") " + this.getNombre() + "";
	}

}
