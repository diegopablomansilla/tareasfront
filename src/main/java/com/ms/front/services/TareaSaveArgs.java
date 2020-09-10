package com.ms.front.services;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;

import com.ms.front.commons.model.JsonObjectWrapper;
import com.ms.front.commons.model.ServiceArgs;

public class TareaSaveArgs extends ServiceArgs implements Cloneable {

	public final String KEY_NOMBRE = "nombre";
	public final String KEY_DETALLE = "detalle";
	public final String KEY_SECCION = "seccion";
	public final String KEY_PUESTO = "puesto";
	public final String KEY_ORDEN_FABRICACION = "orden";
	public final String KEY_ID = "id";

	private String nombre;
	private String detalle;
	private String seccion;
	private String puesto;
	private String ordenFabricacion;
	private String id;

	// ---------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

		if (this.getId() != null) {
			map.put(KEY_ID, this.getId());
		}

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
	public TareaSaveArgs clone() {

		TareaSaveArgs other = new TareaSaveArgs();

		other.setId(getId());
		other.setNombre(getNombre());
		other.setDetalle(getDetalle());
		other.setSeccion(getSeccion());
		other.setPuesto(getPuesto());
		other.setOrdenFabricacion(getOrdenFabricacion());

		return other;
	}

	@Override
	public String toString() {
		return this.getId();
	}

	public JsonObject toJson() {

		JsonObjectWrapper j = new JsonObjectWrapper();

		j.set("id", this.getId());
		j.set("nombre", this.getNombre());
		j.set("detalle", this.getDetalle());
		j.set("orden", this.getOrdenFabricacion());
		j.set("seccion", this.getSeccion());
		j.set("puesto", this.getPuesto());

		return j.build();
	}

	// ---------------------------------------------------------------

}
