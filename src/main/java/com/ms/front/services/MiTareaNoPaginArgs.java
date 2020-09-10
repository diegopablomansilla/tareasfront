package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import com.ms.front.commons.model.NoPaginArgs;

public class MiTareaNoPaginArgs extends NoPaginArgs implements Cloneable {

	public final String KEY_SECCION = "seccion";
	public final String KEY_PUESTO = "puesto";

	private String seccion;
	private String puesto;

	// ---------------------------------------------------------------

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String v) {
		this.seccion = this.emptyIsNull(v);
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String v) {
		this.puesto = this.emptyIsNull(v);
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getSeccion() != null) {
			map.put(KEY_SECCION, this.getSeccion());
		}

		if (this.getPuesto() != null) {
			map.put(KEY_PUESTO, this.getPuesto());
		}

		return map;
	}

	@Override
	public MiTareaNoPaginArgs clone() {

		MiTareaNoPaginArgs other = new MiTareaNoPaginArgs();

		other.setSeccion(getSeccion());		
		other.setPuesto(getPuesto());

		return other;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();		
		result = prime * result + ((puesto == null) ? 0 : puesto.hashCode());
		result = prime * result + ((seccion == null) ? 0 : seccion.hashCode());
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
		MiTareaNoPaginArgs other = (MiTareaNoPaginArgs) obj;
		
		if (puesto == null) {
			if (other.puesto != null)
				return false;
		} else if (!puesto.equals(other.puesto))
			return false;
		if (seccion == null) {
			if (other.seccion != null)
				return false;
		} else if (!seccion.equals(other.seccion))
			return false;
		return true;
	}

	// ---------------------------------------------------------------

}
