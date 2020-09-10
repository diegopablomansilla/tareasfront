package com.ms.front.services.views;

import java.time.LocalTime;

import com.ms.front.MainView;
import com.ms.front.model.CalendarioItem;
import com.ms.front.model.Persona;
import com.ms.front.services.CalendarioService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

public class CalendarioEditView extends FormLayout {

	private MainView main;
	private Persona persona;
	private CalendarioItem item;

	private CalendarioService service;

	public CalendarioEditView(MainView main, Persona persona, CalendarioItem item) {

		this.main = main;
		this.persona = persona;
		this.item = item;

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

		titulo = new H3(item.getEtiqueta());

		inicioHora = new TimePicker();
		inicioHora.setLabel("Hora inicio");
		inicioHora.setClearButtonVisible(true);
		inicioHora.setRequiredIndicatorVisible(true);
		inicioHora.setValue(LocalTime.parse(item.getHoraInicio()));

		cierreHora = new TimePicker();
		cierreHora.setLabel("Hora cierre");
		cierreHora.setClearButtonVisible(true);
		cierreHora.setRequiredIndicatorVisible(true);
		cierreHora.setValue(LocalTime.parse(item.getHoraCierre()));
		cierreHora.setWidthFull();

		add(titulo, inicioHora, cierreHora);

		setColspan(titulo, 3);

		setMaxWidth("800px");
		addClassName("centered-content");

		// ---------------------------------------------------------------------------

	}

	private void guardar() {
		CalendarioItem selected = tareaBinder.getBean();

		if (selected != null) {

			CalendarioItem copySelected = new CalendarioItem();
			copySelected.setId(selected.getId());

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

			copySelected.setHoraInicio(inicioHora.getValue().toString());
			copySelected.setHoraCierre(cierreHora.getValue().toString());

			boolean ok = service.save(copySelected);
			if (ok) {
				guardarSuccessfullyNotification(selected.getId(),
						selected.getHoraInicio() + " - " + selected.getHoraCierre());
				volver();
			} else {
				guardarErrorNotification(selected.getId(), selected.getHoraInicio() + " - " + selected.getHoraCierre());
			}

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

	private TimePicker inicioHora;
	private TimePicker cierreHora;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067862213020325L;

} // END
