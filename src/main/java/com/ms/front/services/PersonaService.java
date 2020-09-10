package com.ms.front.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.Persona;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class PersonaService {
	


	public List<Persona> findAll(String seccionId, String puestoId) {

		List<Persona> items = new ArrayList<Persona>();

		try {

			String urlString = "Persona/findAll";

			PersonaNoPaginArgs args = new PersonaNoPaginArgs();
			args.setSeccionId(seccionId);
			args.setPuestoId(puestoId);

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs(), args);

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				Persona item = new Persona();

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

//				j++;
//				if (t[i][j] != null) {
//					item.setSeccionId(t[i][j].toString());
//				}
//
//				j++;
//				if (t[i][j] != null) {
//					item.setPuestoId(t[i][j].toString());
//				}

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

			String att = "payload";

			if (jo.containsKey(att) && jo.isNull(att)) {
				return null;
			}

			jo = jo.getJsonObject(att);

			att = "id";

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
