package com.ms.front.model;

import com.ms.front.commons.model.Entity;

public class OrdenFabricacionOLDNOUSAR extends Entity {

	private String nombre;
	private String cantidadTareas;
	private String cantidadTareasCerradas;
	private String cantidadTareasCerradasPorcentaje;
	private String cantidadHoras;
	private String cantidadHorasCalculadas;
	private String cantidadTareasTomadasEnEjecucion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String value) {
		this.nombre = this.emptyIsNull(value);
	}

	public String getCantidadTareas() {
		return cantidadTareas;
	}

	public void setCantidadTareas(String value) {
		this.cantidadTareas = this.emptyIsNull(value);
	}

	public String getCantidadTareasCerradas() {
		return cantidadTareasCerradas;
	}

	public void setCantidadTareasCerradas(String value) {
		this.cantidadTareasCerradas = this.emptyIsNull(value);
	}

	public String getCantidadTareasCerradasPorcentaje() {
		return cantidadTareasCerradasPorcentaje;
	}

	public void setCantidadTareasCerradasPorcentaje(String value) {
		this.cantidadTareasCerradasPorcentaje = this.emptyIsNull(value);
	}

	public String getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(String value) {
		this.cantidadHoras = this.emptyIsNull(value);
	}

	public String getCantidadHorasCalculadas() {
		return cantidadHorasCalculadas;
	}

	public void setCantidadHorasCalculadas(String value) {
		this.cantidadHorasCalculadas = this.emptyIsNull(value);
	}

	public String getCantidadTareasTomadasEnEjecucion() {
		return cantidadTareasTomadasEnEjecucion;
	}

	public void setCantidadTareasTomadasEnEjecucion(String value) {
		this.cantidadTareasTomadasEnEjecucion = this.emptyIsNull(value);
	}

//	@Override
//	public String toString() {
//		return "(" + this.getId() + ") " + this.getNombre() + "";
//	}

}
