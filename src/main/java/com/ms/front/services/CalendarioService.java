package com.ms.front.services;

import java.util.ArrayList;
import java.util.List;

import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.model.PostArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.CalendarioItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class CalendarioService {

	public boolean delete(String id) {
		try {

			String urlString = "Calendario/delete";

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

	public boolean save(CalendarioItem item) {
		try {

			String urlString = "Calendario/save";

			CalendarioSaveArgs args = new CalendarioSaveArgs();

			args.setId(item.getId());
			args.setHoraInicio(item.getHoraInicio());
			args.setHoraCierre(item.getHoraCierre());

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

	public boolean create(CalendarioItem item) {
		try {

			String urlString = "Calendario/create";

			CalendarioCreateArgs args = new CalendarioCreateArgs();

			args.setFecha(item.getFecha());
			args.setHoraInicio(item.getHoraInicio());
			args.setHoraCierre(item.getHoraCierre());

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

	public List<CalendarioItem> findAll() {

		List<CalendarioItem> items = new ArrayList<CalendarioItem>();

		try {

			String urlString = "Calendario/findAll";

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs());

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				CalendarioItem item = new CalendarioItem();

				int j = 0;
				if (t[i][j] != null) {
					item.setId(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setDia(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setFecha(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setHoraInicio(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setHoraCierre(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setEtiqueta(t[i][j].toString());
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
