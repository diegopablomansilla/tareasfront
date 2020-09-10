package com.ms.front.services.views;

import java.util.List;
import java.util.Optional;

import com.ms.front.MainView2;
import com.ms.front.commons.view.ConfirmationDialog;
import com.ms.front.model.MiTarea;
import com.ms.front.model.Persona;
import com.ms.front.model.TareaPersona;
import com.ms.front.services.MiTareaEnEjecucionService;
import com.ms.front.services.MiTareaService;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
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

public class MisTareasView extends VerticalLayout {

	private MainView2 main;
	private Persona persona;
	private MiTareaService service;
	private MiTarea tareaTomada;

//	private final String TIEMPO_1 = "Ver último 1 mes";
//	private final String TIEMPO_2 = "Ver últimos 2 meses";
//	private final String TIEMPO_3 = "Ver últimos 3 meses";
//	private final String TIEMPO_4 = "Ver último semestre";
//	private final String TIEMPO_5 = "Ver último año";

//	private Integer meses = 1;

	@SuppressWarnings("unused")
	private boolean emptyList = false;

	public MisTareasView(MainView2 main, Persona persona) {

		this.main = main;
		this.persona = persona;

		service = new MiTareaService();

		render();		

	}

	private void render() {

		List<MiTarea> tareas = service.findAll(persona.getSeccionId(), persona.getPuestoId());

		// ---------------------------------------------------------------------------

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());

		tomarTarea = new Button("Tomar tarea");
		tomarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		tomarTarea.setIcon(new Icon(VaadinIcon.PLAY_CIRCLE));
		tomarTarea.addThemeVariants(ButtonVariant.LUMO_ERROR);
//		verDetalleTarea.addClassName("centered-content");
		tomarTarea.addClickListener(event -> tomarTarea());

		guardarTarea = new Button("Guardar");
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		guardarTarea.setIcon(new Icon(VaadinIcon.CLOUD_UPLOAD));
//		editarTarea.addClassName("centered-content");
		guardarTarea.addClickListener(event -> guardarTarea());
		guardarTarea.setVisible(false);

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		if (tareas.size() > 0) {
			botonera.add(volver, guardarTarea);
		} else {
			botonera.add(volver);
		}

		add(botonera);

		// ----------------------------------------------

		miTareaEditView = new MiTareaEditView(tareaTomada);
		miTareaEditView.setVisible(false);
		add(miTareaEditView);

		MiTareaEnEjecucionService service = new MiTareaEnEjecucionService();
		List<TareaPersona> items = service.findAll(persona.getId());
		if (items.size() > 0) {
			tareaTomada = new MiTareaService().findById(items.get(0).getTareaId());
			if (tareaTomada != null) {
				miTareaEditView.setTarea(tareaTomada);
				miTareaEditView.setVisible(true);
			}
		}

		if (tareas.size() > 0) {
			renderListado(tareas);
		} else {
			this.add(new H4("No hay tareas para hacer"));
		}
	}

	private void renderListado(List<MiTarea> tareas) {

		tareasGrid = new Grid<MiTarea>();

		tareasGrid.addThemeVariants(/* GridVariant.LUMO_NO_BORDER, */
				GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);

		tareasGrid.setSelectionMode(SelectionMode.SINGLE);

		tareasGrid.addSelectionListener(event -> {
			Optional<MiTarea> selected = event.getFirstSelectedItem();
			if (selected != null && selected.isPresent()) {
				tareaBinder.setBean(selected.get());
				select(selected.get());
			}
		});

		tareasGrid.addColumn(MiTarea::toString).setHeader("X Tareas para hacer").setKey("tarea").setAutoWidth(true);

		add(tareasGrid);

		// ---------------------------------------------------------------------------

		tituloTarea = new H5("Tarea ##");
		subTituloTarea = new H5("Seccion");
		detalle = new Label();
		creada = new Label();

		add(tituloTarea, subTituloTarea, detalle, creada, tomarTarea);

		comentarios = new TextArea();
		comentarios.setPlaceholder("Ingresa tus comentarios");
		comentarios.setWidthFull();
		comentarios.setHeight("200px");
		comentarios.setVisible(false);

		add(comentarios);

		// ---------------------------------------------------------------------------

		setItemsTareasGrid(tareas);

	}

	private void setItemsTareasGrid(List<MiTarea> items) {
		tareasGrid.setItems(items);

		if (items.size() > 0) {
			tareasGrid.select(items.get(0));
		} else {
			emptyList = true;
		}
	}

//	private void volver() {
//		main.remove(this);
//		main.add(new LoginMisTareasView(main));
//	}

	private void volver() {

		if (guardarTarea.isVisible()) {
			guardarTarea.setVisible(false);
			comentarios.setVisible(false);

			miTareaEditView.setVisible(true);
			tareasGrid.setVisible(true);
			tituloTarea.setVisible(true);
			subTituloTarea.setVisible(true);
			detalle.setVisible(true);
			creada.setVisible(true);
			tomarTarea.setVisible(true);
			comentarios.setValue("");
		} else {
			main.remove(this);
			main.add(new LoginMisTareasView(main));

		}

	}

	private void select(MiTarea item) {
		if (item != null) {

			String t = "Tarea (" + item.getId() + ") " + item.getNombre();
			if (item.getOrdenFabricacion() != null) {
				t += " - " + item.getOrdenFabricacion();
			}

			tituloTarea.setText(t);

			String st = item.getSeccion();
			if (item.getPuesto() != null) {
				st += " - " + item.getPuesto();
			}
			subTituloTarea.setText(st);
			detalle.setText(item.getDetalle());

			creada.setText("Tarea creada el " + item.getFecha());

			tomarTarea.setVisible(true);

			if (tareaTomada != null && tareaTomada.getId().equals(item.getId())) {
				tomarTarea.setVisible(false);
			}

		} else {
			tomarTarea.setVisible(false);
		}

	}

	public void tomarTarea() {

		if (tareaTomada != null) {

			dejarTareaDialog("Tarea (" + tareaTomada.getId() + ") " + tareaTomada.getNombre(), buttonClickEvent -> {

				guardarTarea.setVisible(true);
				comentarios.setVisible(true);

				miTareaEditView.setVisible(false);
				tareasGrid.setVisible(false);
				tituloTarea.setVisible(false);
				subTituloTarea.setVisible(false);
				detalle.setVisible(false);
				creada.setVisible(false);
				tomarTarea.setVisible(false);

			});

		} else {
			MiTarea selected = tareaBinder.getBean();

			if (selected != null) {

				tomarTareaDialog("Tarea (" + selected.getId() + ") " + selected.getNombre(), buttonClickEvent -> {

					boolean ok = service.create(selected.getId(), persona.getId());
					if (ok) {
						tomarTareaSuccessfullyNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
						tareaTomada = selected;
						miTareaTomada(tareaTomada);
						miTareaEditView.setVisible(true);
						select(tareaTomada);
					} else {
						tomarTareaErrorNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
					}

				});

			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void tomarTareaDialog(String msg, ComponentEventListener listener) {

		String title = "¿ Estás seguro, quieres tomar esta tarea ?";

		ConfirmationDialog confirmationDialog = new ConfirmationDialog(title, msg);
		confirmationDialog.confirm.setIcon(new Icon(VaadinIcon.PLAY_CIRCLE));
		confirmationDialog.confirm.setText("Tomar");
		confirmationDialog.abort.setText("Cancelar");
		confirmationDialog.confirm.addClickListener(listener);
		confirmationDialog.open();
		confirmationDialog.abort.focus();
	}

	private void tomarTareaSuccessfullyNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " tomada con éxito.", 2000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.open();
	}

	private void tomarTareaErrorNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " no se pudo tomar.", 1000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		notification.open();
	}

	private void miTareaTomada(MiTarea tarea) {
		if (tarea != null) {
			miTareaEditView.setTarea(tarea);
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

	private void guardarTarea() {

//		if (tareaTomada != null) {

		MiTarea copySelected = new MiTarea();
		copySelected.setId(tareaTomada.getId());
		copySelected.setComentario(comentarios.getValue());

		boolean ok = service.save(tareaTomada.getId(), persona.getId(), comentarios.getValue());
		if (ok) {
			comentarios.setValue("");
			guardarSuccessfullyNotification("Tarea (" + tareaTomada.getId() + ") ", tareaTomada.getNombre());

			guardarTarea.setVisible(false);
			comentarios.setVisible(false);

			
			tareasGrid.setVisible(true);
			tituloTarea.setVisible(true);
			subTituloTarea.setVisible(true);
			detalle.setVisible(true);
			creada.setVisible(true);
//			tomarTarea.setVisible(false);

			MiTarea selected = tareaBinder.getBean();
			if (selected != null) {
				ok = service.create(selected.getId(), persona.getId());
				if (ok) {
					tomarTareaSuccessfullyNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
					tareaTomada = selected;
					miTareaTomada(tareaTomada);
					miTareaEditView.setVisible(true);
					select(tareaTomada);
				} else {
					tomarTareaErrorNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
				}
			}

		} else {
			guardarErrorNotification("Tarea (" + tareaTomada.getId() + ") ", tareaTomada.getNombre());
		}
//		}

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

	private Grid<MiTarea> tareasGrid;
	private Binder<MiTarea> tareaBinder = new Binder<>(MiTarea.class);

	private Button tomarTarea;

	private H5 tituloTarea;
	private H5 subTituloTarea;
	private Label detalle;

	private Label creada;

	private MiTareaEditView miTareaEditView;

	private TextArea comentarios;
	private Button guardarTarea;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067861213020325L;

} // END
