package com.ms.front.commons.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ConfirmationDialog extends Dialog {

	// ---------------------------------------------------------------------------------------------------------------------------

	public H5 title;
	public H4 msg;
	public Button confirm;
	public Button abort;

	// ---------------------------------------------------------------------------------------------------------------------------

	public ConfirmationDialog(String t, String q) {

		title = new H5(t);
		title.getStyle().set("color", "#2196f3");

		msg = new H4(q);
		msg.getStyle().set("color", "#607d8b");

		add(title);
		add(msg);

		abort = new Button("No");
		abort.addClickListener(buttonClickEvent -> close());

		confirm = new Button("Si");
		confirm.addClickListener(buttonClickEvent -> close());
		confirm.addThemeVariants(ButtonVariant.LUMO_ERROR);

		HorizontalLayout footer = new HorizontalLayout();
		footer.add(abort, confirm);
		footer.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

		add(footer);
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 40152453846491114L;

} // END CLASS -----------------------------------------------------------------
