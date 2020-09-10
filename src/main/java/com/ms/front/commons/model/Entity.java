package com.ms.front.commons.model;

public class Entity extends ObjectModel {

	protected String id;

	// ---------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = this.emptyIsNull(id);
	}

	// ---------------------------------------------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		Entity other = (Entity) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entity [id=" + this.getId() + "]";
	}

}
