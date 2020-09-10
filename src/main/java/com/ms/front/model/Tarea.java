package com.ms.front.model;

import com.ms.front.commons.model.Entity;

public class Tarea extends Entity {

	private String id;
	private String nombre;
	private String detalle;
	private String ordenFabricacion;
	private String seccion;
	private String puesto;
	private String estado;
	private String horas;
	private String adjunto;
	private String fecha;
	private String cantidadVecesTomada;
	private String horasCalculadas;
	private String personas;
	private String cantidadEnEjecucion;
	private String personasEnEjecucion;
	private String ordenFabricacionId;
	private String seccionId;
	private String puestoId;
	private String cerrada;	

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
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

	public String getCantidadVecesTomada() {
		return cantidadVecesTomada;
	}

	public void setCantidadVecesTomada(String cantidadVecesTomada) {
		this.cantidadVecesTomada = cantidadVecesTomada;
	}

	public String getHorasCalculadas() {
		return horasCalculadas;
	}

	public void setHorasCalculadas(String horasCalculadas) {
		this.horasCalculadas = horasCalculadas;
	}

	public String getPersonas() {
		return personas;
	}

	public void setPersonas(String personas) {
		this.personas = personas;
	}

	public String getCantidadEnEjecucion() {
		return cantidadEnEjecucion;
	}

	public void setCantidadEnEjecucion(String cantidadEnEjecucion) {
		this.cantidadEnEjecucion = cantidadEnEjecucion;
	}

	public String getPersonasEnEjecucion() {
		return personasEnEjecucion;
	}

	public void setPersonasEnEjecucion(String personasEnEjecucion) {
		this.personasEnEjecucion = personasEnEjecucion;
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

	public String getCerrada() {
		return cerrada;
	}

	public void setCerrada(String cerrada) {
		this.cerrada = cerrada;
	}

}
