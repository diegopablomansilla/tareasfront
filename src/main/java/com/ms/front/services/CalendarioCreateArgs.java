package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class CalendarioCreateArgs extends ServiceArgs implements Cloneable {

	public final String KEY_INICIO = "inicio";
	public final String KEY_CIERRE = "cierre";
	public final String KEY_FECHA = "fecha";

	private String horaInicio;
	private String horaCierre;
	private String fecha;

	// ---------------------------------------------------------------

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String v) {
		this.fecha = this.emptyIsNull(v);
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

		if (this.getFecha() != null) {
			map.put(KEY_FECHA, this.getFecha());
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
	public CalendarioCreateArgs clone() {

		CalendarioCreateArgs other = new CalendarioCreateArgs();

		other.setFecha(getFecha());
		other.setHoraInicio(getHoraInicio());
		other.setHoraCierre(getHoraCierre());

		return other;
	}

	@Override
	public String toString() {
		return this.getFecha();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set(KEY_FECHA, this.getFecha());
		j.set(KEY_INICIO, this.getHoraInicio());
		j.set(KEY_CIERRE, this.getHoraCierre());

		return j.build();
	}

	// ---------------------------------------------------------------

}
