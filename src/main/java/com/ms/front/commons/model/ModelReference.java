package com.ms.front.commons.model;

public class ModelReference extends Entity {

	// ---------------------------------------------------------------------------------------------------------------------------

	private String id;
	private String descripcion;

	// ---------------------------------------------------------------------------------------------------------------------------

	public ModelReference() {
		setDefaultValues();
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = emptyIsNull(value);
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String value) {
		this.descripcion = emptyIsNull(value);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	public String toString() {
		return this.getDescripcion();
	}

	public void setNull() {
		id = null;
		descripcion = null;
	}

	public void setDefaultValues() {
		id = null;
		descripcion = null;
	}

	public ModelReference clone() {

		ModelReference other = new ModelReference();

		other.setId(this.getId());

		other.setDescripcion(this.getDescripcion());

		// -------------------------------------------------------------------

		return other;

		// -------------------------------------------------------------------
	}

	public boolean equalsFull(Object obj) {

		if (super.equals(obj) == false) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		ModelReference other = (ModelReference) obj;

		// -------------------------------------------------------------------

		if (other.getDescripcion() == null && this.getDescripcion() != null) {
			return false;
		}

		if (other.getDescripcion() != null && this.getDescripcion() == null) {
			return false;
		}

		if (other.getDescripcion() != null && this.getDescripcion() != null) {

			if (other.getDescripcion().equals(this.getDescripcion()) == false) {
				return false;
			}

		}

		// -------------------------------------------------------------------

		return true;

		// -------------------------------------------------------------------
	}

} // END CLASS -----------------------------------------------------------------
