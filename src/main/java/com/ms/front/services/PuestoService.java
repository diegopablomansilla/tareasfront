package com.ms.front.services;

import java.util.ArrayList;
import java.util.List;

import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.Puesto;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class PuestoService {

	public List<Puesto> findAll() {

		List<Puesto> items = new ArrayList<Puesto>();

		try {

			String urlString = "Puesto/findAll";

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs());

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				Puesto item = new Puesto();

				int j = 0;
				if (t[i][j] != null) {
					item.setId(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setNombre(t[i][j].toString());
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
