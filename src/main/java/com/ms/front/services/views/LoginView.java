package com.ms.front.services.views;

import com.ms.front.MainView;
import com.ms.front.model.Persona;
import com.ms.front.services.LoginService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class LoginView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202068861213020121L;

	private MainView main;

	private TextField user;
	private PasswordField password;

	public LoginView(MainView main) {

		this.main = main;

		H4 titulo = new H4("Gestión de tareas");

		user = new TextField();
		user.setLabel("Usuario:");

		password = new PasswordField();
		password.setLabel("Password");

		Button button = new Button("Ingresar", e -> login());
		button.addClickShortcut(Key.ENTER);

		addClassName("centered-content");

		this.add(titulo, user, password, button);
	}

	private void login() {

		if (user.getValue() == null || user.getValue().trim().length() == 0 || password.getValue() == null
				|| password.getValue().trim().length() == 0) {

			Notification notification = new Notification("Debe completar los campos !!", 3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

			return;

		}

		Persona persona = new LoginService().login(user.getValue(), password.getValue());
//		Persona p = new Persona();
//		p.setId("1");
//		p.setNombre("Diego");
//		p.setApellido("Mansilla");

		if (persona != null) {

			if (persona.getRol() != null && persona.getRol().equals("A")) {
				main.remove(this);
//				main.add(new HomeView(main, service, p));			
				main.add(new TareasView(main, persona));
			} else {
				Notification notification = new Notification("El usuario no tiene permisos para ingresar a esta app!!",
						3000, Position.BOTTOM_END);
				notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
				notification.open();
			}

		} else {
			Notification notification = new Notification("Usuario y/o clave incorrectos!!", 3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
	}

}
