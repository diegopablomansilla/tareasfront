package com.ms.front.services.views;

import java.util.List;

import com.ms.front.MainView2;
import com.ms.front.model.MiTarea;
import com.ms.front.model.Persona;
import com.ms.front.model.TareaPersona;
import com.ms.front.services.LoginService;
import com.ms.front.services.MiTareaEnEjecucionService;
import com.ms.front.services.MiTareaService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class LoginMisTareasView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4202068861213020121L;

	private MainView2 main;

	private TextField user;
	private PasswordField password;

	public LoginMisTareasView(MainView2 main) {

		this.main = main;

		H4 titulo = new H4("Gestión de mis tareas");

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
//		Persona persona = new Persona();
//		persona.setId("1");
//		persona.setNombre("Diego");
//		persona.setApellido("Mansilla");
//		persona.setSeccionId("9");
//		persona.setPuestoId("6");

		if (persona != null) {
			
			main.add(new MisTareasView(main, persona));

//			MiTareaEnEjecucionService service = new MiTareaEnEjecucionService();
//			List<TareaPersona> items = service.findAll(persona.getId());
			main.remove(this);
//			if (items.size() > 0) {
//				MiTarea item = new MiTareaService().findById(items.get(0).getTareaId());
//				if (item != null) {
//					main.add(new MiTareaEditView(main, persona, item, true));
//				} else {
//					main.add(new MisTareasView(main, persona));
//				}
//			} else {
//				main.add(new MisTareasView(main, persona));
//			}

		} else {
			Notification notification = new Notification("Usuario y/o clave incorrectos!!", 3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();
		}
	}

}
