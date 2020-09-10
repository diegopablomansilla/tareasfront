package com.front.misc;

import com.ms.front.MainView;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

class HomeView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2576149070949515232L;
	
	private MainView main;
	private ServiceOT service;

	public HomeView(MainView main, ServiceOT service, PersonaX persona) {

		this.main = main;
		this.service = service;

		H4 titulo = new H4(persona.toString());
		
		
		this.add(titulo);

	}

}
