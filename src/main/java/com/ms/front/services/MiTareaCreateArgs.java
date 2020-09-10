package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class MiTareaCreateArgs extends ServiceArgs implements Cloneable {

	public final String KEY_TAREA = "tarea";
	public final String KEY_PERSONA = "persona";

	private String tareaId;
	private String personaId;

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

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getTareaId() != null) {
			map.put(KEY_TAREA, this.getTareaId());
		}

		if (this.getPersonaId() != null) {
			map.put(KEY_PERSONA, this.getPersonaId());
		}

		return map;
	}

	@Override
	public MiTareaCreateArgs clone() {

		MiTareaCreateArgs other = new MiTareaCreateArgs();

		other.setTareaId(getTareaId());
		other.setPersonaId(getPersonaId());

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

		return j.build();
	}

	// ---------------------------------------------------------------

}
