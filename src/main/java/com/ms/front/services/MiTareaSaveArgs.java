package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class MiTareaSaveArgs extends ServiceArgs implements Cloneable {

	public final String KEY_TAREA = "tarea";
	public final String KEY_PERSONA = "persona";
	public final String KEY_COMENTARIO = "comentarios";

	private String tareaId;
	private String personaId;
	private String comentarios;

	// ---------------------------------------------------------------

	public String getTareaId() {
		return tareaId;
	}

	public void setTareaId(String v) {
		this.tareaId = this.emptyIsNull(v);
	}

	public String getPersonaId() {
		return personaId;
	}

	public void setPersonaId(String v) {
		this.personaId = this.emptyIsNull(v);
	}
	
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = this.emptyIsNull(comentarios);
	}
	

	// ---------------------------------------------------------------



	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getTareaId() != null) {
			map.put(KEY_TAREA, this.getTareaId());
		}

		if (this.getPersonaId() != null) {
			map.put(KEY_PERSONA, this.getPersonaId());
		}
		
		if (this.getComentarios() != null) {
			map.put(KEY_COMENTARIO, this.getComentarios());
		}

		return map;
	}

	@Override
	public MiTareaSaveArgs clone() {

		MiTareaSaveArgs other = new MiTareaSaveArgs();

		other.setTareaId(getTareaId());
		other.setPersonaId(getPersonaId());
		other.setComentarios(getComentarios());

		return other;
	}

	@Override
	public String toString() {
		return this.getTareaId();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("tarea", this.getTareaId());
		j.set("persona", this.getPersonaId());
		j.set("comentario", this.getComentarios());

		return j.build();
	}

	// ---------------------------------------------------------------

}
