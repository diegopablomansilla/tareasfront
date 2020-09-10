package com.ms.front.services.views;

import com.ms.front.model.MiTarea;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MiTareaEditView extends VerticalLayout {

	private MiTarea tarea;
//	private MiTareaService service;

	public MiTareaEditView(MiTarea tarea) {

		this.tarea = tarea;

//		service = new MiTareaService();

		render();

	}

	public void setTarea(MiTarea tarea) {
//		tareaBinder.setBean(tarea);

		String idValue = "";
		String nombreValue = "";
		String ordenValue = "";
		String seccionValue = "";
		String puestoValue = "";
		String detalleValue = "";
		String creadaValue = "";
		String url = "";

		if (tarea != null) {
			idValue = tarea.getId();
			nombreValue = tarea.getNombre();
			ordenValue = tarea.getOrdenFabricacion();
			seccionValue = tarea.getSeccion();
			puestoValue = tarea.getPuesto();
			detalleValue = tarea.getDetalle();
			creadaValue = tarea.getFecha();
			url = tarea.getAdjunto() != null ? tarea.getAdjunto() : "";
		}

		tituloTarea.setText("Tarea tomada (" + idValue + ") ");
		nombre.setText(nombreValue);
		orden.setText(ordenValue);
		seccion.setText(seccionValue);
		puesto.setText(puestoValue);
		detalle.setText(detalleValue);
		creada.setText(creadaValue);
		creada.setText(creadaValue);
		anchor.setHref(url);
		anchor.setVisible(
				tarea != null && tarea.getAdjunto() != null && tarea.getAdjunto().endsWith("?id=" + tarea.getId()));
	}

	private void render() {
		renderForm(tarea);
	}

	private void renderForm(MiTarea tarea) {

		// ---------------------------------------------------------------------------

		String idValue = "";
		String nombreValue = "";
		String ordenValue = "";
		String seccionValue = "";
		String puestoValue = "";
		String detalleValue = "";
		String creadaValue = "";
		String url = "";

		if (tarea != null) {
			idValue = tarea.getId();
			nombreValue = tarea.getNombre();
			ordenValue = tarea.getOrdenFabricacion();
			seccionValue = tarea.getSeccion();
			puestoValue = tarea.getPuesto();
			detalleValue = tarea.getDetalle();
			creadaValue = tarea.getFecha();
			url = tarea.getAdjunto() != null ? tarea.getAdjunto() : "";
		}

		tituloTarea = new H3("Tarea tomada (" + idValue + ") ");

		nombreTitulo = new H5("Nombre");
		nombre = new Label(nombreValue);

		ordenTitulo = new H5("Orden");
		orden = new Label(ordenValue);

		seccionTitulo = new H5("Sección");
		seccion = new Label(seccionValue);

		puestoTitulo = new H5("Puesto");
		puesto = new Label(puestoValue);

		detalleTitulo = new H5("Detalle");
		detalle = new Label(detalleValue);

		creadaTitulo = new H5("Creada");
		creada = new Label(creadaValue);

		creada = new Label();
		creada.setText(creadaValue);

		add(tituloTarea, nombreTitulo, nombre, ordenTitulo, orden, seccionTitulo, seccion, puestoTitulo, puesto,
				detalleTitulo, detalle, creadaTitulo, creada);

		anchor = new Anchor(url, "Adjunto");
		add(anchor);
		anchor.setVisible(
				tarea != null && tarea.getAdjunto() != null && tarea.getAdjunto().endsWith("?id=" + tarea.getId()));

		setMaxWidth("800px");
		addClassName("centered-content");

		// ---------------------------------------------------------------------------

	}

	// -----------------------------------------------------------

	private H3 tituloTarea;

	private H5 nombreTitulo;
	private Label nombre;

	private H5 seccionTitulo;
	private Label seccion;

	private H5 puestoTitulo;
	private Label puesto;

	private H5 ordenTitulo;
	private Label orden;

	private H5 detalleTitulo;
	private Label detalle;

	private H5 creadaTitulo;
	private Label creada;

	private Anchor anchor;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067862213020325L;

} // END
