package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class TareaCloseArgs extends ServiceArgs implements Cloneable {

	public final String KEY_HORAS = "horas";
	public final String KEY_ID = "id";

	private String horas;
	private String id;

	// ---------------------------------------------------------------

	public String getHoras() {
		return horas;
	}

	public void setHoras(String v) {
		this.horas = this.emptyIsNull(v);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getHoras() != null) {
			map.put(KEY_HORAS, this.getHoras());
		}

		if (this.getId() != null) {
			map.put(KEY_ID, this.getId());
		}

		return map;
	}

	@Override
	public TareaCloseArgs clone() {

		TareaCloseArgs other = new TareaCloseArgs();

		other.setHoras(getHoras());		
		other.setId(getId());

		return other;
	}

	@Override
	public String toString() {
		return this.getId();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("horas", this.getHoras());
		j.set("id", this.getId());
		

		return j.build();
	}

	// ---------------------------------------------------------------

}
