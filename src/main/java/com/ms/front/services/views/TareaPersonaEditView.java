package com.ms.front.services.views;

import java.time.LocalDateTime;
import java.util.List;

import com.ms.front.MainView;
import com.ms.front.commons.view.UIDatePickerI18n_es_AR;
import com.ms.front.model.Persona;
import com.ms.front.model.Tarea;
import com.ms.front.model.TareaPersonaSave;
import com.ms.front.services.PersonaService;
import com.ms.front.services.TareaPersonaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;

public class TareaPersonaEditView extends FormLayout {

	private MainView main;
	private Persona persona;
	private Tarea tarea;
	private boolean cerrada;

	private TareaPersonaService service;

	public TareaPersonaEditView(MainView main, Persona persona, Tarea tarea) {

		this.main = main;
		this.persona = persona;
		this.tarea = tarea;

		cerrada = "CERRADA".equals(tarea.getEstado());

		service = new TareaPersonaService();

		render();
	}

	private void render() {

		// ---------------------------------------------------------------------------

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());

		Button guardar = new Button("Guardar");
		guardar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		guardar.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		guardar.setIcon(new Icon(VaadinIcon.CLOUD_UPLOAD));
//		editarTarea.addClassName("centered-content");
		guardar.addClickListener(event -> guardarTarea());
		guardar.setVisible(cerrada == false);

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, guardar);

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

		if (cerrada) {

		} else {

		}

	}

	private void renderForm(Tarea tarea) {

		tareaBinder.setBean(tarea);

		List<Persona> personas = new PersonaService().findAll(tarea.getSeccionId(), tarea.getPuestoId());

		boolean readOnly = cerrada;

		// ---------------------------------------------------------------------------

		tituloTarea = new H3("Tarea (" + tarea.getId() + ") ");

		seccion = new TextField();
		seccion.setLabel("Sección");
//		seccion.setClearButtonVisible(true);
//		seccion.setRequiredIndicatorVisible(true);
		seccion.setReadOnly(true);
		seccion.setValue(tarea.getSeccion());

		puesto = new TextField();
		puesto.setLabel("Puesto");
//		puesto.setClearButtonVisible(true);
//		puesto.setRequiredIndicatorVisible(true);
		puesto.setReadOnly(true);
		puesto.setValue(tarea.getPuesto() != null ? tarea.getPuesto() : "");

		operario = new ComboBox<Persona>();
		operario.setLabel("Persona");
		operario.setReadOnly(readOnly);
		operario.setClearButtonVisible(true);
		operario.setItems(personas);

//		if (tarea.getPuestoId() != null) {
//			if (personas.size() > 0) {
//				for (Persona p : personas) {
//					if (p.getId().equals(tarea.getPuestoId())) {
//						operario.setValue(p);
//					}
//				}
//			}
//		} else {
////			if(secciones.size() > 0 ) {
////				seccion.setValue(secciones.get(0));
////			}	
//		}

		tomaTareaFecha = new DatePicker();
		tomaTareaFecha.setLabel("Fecha (toma)");
		tomaTareaFecha.setClearButtonVisible(true);
		tomaTareaFecha.setRequiredIndicatorVisible(true);
		tomaTareaFecha.setReadOnly(readOnly);
		tomaTareaFecha.setI18n(new UIDatePickerI18n_es_AR());

		sueltaTareaFecha = new DatePicker();
		sueltaTareaFecha.setLabel("Fecha (suelta)");
		sueltaTareaFecha.setClearButtonVisible(true);
		sueltaTareaFecha.setRequiredIndicatorVisible(true);
		sueltaTareaFecha.setReadOnly(readOnly);
		sueltaTareaFecha.setWidthFull();
		sueltaTareaFecha.setI18n(new UIDatePickerI18n_es_AR());

		tomaTareaHora = new TimePicker();
		tomaTareaHora.setLabel("Hora (toma)");
		tomaTareaHora.setClearButtonVisible(true);
		tomaTareaHora.setRequiredIndicatorVisible(true);
		tomaTareaHora.setReadOnly(readOnly);

		sueltaTareaHora = new TimePicker();
		sueltaTareaHora.setLabel("Hora (suelta)");
		sueltaTareaHora.setClearButtonVisible(true);
		sueltaTareaHora.setRequiredIndicatorVisible(true);
		sueltaTareaHora.setReadOnly(readOnly);
		sueltaTareaHora.setWidthFull();

		add(tituloTarea, seccion, puesto, operario, tomaTareaFecha, tomaTareaHora, sueltaTareaFecha, sueltaTareaHora);

		setColspan(tituloTarea, 3);
		setColspan(operario, 3);
//		setColspan(tomaTareaFecha, 2);
//		setColspan(sueltaTareaFecha, 2);

		setMaxWidth("800px");
		addClassName("centered-content");

		// ---------------------------------------------------------------------------

	}

	private void guardarTarea() {
		Tarea selected = tareaBinder.getBean();

		if (selected != null) {

			TareaPersonaSave copySelected = new TareaPersonaSave();
			copySelected.setTareaId(selected.getId());

			if (operario.getValue() == null) {
				Notification notification = new Notification("Persona es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}

			if (tomaTareaFecha.isInvalid() || tomaTareaFecha.getValue() == null) {
				Notification notification = new Notification("Fecha (toma) es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}
			if (tomaTareaHora.isInvalid() || tomaTareaHora.getValue() == null) {
				Notification notification = new Notification("Hora (toma) es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}

			if (sueltaTareaFecha.isInvalid() || sueltaTareaFecha.getValue() == null) {
				Notification notification = new Notification("Fecha (suelta) es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}
			if (sueltaTareaHora.isInvalid() || sueltaTareaHora.getValue() == null) {
				Notification notification = new Notification("Hora (suelta) es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}

			LocalDateTime toma = LocalDateTime.of(tomaTareaFecha.getValue(), tomaTareaHora.getValue());
			LocalDateTime suelta = LocalDateTime.of(sueltaTareaFecha.getValue(), sueltaTareaHora.getValue());

			copySelected.setTomaTarea(toma.toString());
			copySelected.setSueltaTarea(suelta.toString());
			copySelected.setPersonaId(operario.getValue().getId());

			boolean ok = service.save(copySelected);
			if (ok) {
				guardarSuccessfullyNotification("Tarea (" + selected.getId() + ") ",
						selected.getNombre() + " - " + operario.getValue());
				volver();
			} else {
				guardarErrorNotification("Tarea (" + selected.getId() + ") ",
						selected.getNombre() + " - " + operario.getValue());
			}

//			volver();

		}

	}

	private void volver() {
		main.remove(this);
		main.add(new TareasPersonaView(main, persona, tarea));
	}

	private void guardarSuccessfullyNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " guadada con éxito.", 2000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.open();
	}

	private void guardarErrorNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " no se pudo guardar.", 1000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		notification.open();
	}

	// -----------------------------------------------------------

	private Binder<Tarea> tareaBinder = new Binder<>(Tarea.class);

	private H3 tituloTarea;

	private TextField seccion;
	private TextField puesto;
	private ComboBox<Persona> operario;
	private DatePicker tomaTareaFecha;
	private DatePicker sueltaTareaFecha;
	private TimePicker tomaTareaHora;
	private TimePicker sueltaTareaHora;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067862213020325L;

} // END
