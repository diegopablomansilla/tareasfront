package com.ms.front;

import com.ms.front.services.views.LoginView;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("tareas")
//@PWA(name = "Gestión de tareas", shortName = "Tareas", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

	public MainView() {		
		
		// URL: /tareas

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

		this.add(new LoginView(this));

//		System.out.println("End");
	}

	// ---------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -619318158914955843L;

}
