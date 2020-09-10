package com.ms.front.services.views;

import com.ms.front.MainView2;
import com.ms.front.commons.view.ConfirmationDialog;
import com.ms.front.model.MiTarea;
import com.ms.front.model.Persona;
import com.ms.front.services.MiTareaService;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;

public class MiTareaEditView2 extends VerticalLayout {

	private MainView2 main;
	private Persona persona;
	private MiTarea tarea;
	private MiTareaService service;
	private boolean backToLogin;

	public MiTareaEditView2(MainView2 main, Persona persona, MiTarea tarea, boolean backToLogin) {

		this.main = main;
		this.persona = persona;
		this.tarea = tarea;
		this.backToLogin = backToLogin;

		service = new MiTareaService();

		render();

		guardarTarea.setVisible(false);
		comentarios.setVisible(false);
	}

	private void render() {

		// ---------------------------------------------------------------------------

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());

		guardarTarea = new Button("Guardar");
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		guardarTarea.setIcon(new Icon(VaadinIcon.CLOUD_UPLOAD));
//		editarTarea.addClassName("centered-content");
		guardarTarea.addClickListener(event -> guardarTarea());

		dejarTarea = new Button("Dejar tarea");
		dejarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		dejarTarea.addThemeVariants(ButtonVariant.LUMO_ERROR);
		dejarTarea.setIcon(new Icon(VaadinIcon.MINUS_CIRCLE));
//		editarTarea.addClassName("centered-content");
		dejarTarea.addClickListener(event -> dejarTarea());

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, dejarTarea, guardarTarea);

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

	private void renderForm(MiTarea tarea) {

		tareaBinder.setBean(tarea);

		// ---------------------------------------------------------------------------

		tituloTarea = new H3("Y Tarea (" + tarea.getId() + ") ");

		nombreTitulo = new H5("Nombre");
		nombre = new Label(tarea.getNombre());

		ordenTitulo = new H5("Orden");
		orden = new Label(tarea.getOrdenFabricacion());

		seccionTitulo = new H5("Sección");
		seccion = new Label(tarea.getSeccion());

		puestoTitulo = new H5("Puesto");
		puesto = new Label(tarea.getPuesto());

		detalleTitulo = new H5("Detalle");
		detalle = new Label(tarea.getDetalle());

		creadaTitulo = new H5("Creada");
		creada = new Label(tarea.getFecha());

		creada = new Label();
		creada.setText(tarea.getFecha());

		comentarios = new TextArea();
		comentarios.setPlaceholder("Ingresa tus comentarios");
		comentarios.setWidthFull();
		comentarios.setHeight("200px");
//		comentarios.setHeightFull();

		add(tituloTarea, nombreTitulo, nombre, ordenTitulo, orden, seccionTitulo, seccion, puestoTitulo, puesto,
				detalleTitulo, detalle, creadaTitulo, creada, comentarios);

		String url = "";		
		
		if (tarea.getAdjunto() != null && tarea.getAdjunto().endsWith("?id=" + tarea.getId())) {
			url = tarea.getAdjunto();
			anchor = new Anchor(url, "Adjunto");
			add(anchor);
		}

		setMaxWidth("800px");
		addClassName("centered-content");

		// ---------------------------------------------------------------------------

	}

	private void guardarTarea() {
		MiTarea selected = tareaBinder.getBean();

		if (selected != null) {

			MiTarea copySelected = new MiTarea();
			copySelected.setId(selected.getId());

//			if (c.isInvalid()) {
//				Notification notification = new Notification("Nombre es obligatorio", 3000, Position.BOTTOM_END);
//				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
//				notification.open();
//				return;
//			}
//			if (seccion.isInvalid() || seccion.getValue() == null) {
//				Notification notification = new Notification("Seccion es obligatorio", 3000, Position.BOTTOM_END);
//				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
//				notification.open();
//				return;
//			}
//
//			TareaService service = new TareaService();
//
			copySelected.setComentario(comentarios.getValue());

			boolean ok = service.save(selected.getId(), persona.getId(), comentarios.getValue());
			if (ok) {
				guardarSuccessfullyNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
				volver();
			} else {
				guardarErrorNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
			}

			backToLogin = false;

			volver();

		}

	}

	private void volver() {

		if (guardarTarea.isVisible()) {
			guardarTarea.setVisible(false);
			comentarios.setVisible(false);

			dejarTarea.setVisible(true);

			nombreTitulo.setVisible(true);
			nombre.setVisible(true);

			seccionTitulo.setVisible(true);
			seccion.setVisible(true);

			puestoTitulo.setVisible(true);
			puesto.setVisible(true);

			ordenTitulo.setVisible(true);
			orden.setVisible(true);

			detalleTitulo.setVisible(true);
			detalle.setVisible(true);

			creadaTitulo.setVisible(false);
			creada.setVisible(false);
		} else {
			main.remove(this);
			if (backToLogin) {
				main.add(new LoginMisTareasView(main));
			} else {
				main.add(new MisTareasView(main, persona));
			}

		}

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

	public void dejarTarea() {

		MiTarea selected = tareaBinder.getBean();

		if (selected != null) {

			dejarTareaDialog("Tarea (" + selected.getId() + ") " + selected.getNombre(), buttonClickEvent -> {

				guardarTarea.setVisible(true);
				comentarios.setVisible(true);

				dejarTarea.setVisible(false);

				nombreTitulo.setVisible(false);
				nombre.setVisible(false);

				seccionTitulo.setVisible(false);
				seccion.setVisible(false);

				puestoTitulo.setVisible(false);
				puesto.setVisible(false);

				ordenTitulo.setVisible(false);
				orden.setVisible(false);

				detalleTitulo.setVisible(false);
				detalle.setVisible(false);

				creadaTitulo.setVisible(false);
				creada.setVisible(false);
			});

		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void dejarTareaDialog(String msg, ComponentEventListener listener) {

		String title = "¿ Estás seguro, quieres dejar la tarea ?";

		ConfirmationDialog confirmationDialog = new ConfirmationDialog(title, msg);
		confirmationDialog.confirm.setIcon(new Icon(VaadinIcon.MINUS_CIRCLE));
		confirmationDialog.confirm.setText("Dejar");
		confirmationDialog.abort.setText("Cancelar");
		confirmationDialog.confirm.addClickListener(listener);
		confirmationDialog.open();
		confirmationDialog.abort.focus();
	}

	// -----------------------------------------------------------

	private Binder<MiTarea> tareaBinder = new Binder<>(MiTarea.class);

	private Button guardarTarea;
	private Button dejarTarea;

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

	private TextArea comentarios;

	private Anchor anchor;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067862213020325L;

} // END
