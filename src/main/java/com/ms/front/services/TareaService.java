package com.ms.front.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.model.PostArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.Tarea;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class TareaService {

	public boolean delete(String id) {
		try {

			String urlString = "Tarea/delete";

			TareaDeleteArgs args = new TareaDeleteArgs();
			args.setId(id);

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

	public boolean close(Double horas, String id) {
		try {

			if (horas == null) {
				horas = 0.0;
			}

			String urlString = "Tarea/close";

			TareaCloseArgs args = new TareaCloseArgs();
			args.setId(id);
			args.setHoras(horas.toString());

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

	public boolean save(Tarea tarea) {
		try {

			String urlString = "Tarea/save";

			TareaSaveArgs args = new TareaSaveArgs();

			args.setId(tarea.getId());
			args.setNombre(tarea.getNombre());
			args.setDetalle(tarea.getDetalle());
			args.setOrdenFabricacion(tarea.getOrdenFabricacionId());
			args.setSeccion(tarea.getSeccionId());
			args.setPuesto(tarea.getPuestoId());

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

	public boolean create(Tarea tarea) {
		try {

			String urlString = "Tarea/create";

			TareaCreateArgs args = new TareaCreateArgs();

			args.setNombre(tarea.getNombre());
			args.setDetalle(tarea.getDetalle());
			args.setOrdenFabricacion(tarea.getOrdenFabricacionId());
			args.setSeccion(tarea.getSeccionId());
			args.setPuesto(tarea.getPuestoId());

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

	public List<Tarea> findAll(Integer meses) {
		return findAll(null, null, meses);
	}

	public List<Tarea> findAll(String ordenFabricacion, Boolean cerrada, Integer meses) {

		List<Tarea> items = new ArrayList<Tarea>();

		try {

			String urlString = "Tarea/findAll";

			TareaNoPaginArgs args = new TareaNoPaginArgs();
			args.setOrdenFabricacion(ordenFabricacion);
			if (cerrada != null) {
				args.setCerrada(cerrada.toString());
			}
			if (meses != null) {
				args.setMeses(meses.toString());
			}

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs(), args);

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				Tarea item = new Tarea();

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
					item.setEstado(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setHoras(t[i][j].toString());
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
					item.setCantidadVecesTomada(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setHorasCalculadas(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setPersonas(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setCantidadEnEjecucion(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setPersonasEnEjecucion(t[i][j].toString());
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

				j++;
				if (t[i][j] != null) {
					item.setCerrada(t[i][j].toString());
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

	public String getEnvironmentVariablesContents() {

		try {

			String urlString = "EnvironmentVariables/contents";

			String json = Service.GET(urlString);

			if (json == null || json.trim().length() == 0) {
				return null;
			}

			JsonObject jo = Json.createReader(new StringReader(json)).readObject();

			if (jo == null) {
				return null;
			}

			if (jo.isEmpty()) {
				return null;
			}

//			String att = "payload";
//
//			if (jo.containsKey(att) && jo.isNull(att)) {
//				return null;
//			}
//
//			jo = jo.getJsonObject(att);

			String att = "id";

			if (jo.containsKey(att) && !jo.isNull(att)) {
				return jo.getString(att);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en la app.", 3000,
					Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
		return null;

	}

}
