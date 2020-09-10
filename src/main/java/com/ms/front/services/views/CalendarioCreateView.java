package com.ms.front.services.views;

import com.ms.front.MainView;
import com.ms.front.commons.view.UIDatePickerI18n_es_AR;
import com.ms.front.model.CalendarioItem;
import com.ms.front.model.Persona;
import com.ms.front.services.CalendarioService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;

public class CalendarioCreateView extends FormLayout {

	private MainView main;
	private Persona persona;
	private CalendarioItem item;

	private CalendarioService service;

	public CalendarioCreateView(MainView main, Persona persona) {

		this.main = main;
		this.persona = persona;

		service = new CalendarioService();

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
		guardar.addClickListener(event -> guardar());

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, guardar);

		add(botonera);

		// ----------------------------------------------

		renderForm(item);

	}

	private void renderForm(CalendarioItem item) {

		tareaBinder.setBean(item);

		// ---------------------------------------------------------------------------

		titulo = new H3("Nueva fecha");

		fecha = new DatePicker();
		fecha.setLabel("Fecha");
		fecha.setClearButtonVisible(true);
		fecha.setRequiredIndicatorVisible(true);
		fecha.setWidthFull();
		fecha.setI18n(new UIDatePickerI18n_es_AR());

		inicioHora = new TimePicker();
		inicioHora.setLabel("Hora inicio");
		inicioHora.setClearButtonVisible(true);
		inicioHora.setRequiredIndicatorVisible(true);
//		inicioHora.setValue(LocalTime.parse(item.getHoraInicio()));

		cierreHora = new TimePicker();
		cierreHora.setLabel("Hora cierre");
		cierreHora.setClearButtonVisible(true);
		cierreHora.setRequiredIndicatorVisible(true);
//		cierreHora.setValue(LocalTime.parse(item.getHoraCierre()));
		cierreHora.setWidthFull();

		add(titulo, fecha, inicioHora, cierreHora);

		setColspan(titulo, 3);

		setMaxWidth("800px");
		addClassName("centered-content");

		// ---------------------------------------------------------------------------

	}

	private void guardar() {
		CalendarioItem item= new CalendarioItem();
//		copySelected.setId(selected.getId());

		if (fecha.isInvalid() || fecha.getValue() == null) {
			Notification notification = new Notification("Fecha es obligatorio", 3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
			return;
		}

		if (inicioHora.getValue() == null || inicioHora.getValue() == null) {
			Notification notification = new Notification("Inicio es obligatorio", 3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
			return;
		}

		if (cierreHora.isInvalid() || cierreHora.getValue() == null) {
			Notification notification = new Notification("Cierre es obligatorio", 3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
			return;
		}

		item.setFecha(fecha.getValue().toString());
		item.setHoraInicio(inicioHora.getValue().toString());
		item.setHoraCierre(cierreHora.getValue().toString());

		boolean ok = service.create(item);
		if (ok) {
			guardarSuccessfullyNotification(item.getFecha(),
					item.getHoraInicio() + " - " + item.getHoraCierre());
			volver();
		} else {
			guardarErrorNotification(item.getFecha(), item.getHoraInicio() + " - " + item.getHoraCierre());
		}

	}

	private void volver() {
		main.remove(this);
		main.add(new CalendarioView(main, persona));
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

	private Binder<CalendarioItem> tareaBinder = new Binder<>(CalendarioItem.class);

	private H3 titulo;

	private DatePicker fecha;
	private TimePicker inicioHora;
	private TimePicker cierreHora;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067862213020325L;

} // END
