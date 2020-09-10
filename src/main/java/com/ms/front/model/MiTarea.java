package com.ms.front.model;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import com.ms.front.commons.model.Entity;

public class MiTarea extends Entity {

	private String id;
	private String nombre;
	private String detalle;
	private String ordenFabricacion;
	private String seccion;
	private String puesto;
	private String adjunto;
	private String fecha;
	private String ordenFabricacionId;
	private String seccionId;
	private String puestoId;
	private String comentario;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getOrdenFabricacion() {
		return ordenFabricacion;
	}

	public void setOrdenFabricacion(String ordenFabricacion) {
		this.ordenFabricacion = ordenFabricacion;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getOrdenFabricacionId() {
		return ordenFabricacionId;
	}

	public void setOrdenFabricacionId(String ordenFabricacionId) {
		this.ordenFabricacionId = ordenFabricacionId;
	}

	public String getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(String seccionId) {
		this.seccionId = seccionId;
	}

	public String getPuestoId() {
		return puestoId;
	}

	public void setPuestoId(String puestoId) {
		this.puestoId = puestoId;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "(" + this.getId() + ") " + this.getNombre();
	}

	public void fromJson(String json) {
		if (json == null || json.trim().length() == 0) {
			return;
		}

		JsonObject jo = Json.createReader(new StringReader(json)).readObject();

		fromJson(jo);
	}

	private void fromJson(JsonObject jo) {

		if (jo == null) {
			return;
		}

		if (jo.isEmpty()) {
			return;
		}

//		String att = "payload";
//
//		if (jo.containsKey(att) && jo.isNull(att)) {
//			return;
//		}
//
//		jo = jo.getJsonObject(att);

		String att = "id";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setId(jo.getString(att));
		}

		att = "nombre";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setNombre(jo.getString(att));
		}

		att = "detalle";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setDetalle(jo.getString(att));
		}

		att = "ordenFabricacion";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setOrdenFabricacion(jo.getString(att));
		}

		att = "seccion";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setSeccion(jo.getString(att));
		}

		att = "puesto";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setPuesto(jo.getString(att));
		}

		att = "adjunto";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setAdjunto(jo.getString(att));
		}

		att = "fecha";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setFecha(jo.getString(att));
		}

		att = "ordenFabricacionId";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setOrdenFabricacionId(jo.getString(att));
		}

		att = "seccionId";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setSeccionId(jo.getString(att));
		}

		att = "puestoId";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setPuestoId(jo.getString(att));
		}

		att = "comentario";

		if (jo.containsKey(att) && !jo.isNull(att)) {
			setComentario(jo.getString(att));
		}

	}

}
