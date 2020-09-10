package com.ms.front.services;

import java.util.ArrayList;
import java.util.List;

import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.TareaPersona;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class MiTareaEnEjecucionService {

	public List<TareaPersona> findAll(String persona) {

		List<TareaPersona> items = new ArrayList<TareaPersona>();

		try {

			String urlString = "MiTareaEnEjecucion/findAll";

			MiTareaEnEjecucionNoPaginArgs args = new MiTareaEnEjecucionNoPaginArgs();			
			args.setPersona(persona.toString());

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
				
				j++;
				if (t[i][j] != null) {
					item.setTareaId(t[i][j].toString());
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
