package com.ms.front.commons.view;

import java.util.Arrays;

import com.vaadin.flow.component.datepicker.DatePicker.DatePickerI18n;

public class UIDatePickerI18n_es_AR extends DatePickerI18n {

	// ---------------------------------------------------------------------------------------------------------------------------

	public UIDatePickerI18n_es_AR() {
		super();

		setWeek("Semana");
		setCalendar("Calendario");
		setClear("Limpiar");
		setToday("Hoy");
		setCancel("Cancelar");
		setFirstDayOfWeek(1);

		setMonthNames(Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
				"Septiembre", "Octubre", "Noviembre", "Diciembre"));

		setWeekdays(Arrays.asList("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"));

		setWeekdaysShort(Arrays.asList("Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sab"));
	}

	// ---------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 6593159149382641423L;

} // END CLASS -----------------------------------------------------------------
