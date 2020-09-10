package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import com.ms.front.commons.model.NoPaginArgs;

public class MiTareaEnEjecucionNoPaginArgs extends NoPaginArgs implements Cloneable {

	public final String KEY_PERSONA = "persona";

	private String persona;

	// ---------------------------------------------------------------

	public String getPersona() {
		return persona;
	}

	public void setPersona(String v) {
		this.persona = this.emptyIsNull(v);
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getPersona() != null) {
			map.put(KEY_PERSONA, this.getPersona());
		}

		return map;
	}

	@Override
	public MiTareaEnEjecucionNoPaginArgs clone() {

		MiTareaEnEjecucionNoPaginArgs other = new MiTareaEnEjecucionNoPaginArgs();

		other.setPersona(getPersona());

		return other;
	}

	@Override
	public String toString() {
		return getPersona();
	}

	// ---------------------------------------------------------------

}
