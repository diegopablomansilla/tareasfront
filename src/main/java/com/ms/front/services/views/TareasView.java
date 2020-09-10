package com.ms.front.services.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ms.front.MainView;
import com.ms.front.commons.view.ConfirmationDialog;
import com.ms.front.model.Persona;
import com.ms.front.model.Tarea;
import com.ms.front.services.TareaService;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
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
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.binder.Binder;

public class TareasView extends VerticalLayout {

	private MainView main;
	private Persona persona;
	private TareaService service;

	private final String TIEMPO_1 = "Ver último 1 mes";
	private final String TIEMPO_2 = "Ver últimos 2 meses";
	private final String TIEMPO_3 = "Ver últimos 3 meses";
	private final String TIEMPO_4 = "Ver último semestre";
	private final String TIEMPO_5 = "Ver último año";

	private Integer meses = 1;

	public TareasView(MainView main, Persona persona) {

		this.main = main;
		this.persona = persona;
		
		service = new TareaService();

		render();
		
		tiempo.addValueChangeListener(event -> {
			switch (event.getValue()) {
			case TIEMPO_1:
				meses = 1;
				break;
			case TIEMPO_2:
				meses = 2;
				break;
			case TIEMPO_3:
				meses = 3;
				break;
			case TIEMPO_4:
				meses = 6;
				break;
			case TIEMPO_5:
				meses = 12;
				break;
			default:
				meses = 1;
			}
			
			List<Tarea> tareas = service.findAll(meses);			
			setItemsTareasGrid(tareas);

		});
	}

	private void render() {

		// ---------------------------------------------------------------------------

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());

		Button crearTarea = new Button("Crear nueva tarea");
		crearTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		crearTarea.setIcon(new Icon(VaadinIcon.PLUS_CIRCLE));
		crearTarea.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
//		crearTarea.addClassName("centered-content");
		crearTarea.addClickListener(event -> crearTarea());

		verDetalleTarea = new Button("Ver detalle de la tarea");
		verDetalleTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		verDetalleTarea.setIcon(new Icon(VaadinIcon.EXTERNAL_LINK));
//		verDetalleTarea.addClassName("centered-content");
		verDetalleTarea.addClickListener(event -> verDetalleTarea());

		editarTarea = new Button("Editar tarea");
		editarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		editarTarea.setIcon(new Icon(VaadinIcon.EDIT));
//		editarTarea.addClassName("centered-content");
		editarTarea.addClickListener(event -> editarTarea());

		borrarTarea = new Button("Borrar tarea");
		borrarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		borrarTarea.addThemeVariants(ButtonVariant.LUMO_ERROR);
		borrarTarea.setIcon(new Icon(VaadinIcon.TRASH));
//		editarTarea.addClassName("centered-content");
		borrarTarea.addClickListener(event -> delete());
		
		verCalendario = new Button("Ver calendario");
		verCalendario.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		verCalendario.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
		verCalendario.setIcon(new Icon(VaadinIcon.CALENDAR));
//		verCalendario.addClassName("centered-content");
		verCalendario.addClickListener(event -> verCalendario());

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, crearTarea, editarTarea, verDetalleTarea, borrarTarea, verCalendario);

		tiempo = new Select<>();		
		tiempo.setItems(TIEMPO_1, TIEMPO_2, TIEMPO_3, TIEMPO_4, TIEMPO_5);
		tiempo.setValue(TIEMPO_1);
	

//		HorizontalLayout filtro = new HorizontalLayout();
//		filtro.add(tiempo);

		add(botonera, tiempo);

		// ----------------------------------------------

		H3 titulo = new H3("Tareas");
//		H5 tituloPersona = new H5(persona.toString());
//
//		HorizontalLayout hs = new HorizontalLayout();
//		hs.setSpacing(false);
//
//		hs.add(titulo, tituloPersona);

		this.add(titulo);

//		addClassName("centered-content");

		
		List<Tarea> tareas = service.findAll(meses);

		renderListado(tareas);
	}

	private void renderListado(List<Tarea> tareas) {

		tareasGrid = new Grid<Tarea>();

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
			Optional<Tarea> selected = event.getFirstSelectedItem();
			if (selected != null && selected.isPresent()) {
				tareaBinder.setBean(selected.get());
				select(selected.get());
			}
		});

//		tareasGrid.addItemClickListener(event -> System.out.println(("Clicked Item: " + event.getItem())));
//
//		tareasGrid
//				.addItemDoubleClickListener(event -> System.out.println(("Double Clicked Item: " + event.getItem())));

		tareasGrid.addColumn(Tarea::getId).setHeader("Código").setKey("numero").setAutoWidth(true);
//		tareasGrid.addColumn(Tarea::getFecha).setHeader("Fecha").setKey("fecha");
		tareasGrid.addColumn(Tarea::getNombre).setHeader("Nombre").setKey("nombre").setAutoWidth(true);
//		tareasGrid.addColumn(OrdenFabricacionTarea::getDetalle).setHeader("Detalle").setKey("detalle");
		tareasGrid.addColumn(Tarea::getOrdenFabricacion).setHeader("Orden").setKey("orden").setAutoWidth(true);
		tareasGrid.addColumn(Tarea::getSeccion).setHeader("Sección").setKey("seccion").setAutoWidth(true);
		tareasGrid.addColumn(Tarea::getPuesto).setHeader("Puesto").setKey("puesto").setAutoWidth(true);
		tareasGrid.addColumn(Tarea::getEstado).setHeader("Estado").setKey("estado").setAutoWidth(true);

		tareasGrid.addColumn(Tarea::getHoras).setHeader("Declaradas").setKey("horas").setAutoWidth(true);
		tareasGrid.addColumn(Tarea::getHorasCalculadas).setHeader("Calculadas").setKey("horasCalculadas")
				.setAutoWidth(true);

//		tareasGrid.addColumn(OrdenFabricacionTarea::getPersonasEnEjecucion).setHeader("Personas trabajando").setKey("personasEnEjecucion");

		HeaderRow topRow = tareasGrid.prependHeaderRow();

		Label columnsTareas = new Label("Horas");
		columnsTareas.addClassName("centered-content");
		columnsTareas.setWidthFull();

		HorizontalLayout tareasColumns = new HorizontalLayout();
		tareasColumns.setSpacing(false);
		tareasColumns.setWidthFull();
		tareasColumns.add(columnsTareas);

		topRow.join(tareasGrid.getColumnByKey("horas"), tareasGrid.getColumnByKey("horasCalculadas"))
				.setComponent(tareasColumns);
//
//		Label columnsHoras = new Label("Horas");
//		columnsHoras.addClassName("centered-content");
//		columnsHoras.setWidthFull();
//
//		HorizontalLayout horasColumns = new HorizontalLayout();
//		horasColumns.setSpacing(false);
//		horasColumns.setWidthFull();
//		horasColumns.add(columnsHoras);
//
//		topRow.join(tareasGrid.getColumnByKey("cantidadHoras"), tareasGrid.getColumnByKey("cantidadHorasCalculadas"))
//				.setComponent(horasColumns);

		add(tareasGrid);

		// ---------------------------------------------------------------------------

		tituloTarea = new H3("Tarea ##");
		detalle = new Label();

		tituloEjecutores = new H5("Ejecutores de la tarea ##");
		ejecutores = new Label();

		tituloEjecutoresActuales = new H5("Ejecutores actuales de la tarea ##");
		ejecutoresActuales = new Label();
		creada = new Label();

		add(tituloTarea, detalle, tituloEjecutores, ejecutores, tituloEjecutoresActuales, ejecutoresActuales, creada);
		

		// ---------------------------------------------------------------------------

		setItemsTareasGrid(tareas);

	}

	private void setItemsTareasGrid(List<Tarea> items) {
		if(items == null) {
			items = new ArrayList<Tarea>();
		}
		boolean b = items.size() > 0;
		tareasGrid.setItems(items);
		if (items.size() > 0) {
			tareasGrid.select(items.get(0));
		} 
		
//		tareasGrid.setVisible(b);
		tituloTarea.setVisible(b);
		detalle.setVisible(b);
		tituloEjecutores.setVisible(b);
		ejecutores.setVisible(b);
		tituloEjecutoresActuales.setVisible(b);
		ejecutoresActuales.setVisible(b);
		creada.setVisible(b);
		
		verDetalleTarea.setVisible(b);
		editarTarea.setVisible(b);
		borrarTarea.setVisible(b);
	}

	private void verDetalleTarea() {
		Tarea selected = tareaBinder.getBean();

		if (selected != null) {
			main.remove(this);
			main.add(new TareasPersonaView(main, persona, selected));
		}

	}

	private void editarTarea() {
		Tarea selected = tareaBinder.getBean();

		if (selected != null) {
			main.remove(this);
			main.add(new TareaEditView(main, persona, selected));
		}

	}

	private void crearTarea() {
		main.remove(this);
		main.add(new TareaCreateView(main, persona));
	}

	private void volver() {
		main.remove(this);
		main.add(new LoginView(main));
	}
	
	private void verCalendario() {
		main.remove(this);
		main.add(new CalendarioView(main, persona));
	}

	private void select(Tarea item) {
		if (item != null) {

			tituloTarea.setText("Tarea (" + item.getId() + ") " + item.getNombre());
			detalle.setText(item.getDetalle());

			tituloEjecutores.setText("Ejecutores (" + item.getCantidadVecesTomada() + ") ");
			ejecutores.setText(item.getPersonas());

			tituloEjecutoresActuales.setText("Ejecutores actuales (" + item.getCantidadEnEjecucion() + ")");
			ejecutoresActuales.setText(item.getPersonasEnEjecucion());

			creada.setText("Tarea creada el " + item.getFecha());
		}
	}

	public void delete() {

		Tarea selected = tareaBinder.getBean();

		if (selected != null) {

			deleteDialog("Tarea (" + selected.getId() + ") " + selected.getNombre(), buttonClickEvent -> {

//				ResultService<PuntoEquilibrio> r = servicesDelete(binder.getBean().clone());

//				TareaService service = new TareaService();

				boolean ok = service.delete(selected.getId());
				if (ok) {
					deleteSuccessfullyNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());

					List<Tarea> tareas = service.findAll(meses);
					setItemsTareasGrid(tareas);
				} else {
					deleteErrorNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
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

	private Grid<Tarea> tareasGrid;
	private Binder<Tarea> tareaBinder = new Binder<>(Tarea.class);
	
	private Button verDetalleTarea;
	private Button editarTarea;
	private Button borrarTarea;
	private Button verCalendario;	

	private Select<String> tiempo;
	
	private H3 tituloTarea;
	private Label detalle;

	private H5 tituloEjecutores;
	private Label ejecutores;

	private H5 tituloEjecutoresActuales;
	private Label ejecutoresActuales;
	private Label creada;	
	

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067861213020325L;

} // END
