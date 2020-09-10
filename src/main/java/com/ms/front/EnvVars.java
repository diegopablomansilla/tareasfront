package com.ms.front;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class EnvVars {

	private static String apiHome;

	private EnvVars() {

	}

	public static String getApiHome() {
		if (apiHome == null || apiHome.trim().length() == 0) {
			loadApiHome();
		}

		return apiHome;
	}

	private static void setApiHome(String apiHome) {
		EnvVars.apiHome = apiHome.trim();
	}

	private static void loadApiHome() {
		try {

//			String TAREAS_API_HOME = System.getenv().get("TAREAS_API_FILES");
//
//			if (TAREAS_API_HOME == null || TAREAS_API_HOME.trim().length() == 0) {
//				TAREAS_API_HOME = "http://localhost:8081/tareasapi/rpc/v1/";
//
//				System.out.println(
//						"[WARNING] No se encontro la variable de entorno TAREAS_API_HOME. Se usa como valor por defecto "
//								+ TAREAS_API_HOME);
//			}
//
//			setApiHome(TAREAS_API_HOME);

			String TAREAS_API_FILES = System.getenv().get("TAREAS_API_FILES");

			if (TAREAS_API_FILES == null || TAREAS_API_FILES.trim().length() == 0) {
				TAREAS_API_FILES = "C:/tareasapi";

				System.out.println(
						"[WARNING] No se encontro la variable de entorno TAREAS_API_FILES. Se usa como valor por defecto "
								+ TAREAS_API_FILES);
			}

			FileReader reader = new FileReader(TAREAS_API_FILES + File.separatorChar + "tareasapi.properties");

			Properties p = new Properties();
			p.load(reader);

			setApiHome(p.getProperty("api.host"));

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
