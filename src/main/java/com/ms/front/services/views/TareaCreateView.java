package com.ms.front.services.views;

import java.util.List;

import com.ms.front.MainView;
import com.ms.front.model.OrdenFabricacion;
import com.ms.front.model.Persona;
import com.ms.front.model.Puesto;
import com.ms.front.model.Seccion;
import com.ms.front.model.Tarea;
import com.ms.front.services.OrdenFabricacionService;
import com.ms.front.services.PuestoService;
import com.ms.front.services.SeccionService;
import com.ms.front.services.TareaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TareaCreateView extends FormLayout {

	private MainView main;
	private Persona persona;
	private Tarea tarea;
	private boolean cerrada;

	public TareaCreateView(MainView main, Persona persona) {

		this.main = main;
		this.persona = persona;
		this.tarea = new Tarea();

		cerrada = "CERRADA".equals(tarea.getEstado());

		render();
	}

	private void render() {

		// ---------------------------------------------------------------------------

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());

		Button guardarTarea = new Button("Crear tarea");
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		guardarTarea.setIcon(new Icon(VaadinIcon.CLOUD_UPLOAD));
//		editarTarea.addClassName("centered-content");
		guardarTarea.addClickListener(event -> guardarTarea());
		guardarTarea.setVisible(cerrada == false);

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, guardarTarea);

		add(botonera);

		// ----------------------------------------------

//		H5 tituloPersona = new H5(persona.toString());
//
//		HorizontalLayout hs = new HorizontalLayout();
//		hs.setSpacing(false);
//
//		hs.add(titulo, tituloPersona);

//		addClassName("centered-content");

		renderForm(tarea);

	}

	private void renderForm(Tarea tarea) {

		tareaBinder.setBean(tarea);

		List<OrdenFabricacion> ordenes = new OrdenFabricacionService().findAll();
		List<Seccion> secciones = new SeccionService().findAll();
		List<Puesto> puestos = new PuestoService().findAll();

		boolean readOnly = cerrada;

		// ---------------------------------------------------------------------------

		tituloTarea = new H3("Nueva tarea");

		nombre = new TextField();
		nombre.setLabel("Nombre");
		nombre.setClearButtonVisible(true);
		nombre.setRequiredIndicatorVisible(true);
		nombre.setReadOnly(readOnly);

		detalle = new TextArea();
		detalle.setLabel("Detalle");
		detalle.setClearButtonVisible(true);
		detalle.setRequiredIndicatorVisible(true);
		detalle.setReadOnly(readOnly);
		detalle.setWidthFull();
		
		orden = new ComboBox<OrdenFabricacion>();
		orden.setLabel("Orden");
		orden.setRequiredIndicatorVisible(true);
		orden.setReadOnly(readOnly);
		orden.setClearButtonVisible(true);
		orden.setItems(ordenes);	

		seccion = new ComboBox<Seccion>();
		seccion.setLabel("Seccion");
		seccion.setRequiredIndicatorVisible(true);
		seccion.setReadOnly(readOnly);
		seccion.setClearButtonVisible(true);
		seccion.setItems(secciones);

		puesto = new ComboBox<Puesto>();
		puesto.setLabel("Puesto");
		puesto.setReadOnly(readOnly);
		puesto.setClearButtonVisible(true);
		puesto.setItems(puestos);

		add(tituloTarea, nombre, orden, seccion, puesto, detalle);

		setColspan(tituloTarea, 3);
		setColspan(detalle, 3);
		setColspan(orden, 3);

		setMaxWidth("800px");
		addClassName("centered-content");

		// ---------------------------------------------------------------------------

	}

	private void guardarTarea() {
		Tarea selected = tareaBinder.getBean();

		if (selected != null) {

			if (nombre.isInvalid()) {
				Notification notification = new Notification("Nombre es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}
			if (seccion.isInvalid() || seccion.getValue() == null) {
				Notification notification = new Notification("Seccion es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}

			TareaService service = new TareaService();

			selected.setNombre(nombre.getValue());
			selected.setDetalle(detalle.getValue());
			if (orden.getValue() != null) {
				selected.setOrdenFabricacionId(orden.getValue().getId());
//				selected.setSeccion(selected.getSeccionId());
			}
			if (seccion.getValue() != null) {
				selected.setSeccionId(seccion.getValue().getId());
//				selected.setSeccion(selected.getSeccionId());
			}
			if (puesto.getValue() != null) {
				selected.setPuestoId(puesto.getValue().getId());
//				selected.setPuesto(selected.getPuestoId());
			}

			boolean ok = service.create(selected);
			if (ok) {
				guardarSuccessfullyNotification("La tarea ", selected.getNombre());
				volver();
			} else {
				guardarErrorNotification("La tarea ", selected.getNombre());
			}

//			volver();
		}

	}

	private void volver() {
		main.remove(this);
		main.add(new TareasView(main, persona));
	}

	private void guardarSuccessfullyNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " creada con éxito.", 2000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.open();
	}

	private void guardarErrorNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " no se pudo crear.", 1000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		notification.open();
	}

	// -----------------------------------------------------------

	private Binder<Tarea> tareaBinder = new Binder<>(Tarea.class);

	private H3 tituloTarea;
	private TextField nombre;
	private TextArea detalle;
	private ComboBox<OrdenFabricacion> orden;
	private ComboBox<Seccion> seccion;
	private ComboBox<Puesto> puesto;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067862213020325L;

} // END
