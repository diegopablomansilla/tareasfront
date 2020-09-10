package com.ms.front.commons.model;

import java.util.HashMap;
import java.util.Map;

public class PaginArgs extends ServiceArgs implements Cloneable {

	private final String PAGE_REQUEST_NEXT = "NEXT";
	private final String PAGE_REQUEST_BACK = "BACK";
	private final String PAGE_REQUEST_FIRST = "FIRST";
	private final String PAGE_REQUEST_LAST = "LAST";

	public final String KEY_DB = "db";
	public final String KEY_PAGE_REQUEST = "pr";
	public final String KEY_LAST_INDEX_OLD = "i";

	private String db;
	private String pageRequest;
	private Integer lastIndexOld;

	// ---------------------------------------------------------------

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = this.emptyIsNull(db);
	}

	public String getPageRequest() {
		return pageRequest;
	}

	public void setPageRequestNext() {
		this.pageRequest = PAGE_REQUEST_NEXT;
	}

	public void setPageRequestBack() {
		this.pageRequest = PAGE_REQUEST_BACK;
	}

	public void setPageRequestFirst() {
		this.pageRequest = PAGE_REQUEST_FIRST;
	}

	public void setPageRequestLast() {
		this.pageRequest = PAGE_REQUEST_LAST;
	}

	private void setPageRequest(String pageRequest) {
		this.pageRequest = pageRequest;
	}

	public Integer getLastIndexOld() {
		return lastIndexOld;
	}

	public void setLastIndexOld(Integer lastIndexOld) {
		if (lastIndexOld == null) {
			lastIndexOld = 0;
		}
		this.lastIndexOld = lastIndexOld;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();

		if (this.getDb() != null) {
			map.put(KEY_DB, this.getDb());
		}

		if (this.getPageRequest() != null) {
			map.put(KEY_PAGE_REQUEST, this.getPageRequest());
		}

		if (this.getLastIndexOld() != null) {
			map.put(KEY_LAST_INDEX_OLD, this.getLastIndexOld().toString());
		}

		return map;
	}

	@Override
	protected PaginArgs clone() {

		PaginArgs other = new PaginArgs();

		other.setDb(getDb());
		other.setPageRequest(getPageRequest());
		other.setLastIndexOld(getLastIndexOld());

		return other;
	}

	@Override
	public String toString() {
		return "PaginArgs [getDb()=" + getDb() + ", getPageRequest()=" + getPageRequest() + ", getLastIndexOld()="
				+ getLastIndexOld() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDb() == null) ? 0 : getDb().hashCode());
		result = prime * result + ((getLastIndexOld() == null) ? 0 : getLastIndexOld().hashCode());
		result = prime * result + ((getPageRequest() == null) ? 0 : getPageRequest().hashCode());
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
		PaginArgs other = (PaginArgs) obj;
		if (getDb() == null) {
			if (other.getDb() != null)
				return false;
		} else if (!getDb().equals(other.getDb()))
			return false;
		if (getLastIndexOld() == null) {
			if (other.getLastIndexOld() != null)
				return false;
		} else if (!getLastIndexOld().equals(other.getLastIndexOld()))
			return false;
		if (getPageRequest() == null) {
			if (other.getPageRequest() != null)
				return false;
		} else if (!getPageRequest().equals(other.getPageRequest()))
			return false;
		return true;
	}

	// ---------------------------------------------------------------

}
