package com.front.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ms.front.MainView;
import com.ms.front.model.Persona;
import com.ms.front.services.MiTareaService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.data.selection.SingleSelect;

class MisTareasView extends VerticalLayout {

	private MainView main;
	private Persona persona;
	private MiTareaService service;

	public MisTareasView(MainView main, Persona persona) {

		this.main = main;
		this.persona = persona;

		this.service = new MiTareaService();

		render();
	}

	private void render() {		

		usuario = new H5(persona.getApellido() + ", " + persona.getNombre());
//		usuario.addClassName("centered-content");

		List<OPTareaSinHacer> itemsOP  = null; ; //service.findOPTareasSinHacer();
//		List<TareaSinHacer> itemsTareas = service.findTareasSinHacer(null);		

		if (itemsOP.size() > 0) {

			// ---------------------------------------------------------------------

			detalle = new Label();
			ordenesProduccion = new ComboBox<OPTareaSinHacer>();
			tareasSinHacer = new Grid<TareaSinHacer>();

			// ---------------------------------------------------------------------

			ordenesProduccion.addValueChangeListener(event -> {
				if (event.getValue() != null) {
					setItemsTtareasSinHacer(event.getValue().getTareas());
				} else {
					tareasSinHacer.setItems(new ArrayList<TareaSinHacer>());
				}
			});
			ordenesProduccion.setWidthFull();
			ordenesProduccion.setLabel("Ordenes de producción");
			ordenesProduccion.setItems(itemsOP);

			ordenesProduccion.setValue(itemsOP.get(0));

			// ---------------------------------------------------------------------

			tareasSinHacer.addThemeVariants(/* GridVariant.LUMO_NO_BORDER, */
					GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

			tareasSinHacer.setSelectionMode(SelectionMode.SINGLE);

			SingleSelect<Grid<TareaSinHacer>, TareaSinHacer> itemSelect = tareasSinHacer.asSingleSelect();
			itemSelect.addValueChangeListener(e -> {
				TareaSinHacer selected = e.getValue();
				// System.out.println(selected);
//				binderTareaSinHacer.setBean(selected);
			});

			tareasSinHacer.addSelectionListener(event -> {
				Optional<TareaSinHacer> selected = event.getFirstSelectedItem();
				if (selected != null && selected.isPresent()) {
					System.out.println(selected.get());
					binderTareaSinHacer.setBean(selected.get());
				}
			});

			tareasSinHacer.addItemClickListener(event -> System.out.println(("Clicked Item: " + event.getItem())));

			tareasSinHacer.addItemDoubleClickListener(
					event -> System.out.println(("Double Clicked Item: " + event.getItem())));

			tareasSinHacer.addColumn(TareaSinHacer::toString).setHeader("Tareas").setKey("tareas");

			setItemsTtareasSinHacer(itemsOP.get(0).getTareas());

			// ---------------------------------------------------------------------

			ReadOnlyHasValue<String> detalleRO = new ReadOnlyHasValue<>(text -> detalle.setText(text));
			binderTareaSinHacer.forField(detalleRO).bind(TareaSinHacer::toString, null);

			// ---------------------------------------------------------------------

//			addClassName("centered-content");
			add(usuario, ordenesProduccion, tareasSinHacer, detalle);

			// ---------------------------------------------------------------------

		} else {

			// ---------------------------------------------------------------------

			noTareas = new Label("No hay tareas para hacer!!");
			noTareas.setSizeFull();

			noTareas.addClassName("centered-content");
			add(usuario, noTareas);

			// ---------------------------------------------------------------------
		}

	}

	private void setItemsTtareasSinHacer(List<TareaSinHacer> items) {
		tareasSinHacer.setItems(items);
		if (items.size() > 0) {
			tareasSinHacer.select(items.get(0));
		}
	}

	// ---------------------------------------------------------------------

	private H5 usuario;
	private ComboBox<OPTareaSinHacer> ordenesProduccion;
	private Label noTareas;
	private Grid<TareaSinHacer> tareasSinHacer;
	private Binder<TareaSinHacer> binderTareaSinHacer = new Binder<>(TareaSinHacer.class);

	private Label nroTarea;
	private Label detalle;

	/**
	 * 
	 */
	private static final long serialVersionUID = -619318158914955833L;

}
