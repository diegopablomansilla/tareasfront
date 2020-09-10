package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class LoginArgs extends ServiceArgs implements Cloneable {

	public final String KEY_USER = "user";
	public final String KEY_PASS = "pass";

	private String user;
	private String pass;

	// ---------------------------------------------------------------

	public String getUser() {
		return user;
	}

	public void setUser(String v) {
		this.user = this.emptyIsNull(v);
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String v) {
		this.pass = this.emptyIsNull(v);
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getUser() != null) {
			map.put(KEY_USER, this.getUser());
		}

		if (this.getPass() != null) {
			map.put(KEY_PASS, this.getPass());
		}

		return map;
	}

	@Override
	public LoginArgs clone() {

		LoginArgs other = new LoginArgs();

		other.setUser(getUser());
		other.setPass(getPass());

		return other;
	}

	@Override
	public String toString() {
		return this.getUser();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("user", this.getUser());
		j.set("pass", this.getPass());

		return j.build();
	}

	// ---------------------------------------------------------------

}
