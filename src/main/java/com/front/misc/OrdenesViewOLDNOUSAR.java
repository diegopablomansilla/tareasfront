package com.front.misc;

import java.util.List;
import java.util.Optional;

import com.ms.front.MainView;
import com.ms.front.model.OrdenFabricacionOLDNOUSAR;
import com.ms.front.services.OrdenFabricacionService;
import com.ms.front.services.views.LoginView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;

class OrdenesViewOLDNOUSAR extends VerticalLayout {

	private MainView main;
	private PersonaX persona;

	public OrdenesViewOLDNOUSAR(MainView main, PersonaX persona) {

		this.main = main;
		this.persona = persona;

		render();
	}

	private void render() {

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());

		Button verDetalleOrden = new Button("Ver detalle de la orden");
		verDetalleOrden.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		verDetalleOrden.setIcon(new Icon(VaadinIcon.EXTERNAL_LINK));
//		verDetalleOrden.addClassName("centered-content");
		verDetalleOrden.addClickListener(event -> verDetalleOrden());

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, verDetalleOrden);

		add(botonera);

		// ----------------------------------------------

		H3 titulo = new H3("Ordenes de fabricación");
//		H5 tituloPersona = new H5(persona.toString());
//
//		HorizontalLayout hs = new HorizontalLayout();
//		hs.setSpacing(false);
//
//		hs.add(titulo, tituloPersona);

		this.add(titulo);

//		addClassName("centered-content");

		OrdenFabricacionService service = new OrdenFabricacionService();
		List<OrdenFabricacionOLDNOUSAR> ordenes = service.findAllOLDNOUSAR();

		if (ordenes.size() > 0) {
			renderListado(ordenes);
		} else {

		}

	}

	private void renderListado(List<OrdenFabricacionOLDNOUSAR> ordenes) {

		ordenesGrid = new Grid<OrdenFabricacionOLDNOUSAR>();

		ordenesGrid.addThemeVariants(/* GridVariant.LUMO_NO_BORDER, */
				GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

		ordenesGrid.setSelectionMode(SelectionMode.SINGLE);

//		SingleSelect<Grid<OrdenFabricacion>, OrdenFabricacion> itemSelect = ordenesGrid.asSingleSelect();
//		itemSelect.addValueChangeListener(e -> {
////			OrdenFabricacion selected = e.getValue();
//			// System.out.println(selected);
////						binderTareaSinHacer.setBean(selected);
//		});

		ordenesGrid.addSelectionListener(event -> {
			Optional<OrdenFabricacionOLDNOUSAR> selected = event.getFirstSelectedItem();
			if (selected != null && selected.isPresent()) {
				ordenFabricacionBinder.setBean(selected.get());
			}
		});

//		ordenesGrid.addItemClickListener(event -> System.out.println(("Clicked Item: " + event.getItem())));
//
//		ordenesGrid
//				.addItemDoubleClickListener(event -> System.out.println(("Double Clicked Item: " + event.getItem())));

		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getId).setHeader("Nro").setKey("id").setWidth("100px");
		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getNombre).setHeader("Denominación").setKey("nombre");
		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getCantidadTareasCerradasPorcentaje).setHeader("Avance")
				.setKey("cantidadTareasCerradasPorcentaje");

		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getCantidadTareas).setHeader("Total").setKey("cantidadTareas");
		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getCantidadTareasCerradas).setHeader("Cerradas")
				.setKey("cantidadTareasCerradas");
		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getCantidadTareasTomadasEnEjecucion).setHeader("En ejecución")
				.setKey("cantidadTareasTomadasEnEjecucion");

		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getCantidadHoras).setHeader("Declaradas").setKey("cantidadHoras");
		ordenesGrid.addColumn(OrdenFabricacionOLDNOUSAR::getCantidadHorasCalculadas).setHeader("Calculadas")
				.setKey("cantidadHorasCalculadas");

		// Create a header row
		HeaderRow topRow = ordenesGrid.prependHeaderRow();

		Label columnsTareas = new Label("Tareas");
		columnsTareas.addClassName("centered-content");
		columnsTareas.setWidthFull();

		HorizontalLayout tareasColumns = new HorizontalLayout();
		tareasColumns.setSpacing(false);
		tareasColumns.setWidthFull();
		tareasColumns.add(columnsTareas);

		topRow.join(ordenesGrid.getColumnByKey("cantidadTareas"), ordenesGrid.getColumnByKey("cantidadTareasCerradas"),
				ordenesGrid.getColumnByKey("cantidadTareasTomadasEnEjecucion")).setComponent(tareasColumns);

		Label columnsHoras = new Label("Horas");
		columnsHoras.addClassName("centered-content");
		columnsHoras.setWidthFull();

		HorizontalLayout horasColumns = new HorizontalLayout();
		horasColumns.setSpacing(false);
		horasColumns.setWidthFull();
		horasColumns.add(columnsHoras);

		topRow.join(ordenesGrid.getColumnByKey("cantidadHoras"), ordenesGrid.getColumnByKey("cantidadHorasCalculadas"))
				.setComponent(horasColumns);

		setItemsOrdenesGrid(ordenes);

		add(ordenesGrid);

		// ---------------------------------------------------------------------------

	}

	private void setItemsOrdenesGrid(List<OrdenFabricacionOLDNOUSAR> items) {
		ordenesGrid.setItems(items);
		if (items.size() > 0) {
			ordenesGrid.select(items.get(0));
		}
	}

	private void verDetalleOrden() {
		OrdenFabricacionOLDNOUSAR selected = ordenFabricacionBinder.getBean();

		if (selected != null) {
			main.remove(this);
//			main.add(new TareasView(main, persona, selected));
		}
	}

	private void volver() {
		main.remove(this);
		main.add(new LoginView(main));
	}

	// -----------------------------------------------------------

	private Grid<OrdenFabricacionOLDNOUSAR> ordenesGrid;
	private Binder<OrdenFabricacionOLDNOUSAR> ordenFabricacionBinder = new Binder<>(OrdenFabricacionOLDNOUSAR.class);

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202068861213020125L;

} // END
