package com.ms.front.commons.model;

import java.util.HashMap;
import java.util.Map;

public class PostArgs extends ServiceArgs implements Cloneable {

	public final String KEY_DB = "db";

	private String db;

	// ---------------------------------------------------------------

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = this.emptyIsNull(db);
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();

		if (this.getDb() != null) {
			map.put(KEY_DB, this.getDb());
		}

		return map;
	}

	@Override
	protected PostArgs clone() {

		PostArgs other = new PostArgs();

		other.setDb(getDb());

		return other;
	}

	@Override
	public String toString() {
		return "NoPaginArgs [getDb()=" + getDb() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDb() == null) ? 0 : getDb().hashCode());

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
		PostArgs other = (PostArgs) obj;
		if (getDb() == null) {
			if (other.getDb() != null)
				return false;
		} else if (!getDb().equals(other.getDb()))
			return false;

		return true;
	}

	// ---------------------------------------------------------------

}
