package com.ms.front.services.views;

import java.util.List;
import java.util.Optional;

import com.ms.front.MainView;
import com.ms.front.model.Persona;
import com.ms.front.model.Tarea;
import com.ms.front.model.TareaPersona;
import com.ms.front.services.TareaPersonaService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;

public class TareasPersonaView extends VerticalLayout {

	private MainView main;
	private Persona persona;
	private Tarea tarea;

	public TareasPersonaView(MainView main, Persona persona, Tarea tarea) {

		this.main = main;
		this.persona = persona;
		this.tarea = tarea;

		render();
	}

	private void render() {

		// ---------------------------------------------------------------------------

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());
		
		Button asignarTarea = new Button("Asignar tarea");
		asignarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		asignarTarea.setIcon(new Icon(VaadinIcon.PLUS_CIRCLE));
		asignarTarea.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
//		crearTarea.addClassName("centered-content");
		asignarTarea.addClickListener(event -> asignarTarea());

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, asignarTarea);

		add(botonera);

		// ----------------------------------------------

		H3 titulo = new H3("Historial de la tarea (" + tarea.getId() + ") " + tarea.getNombre());
//		H5 tituloPersona = new H5(persona.toString());
//
//		HorizontalLayout hs = new HorizontalLayout();
//		hs.setSpacing(false);
//
//		hs.add(titulo, tituloPersona);

		this.add(titulo);

//		addClassName("centered-content");

		TareaPersonaService service = new TareaPersonaService();
		List<TareaPersona> tareas = service.findAll(tarea.getId());

		if (tareas.size() > 0) {
			renderListado(tareas);
		} else {

		}

	}

	private void renderListado(List<TareaPersona> tareas) {

		tareasGrid = new Grid<TareaPersona>();

		tareasGrid.addThemeVariants(/* GridVariant.LUMO_NO_BORDER, */
				GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

		tareasGrid.setSelectionMode(SelectionMode.SINGLE);

//		SingleSelect<Grid<OrdenFabricacionTarea>, OrdenFabricacionTarea> itemSelect = ordenesGrid.asSingleSelect();
//		itemSelect.addValueChangeListener(e -> {
////			OrdenFabricacion selected = e.getValue();
//			// System.out.println(selected);
////						binderTareaSinHacer.setBean(selected);
//		});

		tareasGrid.addSelectionListener(event -> {
			Optional<TareaPersona> selected = event.getFirstSelectedItem();
			if (selected != null && selected.isPresent()) {
				tareaBinder.setBean(selected.get());
				select(selected.get());
			}
		});

//		tareasGrid.addItemClickListener(event -> System.out.println(("Clicked Item: " + event.getItem())));
//
//		tareasGrid
//				.addItemDoubleClickListener(event -> System.out.println(("Double Clicked Item: " + event.getItem())));

		tareasGrid.addColumn(TareaPersona::getApellido).setHeader("Apellido").setKey("apellido");
		tareasGrid.addColumn(TareaPersona::getNombre).setHeader("Nombre").setKey("nombre");//
		tareasGrid.addColumn(TareaPersona::getTomaTarea).setHeader("Comienza").setKey("tomaTarea");
		tareasGrid.addColumn(TareaPersona::getSueltaTarea).setHeader("Termina").setKey("sueltaTarea");

		add(tareasGrid);

		// ---------------------------------------------------------------------------

		tituloTarea = new H3("Tarea ##");
		detalle = new Label();

		add(tituloTarea, detalle);

		// ---------------------------------------------------------------------------

		setItemsTareasGrid(tareas);

	}

	private void setItemsTareasGrid(List<TareaPersona> items) {
		tareasGrid.setItems(items);
		if (items.size() > 0) {
			tareasGrid.select(items.get(0));
		}
	}

	private void volver() {
		main.remove(this);
		main.add(new TareasView(main, persona));
	}
	
	private void asignarTarea() {		
		main.remove(this);
		main.add(new TareaPersonaEditView(main, persona, tarea));

	}

	private void select(TareaPersona item) {
		if (item != null) {

			tituloTarea.setText("");
			detalle.setText("");

			if (item.getComentarios() != null) {
				tituloTarea.setText(item.getApellido() + ", " + item.getNombre() + " comenta:");
				detalle.setText(item.getComentarios());
			}

		}
	}

	// -----------------------------------------------------------

	private Grid<TareaPersona> tareasGrid;
	private Binder<TareaPersona> tareaBinder = new Binder<>(TareaPersona.class);

	private H3 tituloTarea;
	private Label detalle;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067361213020325L;

} // END
