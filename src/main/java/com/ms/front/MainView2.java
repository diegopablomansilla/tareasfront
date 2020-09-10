package com.ms.front;

import com.ms.front.services.views.LoginMisTareasView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route("mistareas")
@PWA(name = "Gestión de tareas", shortName = "Tareas", enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView2 extends VerticalLayout {

	public MainView2() {

		// --------------------------------------------------------------------------------------

//		String apiHome = "https://api.massoftware.com";
//		String apiHome = "http://localhost:4567";
//		String apiHome = "http://localhost:8081/tareasapi/rpc/v1/";
//		String apiVersion = "v1";
		// --------------------------------------------------------------------------------------

//		System.out.println("Start");

//		try {

//			EnvVars.setApiHome(apiHome);
//			EnvVars.setApiVersion(apiVersion);
			// --------------------------------------------------------------------------------------

			// --------------------------------------------------------------------------------------

//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		this.add(new LoginMisTareasView(this));

	}

	// ---------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -619318158914975843L;

}
