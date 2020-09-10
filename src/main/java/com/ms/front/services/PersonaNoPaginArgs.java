package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import com.ms.front.commons.model.NoPaginArgs;

public class PersonaNoPaginArgs extends NoPaginArgs implements Cloneable {

	public final String KEY_SECCION = "seccion";
	public final String KEY_PUESTO = "puesto";	

	private String seccionId;
	private String puestoId;

	// ---------------------------------------------------------------

	public String getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(String v) {
		this.seccionId = this.emptyIsNull(v);
	}

	public String getPuestoId() {
		return puestoId;
	}

	public void setPuestoId(String v) {
		this.puestoId = this.emptyIsNull(v);
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getSeccionId() != null) {
			map.put(KEY_SECCION, this.getSeccionId());
		}

		if (this.getPuestoId() != null) {
			map.put(KEY_PUESTO, this.getPuestoId());
		}

		return map;
	}

	@Override
	public PersonaNoPaginArgs clone() {

		PersonaNoPaginArgs other = new PersonaNoPaginArgs();

		other.setSeccionId(getSeccionId());
		other.setPuestoId(getPuestoId());

		return other;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((puestoId == null) ? 0 : puestoId.hashCode());		
		result = prime * result + ((seccionId == null) ? 0 : seccionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaNoPaginArgs other = (PersonaNoPaginArgs) obj;
		if (puestoId == null) {
			if (other.puestoId != null)
				return false;
		} else if (!puestoId.equals(other.puestoId))
			return false;		
		if (seccionId == null) {
			if (other.seccionId != null)
				return false;
		} else if (!seccionId.equals(other.seccionId))
			return false;
		return true;
	}

	// ---------------------------------------------------------------

}
