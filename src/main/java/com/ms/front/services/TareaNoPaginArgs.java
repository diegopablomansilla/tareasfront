package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import com.ms.front.commons.model.NoPaginArgs;

public class TareaNoPaginArgs extends NoPaginArgs implements Cloneable {

	public final String KEY_ORDEN_FABRICACION = "orden";
	public final String KEY_CERRADA = "cerrada";
	public final String KEY_MESES = "meses";	

	private String ordenFabricacion;
	private String cerrada;
	private String meses;

	// ---------------------------------------------------------------

	public String getOrdenFabricacion() {
		return ordenFabricacion;
	}

	public void setOrdenFabricacion(String v) {
		this.ordenFabricacion = this.emptyIsNull(v);
	}

	public String getCerrada() {
		return cerrada;
	}

	public void setCerrada(String cerrada) {
		this.cerrada = this.emptyIsNull(cerrada);
	}

	public String getMeses() {
		return meses;
	}

	public void setMeses(String meses) {
		this.meses = meses;
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getOrdenFabricacion() != null) {
			map.put(KEY_ORDEN_FABRICACION, this.getOrdenFabricacion());
		}

		if (this.getCerrada() != null) {
			map.put(KEY_CERRADA, this.getCerrada());
		}

		if (this.getMeses() != null) {
			map.put(KEY_MESES, this.getMeses());
		}

		return map;
	}

	@Override
	public TareaNoPaginArgs clone() {

		TareaNoPaginArgs other = new TareaNoPaginArgs();

		other.setOrdenFabricacion(getOrdenFabricacion());
		other.setCerrada(getCerrada());
		other.setMeses(getMeses());

		return other;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cerrada == null) ? 0 : cerrada.hashCode());
		result = prime * result + ((meses == null) ? 0 : meses.hashCode());
		result = prime * result + ((ordenFabricacion == null) ? 0 : ordenFabricacion.hashCode());
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
		TareaNoPaginArgs other = (TareaNoPaginArgs) obj;
		if (cerrada == null) {
			if (other.cerrada != null)
				return false;
		} else if (!cerrada.equals(other.cerrada))
			return false;
		if (meses == null) {
			if (other.meses != null)
				return false;
		} else if (!meses.equals(other.meses))
			return false;
		if (ordenFabricacion == null) {
			if (other.ordenFabricacion != null)
				return false;
		} else if (!ordenFabricacion.equals(other.ordenFabricacion))
			return false;
		return true;
	}

	// ---------------------------------------------------------------

}
