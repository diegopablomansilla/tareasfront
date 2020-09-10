package com.ms.front.services;

import com.ms.front.commons.model.PostArgs;
import com.ms.front.commons.services.Service;
import com.ms.front.model.Persona;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class LoginService {

	public Persona login(String user, String pass) {
		try {

			String urlString = "login";

			LoginArgs args = new LoginArgs();
			args.setUser(user);
			args.setPass(pass);

			String json = Service.LOGIN(urlString, new PostArgs(), args);

			if (json != null) {
				Persona item = new Persona();
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

}
