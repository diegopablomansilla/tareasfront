package com.ms.front.commons.model;

import java.util.HashMap;
import java.util.Map;

public class ByIdArgs extends ServiceArgs implements Cloneable {

	public final String KEY_DB = "db";
	public final String KEY_ID = "id";

	private String db;
	private String id;

	// ---------------------------------------------------------------

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = this.emptyIsNull(db);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = this.emptyIsNull(id);
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();

		if (this.getDb() != null) {
			map.put(KEY_DB, this.getDb());
		}

		if (this.getId() != null) {
			map.put(KEY_ID, this.getId());
		}

		return map;
	}

	@Override
	protected ByIdArgs clone() {

		ByIdArgs other = new ByIdArgs();

		other.setDb(getDb());
		other.setId(getId());

		return other;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((db == null) ? 0 : db.hashCode());
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
		ByIdArgs other = (ByIdArgs) obj;
		if (db == null) {
			if (other.db != null)
				return false;
		} else if (!db.equals(other.db))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ByIdArgs [db=" + db + ", id=" + id + "]";
	}

	// ---------------------------------------------------------------

}
