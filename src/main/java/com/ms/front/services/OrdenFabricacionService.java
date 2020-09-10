package com.ms.front.services;

import java.util.ArrayList;
import java.util.List;

import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.OrdenFabricacion;
import com.ms.front.model.OrdenFabricacionOLDNOUSAR;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class OrdenFabricacionService {

	public List<OrdenFabricacion> findAll() {

		List<OrdenFabricacion> items = new ArrayList<OrdenFabricacion>();

		try {

			String urlString = "OrdenFabricacion/findAll";

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs());

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				OrdenFabricacion item = new OrdenFabricacion();

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

	public List<OrdenFabricacionOLDNOUSAR> findAllOLDNOUSAR() {

		List<OrdenFabricacionOLDNOUSAR> items = new ArrayList<OrdenFabricacionOLDNOUSAR>();

		try {

			String urlString = "OrdenFabricacion/findAll";

			NoPagin pagin = Service.GETNoPagin(urlString, new NoPaginArgs());

			if (pagin.getThisPageItems() == null || pagin.getThisPageItems().length == 0) {
				return items;
			}

			Object[][] t = pagin.getThisPageItems();

			for (int i = 0; i < t.length; i++) {

				OrdenFabricacionOLDNOUSAR item = new OrdenFabricacionOLDNOUSAR();

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
					item.setCantidadTareas(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setCantidadTareasCerradas(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setCantidadTareasCerradasPorcentaje(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setCantidadHoras(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setCantidadHorasCalculadas(t[i][j].toString());
				}

				j++;
				if (t[i][j] != null) {
					item.setCantidadTareasTomadasEnEjecucion(t[i][j].toString());
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
