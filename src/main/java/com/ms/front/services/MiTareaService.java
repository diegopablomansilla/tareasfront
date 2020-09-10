package com.ms.front.services;

import java.util.ArrayList;
import java.util.List;

import com.ms.front.commons.model.ByIdArgs;
import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.model.PostArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.MiTarea;
import com.ms.front.model.TareaPersona;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class MiTareaService {

	public boolean save(String tareaId, String personaId, String comentarios) {
		try {

			String urlString = "MiTarea/save";

			MiTareaSaveArgs args = new MiTareaSaveArgs();

			args.setTareaId(tareaId);
			args.setPersonaId(personaId);
			args.setComentarios(comentarios);

			return Service.POST(urlString, new PostArgs(), args);

		} catch (Exception e) {
			e.printStackTrace();
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}

		return false;

	}

	public boolean create(String tareaId, String personaId) {
		try {

			String urlString = "MiTarea/create";

			MiTareaCreateArgs args = new MiTareaCreateArgs();

			args.setTareaId(tareaId);
			args.setPersonaId(personaId);

			return Service.POST(urlString, new PostArgs(), args);

		} catch (Exception e) {
			e.printStackTrace();
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}

		return false;

	}

	public List<MiTarea> findAll(String seccion, String puesto) {

		List<MiTarea> items = new ArrayList<MiTarea>();

		try {

			String urlString = "MiTarea/findAll";

			MiTareaNoPaginArgs args = new MiTareaNoPaginArgs();
			if (seccion != null) {
				args.setSeccion(seccion.toString());
			}
			if (puesto != null) {
				args.setPuesto(puesto.toString());
			}

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs(), args);

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				MiTarea item = new MiTarea();

				int j = 0;
				if (t[i][j] != null) {
					item.setId(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setNombre(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setDetalle(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setOrdenFabricacion(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setSeccion(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setPuesto(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setAdjunto(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setFecha(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setOrdenFabricacionId(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setSeccionId(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setPuestoId(t[i][j].toString());
				}

				items.add(item);
			}

			return items;

		} catch (Exception e) {
			e.printStackTrace();
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
		return items;

	}

	public MiTarea findById(String id) {

		try {

			String urlString = "MiTarea/findById";

			ByIdArgs args = new ByIdArgs();
			args.setId(id);

			String json = Service.GETById(urlString, new ByIdArgs(), args);

			if (json != null) {
				MiTarea item = new MiTarea();
				item.fromJson(json);

				return item;
			}

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
		return null;

	}

	public List<TareaPersona> findAll(String tarea) {

		List<TareaPersona> items = new ArrayList<TareaPersona>();

		try {

			String urlString = "TareaPersona/findAll";

			TareaPersonaNoPaginArgs args = new TareaPersonaNoPaginArgs();
			args.setTarea(tarea);

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs(), args);

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				TareaPersona item = new TareaPersona();

				int j = 0;
				if (t[i][j] != null) {
					item.setId(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setApellido(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setNombre(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setTomaTarea(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setSueltaTarea(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setComentarios(t[i][j].toString());
				}

				items.add(item);
			}

			return items;

		} catch (Exception e) {
			e.printStackTrace();
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
		return items;

	}

}
