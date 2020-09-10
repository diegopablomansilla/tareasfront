package com.ms.front.model;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import com.ms.front.commons.model.Entity;

public class Persona extends Entity {

	private String nombre;
	private String apellido;
	private String seccionId;
	private String puestoId;
	private String rol;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String value) {
		this.nombre = this.emptyIsNull(value);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String value) {
		this.apellido = this.emptyIsNull(value);
	}

	public String getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(String value) {
		this.seccionId = this.emptyIsNull(value);
	}

	public String getPuestoId() {
		return puestoId;
	}

	public void setPuestoId(String value) {
		this.puestoId = this.emptyIsNull(value);
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String v) {
		this.rol = this.emptyIsNull(v);
	}

	@Override
	public String toString() {
//		return "(" + this.getId() + ") " + this.getApellido() + " " + this.getNombre() + "";
		return this.getApellido() + " " + this.getNombre() + "";
	}

	public void fromJson(String json) {
		if (json == null || json.trim().length() == 0) {
			return;
		}

		JsonObject jo = Json.createReader(new StringReader(json)).readObject();

		fromJson(jo);
	}

	private void fromJson(JsonObject jo) {

		if (jo == null) {
			return;
		}

		if (jo.isEmpty()) {
			return;
		}

//		String att = "payload";
//
//		if (jo.containsKey(att) && jo.isNull(att)) {
//			return;
//		}
//
//		jo = jo.getJsonObject(att);

		String att = "id";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setId(jo.getString(att));
		}

		att = "nombre";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setNombre(jo.getString(att));
		}

		att = "apellido";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setApellido(jo.getString(att));
		}

		att = "seccionId";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setSeccionId(jo.getString(att));
		}

		att = "puestoId";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setPuestoId(jo.getString(att));
		}
		
		att = "rol";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setRol(jo.getString(att));
		}

	}

}
