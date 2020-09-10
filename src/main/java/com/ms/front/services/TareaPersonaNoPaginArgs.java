package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import com.ms.front.commons.model.NoPaginArgs;

public class TareaPersonaNoPaginArgs extends NoPaginArgs implements Cloneable {

	public final String KEY_TAREA = "tarea";

	private String tarea;

	// ---------------------------------------------------------------

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getTarea() != null) {
			map.put(KEY_TAREA, this.getTarea());
		}

		return map;
	}

	@Override
	public TareaPersonaNoPaginArgs clone() {

		TareaPersonaNoPaginArgs other = new TareaPersonaNoPaginArgs();

		other.setTarea(getTarea());

		return other;
	}

	@Override
	public String toString() {
		return getTarea();
	}

	// ---------------------------------------------------------------

}
