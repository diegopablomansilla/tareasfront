package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class TareaPersonaSaveArgs extends ServiceArgs implements Cloneable {

	public final String KEY_PERSONA_ID = "persona";
	public final String KEY_TOMA = "toma";
	public final String KEY_SUELTA = "suelta";
	public final String KEY_TAREA_ID = "tarea";

	private String personaId;
	private String toma;
	private String suelta;
	private String tareaId;

	// ---------------------------------------------------------------

	public String getTareaId() {
		return tareaId;
	}

	public void setTareaId(String id) {
		this.tareaId = id;
	}

	public String getPersonaId() {
		return personaId;
	}

	public void setPersonaId(String v) {
		this.personaId = this.emptyIsNull(v);
	}

	public String getToma() {
		return toma;
	}

	public void setToma(String v) {
		this.toma = this.emptyIsNull(v);
	}

	public String getSuelta() {
		return suelta;
	}

	public void setSuelta(String v) {
		this.suelta = this.emptyIsNull(v);
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getTareaId() != null) {
			map.put(KEY_TAREA_ID, this.getTareaId());
		}

		if (this.getPersonaId() != null) {
			map.put(KEY_PERSONA_ID, this.getPersonaId());
		}

		if (this.getToma() != null) {
			map.put(KEY_TOMA, this.getToma());
		}

		if (this.getSuelta() != null) {
			map.put(KEY_SUELTA, this.getSuelta());
		}

		return map;
	}

	@Override
	public TareaPersonaSaveArgs clone() {

		TareaPersonaSaveArgs other = new TareaPersonaSaveArgs();

		other.setTareaId(getTareaId());
		other.setPersonaId(getPersonaId());
		other.setToma(getToma());
		other.setSuelta(getSuelta());

		return other;
	}

	@Override
	public String toString() {
		return this.getTareaId();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set(KEY_TAREA_ID, this.getTareaId());
		j.set(KEY_PERSONA_ID, this.getPersonaId());
		j.set(KEY_TOMA, this.getToma());
		j.set(KEY_SUELTA, this.getSuelta());

		return j.build();
	}

	// ---------------------------------------------------------------

}
