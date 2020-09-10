package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class TareaCreateArgs extends ServiceArgs implements Cloneable {

	public final String KEY_NOMBRE = "nombre";
	public final String KEY_DETALLE = "detalle";
	public final String KEY_ORDEN_FABRICACION = "orden";
	public final String KEY_SECCION = "seccion";
	public final String KEY_PUESTO = "puesto";

	private String nombre;
	private String detalle;
	private String ordenFabricacion;
	private String seccion;
	private String puesto;

	// ---------------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String v) {
		this.nombre = this.emptyIsNull(v);
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String v) {
		this.detalle = this.emptyIsNull(v);
	}

	public String getOrdenFabricacion() {
		return ordenFabricacion;
	}

	public void setOrdenFabricacion(String v) {
		this.ordenFabricacion = this.emptyIsNull(v);
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String v) {
		this.seccion = this.emptyIsNull(v);
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	// ---------------------------------------------------------------

	public Map<String, String> toMap() {

		Map<String, String> map = new HashMap<String, String>();

		if (this.getNombre() != null) {
			map.put(KEY_NOMBRE, this.getNombre());
		}

		if (this.getDetalle() != null) {
			map.put(KEY_DETALLE, this.getDetalle());
		}

		if (this.getOrdenFabricacion() != null) {
			map.put(KEY_ORDEN_FABRICACION, this.getOrdenFabricacion());
		}

		if (this.getSeccion() != null) {
			map.put(KEY_SECCION, this.getSeccion());
		}

		if (this.getPuesto() != null) {
			map.put(KEY_PUESTO, this.getPuesto());
		}

		return map;
	}

	@Override
	public TareaCreateArgs clone() {

		TareaCreateArgs other = new TareaCreateArgs();

		other.setNombre(getNombre());
		other.setDetalle(getDetalle());
		other.setOrdenFabricacion(getOrdenFabricacion());
		other.setSeccion(getSeccion());
		other.setPuesto(getPuesto());

		return other;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("nombre", this.getNombre());
		j.set("detalle", this.getDetalle());
		j.set("orden", this.getOrdenFabricacion());
		j.set("seccion", this.getSeccion());
		j.set("puesto", this.getPuesto());

		return j.build();
	}

	// ---------------------------------------------------------------

}
