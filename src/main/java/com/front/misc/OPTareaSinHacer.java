package com.front.misc;

import java.util.ArrayList;
import java.util.List;

class OPTareaSinHacer {

	private String id;
	private Integer numero;
	private String detalle;
	List<TareaSinHacer> tareas = new ArrayList<TareaSinHacer>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public boolean addTarea(TareaSinHacer e) {
		return tareas.add(e);
	}

	public List<TareaSinHacer> getTareas() {
		return tareas;
	}

	public void setTareas(List<TareaSinHacer> tareas) {
		this.tareas = tareas;
	}

	@Override
	public String toString() {
		return "(" + numero + ") " + detalle + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OPTareaSinHacer other = (OPTareaSinHacer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
