package com.ms.front.services.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ms.front.EnvVars;
import com.ms.front.MainView;
import com.ms.front.commons.view.ConfirmationDialog;
import com.ms.front.model.OrdenFabricacion;
import com.ms.front.model.Persona;
import com.ms.front.model.Puesto;
import com.ms.front.model.Seccion;
import com.ms.front.model.Tarea;
import com.ms.front.services.OrdenFabricacionService;
import com.ms.front.services.PuestoService;
import com.ms.front.services.SeccionService;
import com.ms.front.services.TareaService;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.binder.Binder;

public class TareaEditView extends FormLayout {

	private MainView main;
	private Persona persona;
	private Tarea tarea;
	private boolean cerrada;

	private TareaService service;

	public TareaEditView(MainView main, Persona persona, Tarea tarea) {

//		tarea.setAdjunto("http://localhost:4567/RPC/v1/Tarea/download?id=" + tarea.getId());

		this.main = main;
		this.persona = persona;
		this.tarea = tarea;

		cerrada = "CERRADA".equals(tarea.getEstado());

		service = new TareaService();

		render();
	}

	private void render() {

		// ---------------------------------------------------------------------------

		Button volver = new Button("Volver");
		volver.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		volver.setIcon(new Icon(VaadinIcon.ARROW_BACKWARD));
		volver.addClickListener(event -> volver());

		Button guardarTarea = new Button("Guardar tarea");
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		guardarTarea.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		guardarTarea.setIcon(new Icon(VaadinIcon.CLOUD_UPLOAD));
//		editarTarea.addClassName("centered-content");
		guardarTarea.addClickListener(event -> guardarTarea());
		guardarTarea.setVisible(cerrada == false);

		Button cerrarTarea = new Button("Cerrar tarea");
		cerrarTarea.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		cerrarTarea.addThemeVariants(ButtonVariant.LUMO_ERROR);
		cerrarTarea.setIcon(new Icon(VaadinIcon.CHECK_SQUARE));
		cerrarTarea.setVisible(cerrada == false);
//		editarTarea.addClassName("centered-content");
		cerrarTarea.addClickListener(event -> cerrarTarea());

		HorizontalLayout botonera = new HorizontalLayout();
//		botonera.setSpacing(false);
//		botonera.setWidthFull();
		botonera.add(volver, guardarTarea, cerrarTarea);

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

		boolean tieneArchivo = tarea.getAdjunto() != null;

		if (cerrada) {
			if (tieneArchivo) {
				file.setVisible(true);
				upload.setVisible(false);
			} else {
				file.setVisible(false);
				upload.setVisible(false);
			}
		} else {
			if (tieneArchivo) {
				file.setVisible(true);
				upload.setVisible(false);
			} else {
				file.setVisible(false);
				upload.setVisible(true);
			}
		}

	}

	private void renderForm(Tarea tarea) {

		tareaBinder.setBean(tarea);

		List<OrdenFabricacion> ordenes = new OrdenFabricacionService().findAll();
		List<Seccion> secciones = new SeccionService().findAll();
		List<Puesto> puestos = new PuestoService().findAll();

		boolean readOnly = cerrada;

		// ---------------------------------------------------------------------------

		tituloTarea = new H3("Tarea (" + tarea.getId() + ") ");

		nombre = new TextField();
		nombre.setLabel("Nombre");
		nombre.setClearButtonVisible(true);
		nombre.setRequiredIndicatorVisible(true);
		nombre.setReadOnly(readOnly);
		nombre.setValue(tarea.getNombre());

		detalle = new TextArea();
		detalle.setLabel("Detalle");
		detalle.setClearButtonVisible(true);
		detalle.setRequiredIndicatorVisible(true);
		detalle.setReadOnly(readOnly);
		detalle.setValue(tarea.getDetalle() != null ? tarea.getDetalle() : "");
		detalle.setWidthFull();

		orden = new ComboBox<OrdenFabricacion>();
		orden.setLabel("Orden");
		orden.setRequiredIndicatorVisible(true);
		orden.setReadOnly(readOnly);
		orden.setClearButtonVisible(true);
		orden.setItems(ordenes);

		if (tarea.getSeccionId() != null) {
			if (ordenes.size() > 0) {
				for (OrdenFabricacion s : ordenes) {
					if (s.getId().equals(tarea.getOrdenFabricacionId())) {
						orden.setValue(s);
					}
				}
			}
		} else {
//			if(ordenes.size() > 0 ) {
//				orden.setValue(ordenes.get(0));
//			}	
		}

		seccion = new ComboBox<Seccion>();
		seccion.setLabel("Seccion");
		seccion.setRequiredIndicatorVisible(true);
		seccion.setReadOnly(readOnly);
		seccion.setClearButtonVisible(true);
		seccion.setItems(secciones);

		if (tarea.getSeccionId() != null) {
			if (secciones.size() > 0) {
				for (Seccion s : secciones) {
					if (s.getId().equals(tarea.getSeccionId())) {
						seccion.setValue(s);
					}
				}
			}
		} else {
//			if(secciones.size() > 0 ) {
//				seccion.setValue(secciones.get(0));
//			}	
		}

		puesto = new ComboBox<Puesto>();
		puesto.setLabel("Puesto");
		puesto.setReadOnly(readOnly);
		puesto.setClearButtonVisible(true);
		puesto.setItems(puestos);

		if (tarea.getPuestoId() != null) {
			if (puestos.size() > 0) {
				for (Puesto p : puestos) {
					if (p.getId().equals(tarea.getPuestoId())) {
						puesto.setValue(p);
					}
				}
			}
		} else {
//			if(secciones.size() > 0 ) {
//				seccion.setValue(secciones.get(0));
//			}	
		}

		horas = new NumberField();
		horas.setLabel("Horas declaradas");
		horas.setClearButtonVisible(true);
		horas.setReadOnly(readOnly);
		horas.addThemeVariants(TextFieldVariant.LUMO_ALIGN_RIGHT);
		if (tarea.getHoras() != null) {
			horas.setValue(Double.parseDouble(tarea.getHoras()));
		}

		horasCalculadasTitulo = new H5("Horas calculadas: ");
		personasTitulo = new H5("Ejecutores (" + tarea.getCantidadVecesTomada() + "): ");
		personasEnEjecucionTitulo = new H5("Ejecutores actuales (" + tarea.getCantidadEnEjecucion() + "): ");
		creadaTitulo = new H5("Tarea creada el:");

		horasCalculadas = new Label();
		horasCalculadas.setText(tarea.getHorasCalculadas());

		personas = new Label();
		personas.setText(tarea.getPersonas());

		personasEnEjecucion = new Label();
		personasEnEjecucion.setText(tarea.getPersonasEnEjecucion());

		creada = new Label();
		creada.setText(tarea.getFecha());

		String url = "";

		if (tarea.getAdjunto() != null && tarea.getAdjunto().endsWith("?id=" + tarea.getId())) {
			url = tarea.getAdjunto();
		}

		anchor = new Anchor(url, "Adjunto");

		buildUpload();

//		subirArchivo = new Button("Subir pdf");
//		subirArchivo.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//		subirArchivo.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
//		subirArchivo.setIcon(new Icon(VaadinIcon.UPLOAD));
////		editarTarea.addClassName("centered-content");
//		subirArchivo.addClickListener(event -> guardarTarea());

		eliminarArchivo = new Button("");
		eliminarArchivo.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		eliminarArchivo.addThemeVariants(ButtonVariant.LUMO_ERROR);
		eliminarArchivo.setIcon(new Icon(VaadinIcon.FILE_REMOVE));
//		editarTarea.addClassName("centered-content");
		eliminarArchivo.setVisible(readOnly == false);

		eliminarArchivo.addClickListener(event -> {
			eliminarArchivo();
		});

		file = new HorizontalLayout();
		file.setWidthFull();
		file.setPadding(false);
		file.setSpacing(false);
		file.add(eliminarArchivo, anchor);

		add(tituloTarea, nombre, horas, orden, seccion, puesto, detalle, horasCalculadasTitulo, horasCalculadas,
				personasTitulo, personas, personasEnEjecucionTitulo, personasEnEjecucion, creadaTitulo, creada, file,
				upload);

		setColspan(tituloTarea, 3);
		setColspan(orden, 3);
		setColspan(detalle, 3);
		setColspan(upload, 3);
		setColspan(file, 3);
//		setColspan(subirArchivo, 3);

		setMaxWidth("800px");
		addClassName("centered-content");

		// ---------------------------------------------------------------------------

	}

	private void buildUpload() {
		MemoryBuffer buffer = new MemoryBuffer();
		upload = new Upload(buffer);

		UploadI18N i18n = new UploadI18N();
		i18n.setDropFiles(
				new UploadI18N.DropFiles().setOne("Arrastra el pdf aquí...").setMany("Arrastre los archivos aquí..."))
				.setAddFiles(new UploadI18N.AddFiles().setOne("Seleccione un pdf").setMany("Agregar archivos"))
				.setCancel("Cancelar")
				.setError(new UploadI18N.Error().setTooManyFiles("Demasiados archivos.")
						.setFileIsTooBig("Archivo demasiado grande.").setIncorrectFileType("Tipo de archivo invalido."))
				.setUploading(new UploadI18N.Uploading()
						.setStatus(new UploadI18N.Uploading.Status().setConnecting("Conectado...")
								.setStalled("Descarga detenida.").setProcessing("Procesando..."))
						.setRemainingTime(new UploadI18N.Uploading.RemainingTime().setPrefix("tiempo restante: ")
								.setUnknown("el tiempo restante es desconocido"))
						.setError(
								new UploadI18N.Uploading.Error().setServerUnavailable("El servidor no esta disponible")
										.setUnexpectedServerError("Error inesperado del servidor")
										.setForbidden("Descarga prohibida")))
				.setUnits(Stream.of("B", "KB", "MEGABYTE", "GB", "Tbyte", "Pbytes", "Ebayt", "Zbayt", "Ibyte")
						.collect(Collectors.toList()));

		upload.setI18n(i18n);
		upload.setAcceptedFileTypes("application/pdf");

		upload.addSucceededListener(event -> {
			guardarArchivo(event.getMIMEType(), event.getFileName(), buffer.getInputStream());
		});
	}

	private void eliminarArchivo() {

		try {

			String path = service.getEnvironmentVariablesContents();
			if (path == null || path.trim().length() == 0) {
				throw new IllegalStateException("EnvironmentVariablesContents is null or empty");
			}
			path = path + "/" + tarea.getId().replace(".", "_") + ".pdf";
			path = path.replace("file:", "").replace("/", File.separatorChar + "");

			File targetFile = new File(path);
			if (targetFile.delete()) {
				tarea.setAdjunto(null);
				file.setVisible(false);
				upload.setVisible(true);

			} else {
				throw new IllegalStateException("No se pudo borrar el anterior adjunto.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
	}

	private void guardarArchivo(String mime, String fileName, InputStream initialStream) {
		try {
			// InputStream initialStream = new FileInputStream(new
			// File("src/main/resources/sample.txt"));
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);

			String path = service.getEnvironmentVariablesContents();
			if (path == null || path.trim().length() == 0) {
				throw new IllegalStateException("EnvironmentVariablesContents is null or empty");
			}
			path = path + "/" + tarea.getId().replace(".", "_") + ".pdf";
			path = path.replace("file:", "").replace("/", File.separatorChar + "");

			File targetFile = new File(path);
//			if (targetFile.delete()) {

			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			outStream.close();
			initialStream.close();

			upload.setVisible(false);

//			String urlAdjunto = EnvVars.getApiHome() + "/RPC/" + EnvVars.getApiVersion() + "/";
			String urlAdjunto = EnvVars.getApiHome(); 
			tarea.setAdjunto(urlAdjunto + "Tarea/download?id=" + tarea.getId());
			anchor.setHref(tarea.getAdjunto());
			file.setVisible(true);

//			} else {
//				throw new IllegalStateException("No se pudo borrar el anterior adjunto.");
//			}

		} catch (Exception e) {
			e.printStackTrace();
			
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
	}

	private void guardarTarea() {
		Tarea selected = tareaBinder.getBean();

		if (selected != null) {

			Tarea copySelected = new Tarea();
			copySelected.setId(selected.getId());

			if (nombre.isInvalid()) {
				Notification notification = new Notification("Nombre es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}
			if (seccion.isInvalid() || seccion.getValue() == null) {
				Notification notification = new Notification("Seccion es obligatorio", 3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
				return;
			}

			copySelected.setNombre(nombre.getValue());
			copySelected.setDetalle(detalle.getValue());
			if (orden.getValue() != null) {
				copySelected.setOrdenFabricacionId(orden.getValue().getId());
//				selected.setSeccion(selected.getSeccionId());
			}
			if (seccion.getValue() != null) {
				copySelected.setSeccionId(seccion.getValue().getId());
//				selected.setSeccion(selected.getSeccionId());
			}
			if (puesto.getValue() != null) {
				copySelected.setPuestoId(puesto.getValue().getId());
//				selected.setPuesto(selected.getPuestoId());
			}

			boolean ok = service.save(copySelected);
			if (ok) {
				guardarSuccessfullyNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
				volver();
			} else {
				guardarErrorNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
			}

//			volver();

		}

	}

	private void volver() {
		main.remove(this);
		main.add(new TareasView(main, persona));
	}

	public void cerrarTarea() {

		Tarea selected = tareaBinder.getBean();

		if (selected != null) {

			cerrarDialog("Tarea (" + selected.getId() + ") " + selected.getNombre(), buttonClickEvent -> {

				TareaService service = new TareaService();

				boolean ok = service.close(horas.getValue(), selected.getId());
				if (ok) {
					cerrarSuccessfullyNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
					volver();
				} else {
					cerraErrorNotification("Tarea (" + selected.getId() + ") ", selected.getNombre());
				}

			});

		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void cerrarDialog(String msg, ComponentEventListener listener) {

		String title = "¿ Estás seguro, quieres cerrar la tarea ?";

		ConfirmationDialog confirmationDialog = new ConfirmationDialog(title, msg);
		confirmationDialog.confirm.setIcon(new Icon(VaadinIcon.CHECK_SQUARE));
		confirmationDialog.confirm.setText("Cerrar");
		confirmationDialog.abort.setText("Cancelar");
		confirmationDialog.confirm.addClickListener(listener);
		confirmationDialog.open();
		confirmationDialog.abort.focus();
	}

	private void cerrarSuccessfullyNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " cerrado con éxito.", 2000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		notification.open();
	}

	private void cerraErrorNotification(String caption, String item) {

		Notification notification = new Notification(caption + " " + item + " no se pudo cerrar.", 1000,
				Position.BOTTOM_END);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
		notification.open();
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
	private TextField nombre;
	private TextArea detalle;
	private ComboBox<OrdenFabricacion> orden;
	private ComboBox<Seccion> seccion;
	private ComboBox<Puesto> puesto;
	private NumberField horas;

	private H5 horasCalculadasTitulo;
	private H5 personasTitulo;
	private H5 personasEnEjecucionTitulo;
	private H5 creadaTitulo;

	private Label horasCalculadas;
	private Label personas;
	private Label personasEnEjecucion;
	private Label creada;

	private Upload upload;
	private Anchor anchor;

//	private Button subirArchivo;
	private Button eliminarArchivo;
	private HorizontalLayout file;

	// -----------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202067862213020325L;

} // END
