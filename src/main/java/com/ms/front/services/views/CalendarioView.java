package com.ms.front.services.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ms.front.MainView;
import com.ms.front.commons.view.ConfirmationDialog;
import com.ms.front.model.CalendarioItem;
import com.ms.front.model.Persona;
import com.ms.front.services.CalendarioService;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;

public class CalendarioView extends VerticalLayout {

	private MainView main;
	private Persona persona;
	private CalendarioService service;

	public CalendarioView(MainView main, Persona persona) {

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

		Button crearTarea = new Button("Agregar fecha");
		crearTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		crearTarea.setIcon(new Icon(VaadinIcon.PLUS_CIRCLE));
		crearTarea.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
//		crearTarea.addClassName("centered-content");
		crearTarea.addClickListener(event -> agregarFecha());

		editarTarea = new Button("Editar");
		editarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		editarTarea.setIcon(new Icon(VaadinIcon.EDIT));
//		editarTarea.addClassName("centered-content");
		editarTarea.addClickListener(event -> editarItem());

		borrarTarea = new Button("Borrar fecha");
		borrarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		borrarTarea.addThemeVariants(ButtonVariant.LUMO_ERROR);
		borrarTarea.setIcon(new Icon(VaadinIcon.TRASH));
//		editarTarea.addClassName("centered-content");
		borrarTarea.addClickListener(event -> delete());

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, crearTarea, editarTarea, borrarTarea);

//		HorizontalLayout filtro = new HorizontalLayout();
//		filtro.add(tiempo);

		add(botonera);

		// ----------------------------------------------

		H3 titulo = new H3("Calendario");
//		H5 tituloPersona = new H5(persona.toString());
//
//		HorizontalLayout hs = new HorizontalLayout();
//		hs.setSpacing(false);
//
//		hs.add(titulo, tituloPersona);

		this.add(titulo);

//		addClassName("centered-content");

		List<CalendarioItem> tareas = service.findAll();

		renderListado(tareas);
	}

	private void renderListado(List<CalendarioItem> items) {

		tareasGrid = new Grid<CalendarioItem>();

		tareasGrid.addThemeVariants(/* GridVariant.LUMO_NO_BORDER, */
				GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);

		tareasGrid.setSelectionMode(SelectionMode.SINGLE);

//		SingleSelect<Grid<OrdenFabricacionTarea>, OrdenFabricacionTarea> itemSelect = ordenesGrid.asSingleSelect();
//		itemSelect.addValueChangeListener(e -> {
////			OrdenFabricacion selected = e.getValue();
//			// System.out.println(selected);
////						binderTareaSinHacer.setBean(selected);
//		});

		tareasGrid.addSelectionListener(event -> {
			Optional<CalendarioItem> selected = event.getFirstSelectedItem();
			if (selected != null && selected.isPresent()) {
				tareaBinder.setBean(selected.get());
//				select(selected.get());
			}
		});

		tareasGrid.addColumn(CalendarioItem::getEtiqueta).setHeader("Día/Fecha").setKey("etiqueta").setAutoWidth(true);
		tareasGrid.addColumn(CalendarioItem::getHoraInicio).setHeader("Inicio").setKey("inicio").setAutoWidth(true);
		tareasGrid.addColumn(CalendarioItem::getHoraCierre).setHeader("Cierre").setKey("cierre").setAutoWidth(true);

		add(tareasGrid);

		// ---------------------------------------------------------------------------

		setItemsTareasGrid(items);

	}

	private void setItemsTareasGrid(List<CalendarioItem> items) {
		if (items == null) {
			items = new ArrayList<CalendarioItem>();
		}
		boolean b = items.size() > 0;
		tareasGrid.setItems(items);
		if (items.size() > 0) {
			tareasGrid.select(items.get(0));
		}

		editarTarea.setVisible(b);
		borrarTarea.setVisible(b);
	}

	private void editarItem() {
		CalendarioItem selected = tareaBinder.getBean();

		if (selected != null) {
			main.remove(this);
			main.add(new CalendarioEditView(main, persona, selected));
		}

	}

	private void agregarFecha() {
		main.remove(this);
		main.add(new CalendarioCreateView(main, persona));
	}

	private void volver() {
		main.remove(this);
		main.add(new TareasView(main, persona));
	}

//	private void select(CalendarioItem item) {
//		if (item != null) {
//		}
//	}

	public void delete() {

		CalendarioItem selected = tareaBinder.getBean();

		if (selected != null && selected.getDia() == null) {

			deleteDialog(selected.getEtiqueta(), buttonClickEvent -> {

				boolean ok = service.delete(selected.getId());
				if (ok) {
					deleteSuccessfullyNotification(selected.getEtiqueta(), selected.getEtiqueta());

					List<CalendarioItem> tareas = service.findAll();
					setItemsTareasGrid(tareas);
				} else {
					deleteErrorNotification(selected.getEtiqueta(), selected.getEtiqueta());
				}

			});

		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void deleteDialog(String msg, ComponentEventListener listener) {

		String title = "¿ Estás seguro, quieres borrar el ítem ?";

		ConfirmationDialog confirmationDialog = new ConfirmationDialog(title, msg);
		confirmationDialog.confirm.setIcon(new Icon(VaadinIcon.TRASH));
		confirmationDialog.confirm.setText("Borrar");
		confirmationDialog.abort.setText("Cancelar");
		confirmationDialog.confirm.addClickListener(listener);
		confirmationDialog.open();
		confirmationDialog.abort.focus();
	}

	private void deleteSuccessfullyNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " borrado con éxito.", 2000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.open();
	}

	private void deleteErrorNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " no se pudo borrar.", 1000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		notification.open();
	}

	// -----------------------------------------------------------

	private Grid<CalendarioItem> tareasGrid;
	private Binder<CalendarioItem> tareaBinder = new Binder<>(CalendarioItem.class);

	private Button editarTarea;
	private Button borrarTarea;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067861213020325L;

} // END
