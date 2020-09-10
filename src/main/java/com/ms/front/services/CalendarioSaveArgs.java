package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class CalendarioSaveArgs extends ServiceArgs implements Cloneable {

	public final String KEY_INICIO = "inicio";
	public final String KEY_CIERRE = "cierre";
	public final String KEY_ID = "id";

	private String horaInicio;
	private String horaCierre;
	private String id;

	// ---------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String v) {
		this.horaInicio = this.emptyIsNull(v);
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String v) {
		this.horaCierre = this.emptyIsNull(v);
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getId() != null) {
			map.put(KEY_ID, this.getId());
		}

		if (this.getHoraInicio() != null) {
			map.put(KEY_INICIO, this.getHoraInicio());
		}

		if (this.getHoraCierre() != null) {
			map.put(KEY_CIERRE, this.getHoraCierre());
		}

		return map;
	}

	@Override
	public CalendarioSaveArgs clone() {

		CalendarioSaveArgs other = new CalendarioSaveArgs();

		other.setId(getId());
		other.setHoraInicio(getHoraInicio());
		other.setHoraCierre(getHoraCierre());

		return other;
	}

	@Override
	public String toString() {
		return this.getId();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set(KEY_ID, this.getId());
		j.set(KEY_INICIO, this.getHoraInicio());
		j.set(KEY_CIERRE, this.getHoraCierre());

		return j.build();
	}

	// ---------------------------------------------------------------

}
