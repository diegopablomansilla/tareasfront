package com.front.misc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class ServiceOT {

	private String[][] tabla_tareas_persona;
	
	public ServiceOT() {
		
	}

	public void init() {

//		String sql = "INSERT INTO IndOrdenesDeFabricacion (ORDENFABRICACION, DENOMINACION ) "
//				+ "VALUES (:numero, ':nombre');";
//
//		String s = "";
//		for (int i = 0; i < tabla_op.length; i++) {
//
//			s += "\n" + sql.replace(":numero", this.tabla_op[i][0]).replace(":nombre", this.tabla_op[i][1])
//
//			;
//
//		}

		Random r = new Random();

		String sql = "INSERT INTO Tarea (id, nombre, detalle, ordenFabricacion, seccion, puesto, cerrada, horas, fecha ) "
				+ "VALUES (':numero', ':nombre', ':detalle',:orden, :seccion, :puesto, :cerrada, :horas, :fecha);";

		String s = "";

		int numeroTarea = 1;
		for (int i = 0; i < tabla_op.length; i++) {

			if (r.nextBoolean()) {
				continue;
			}

			for (int j = 1; j <= 20; j++) {

				LocalDateTime ldt = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd h:m:ss a");

				String fecha = "CAST('" + formatter.format(ldt).replace("p. m.", "PM").replace("a. m.", "AM")
						+ "' AS DATETIME)";

				String codigo = numeroTarea + "." + ldt.getMonthValue() + "." + ldt.getYear();

				int index = ThreadLocalRandom.current().nextInt(0, tabla_secciones.length - 1);

				String seccion = this.tabla_secciones[index][0];

				index = ThreadLocalRandom.current().nextInt(0, tabla_puestos.length - 1);

				String puesto = this.tabla_puestos[index][0];

				String orden = this.tabla_op[i][0];
				String detalle = "Tarea " + j + " - OP: " + this.tabla_op[i][1];

				if (r.nextBoolean()) {
					puesto = "null";
				}

				if (r.nextBoolean()) {
					orden = "null";
					detalle = " Una tarea sin orden de fabricación. Los pasos que se deben segui son ...";
				}

				s += "\n" + sql.replace(":numero", codigo).replace(":nombre", "Tarea " + codigo)
						.replace(":detalle", detalle).replace(":orden", orden).replace(":seccion", seccion)
						.replace(":puesto", puesto).replace(":cerrada", "0").replace(":horas", "null")
						.replace(":fecha", fecha)

				;

				numeroTarea++;

				if (r.nextBoolean()) {

					String sql2 = "INSERT INTO TareaPersona (tomaTarea, sueltaTarea, comentarios, tarea, persona ) "
							+ "VALUES (:toma, :suelta, ':comentario', ':numero', :personal);";

					int c = 1;

					if (r.nextBoolean()) {
						c++;
					}

					if (r.nextBoolean()) {
						c++;
					}

					for (int k = 1; k <= c; k++) {

						index = ThreadLocalRandom.current().nextInt(0, tabla_personal.length - 1);

						String persona = this.tabla_personal[index][0];

						String hs = "CONVERT(DATETIME, '" + ldt.getYear() + "-" + ldt.getMonthValue() + "-dd HH:"
								+ ldt.getMinute() + ":" + ldt.getSecond() + "', 102)";

						int h = ldt.getHour() + 1;
						int d = ldt.getDayOfMonth() + 1;

						String toma = hs.replace("HH", h + "").replace("dd", d + "");
						h++;
						String suelta = hs.replace("HH", h + "").replace("dd", d + "");

						s += "\n" + sql2.replace(":toma", toma).replace(":suelta", ((k == 3) ? "null" : suelta))
								.replace(":comentario", ((k == 3) ? "null" : "Este es un comentario del operario"))
								.replace(":numero", codigo).replace(":personal", persona);

					}
				}

			}

		}

		System.out.println(s);

		try {
			String ruta = "D:\\filename.txt";
			String contenido = s;
			File file = new File(ruta);
			// Si el archivo no existe es creado
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contenido);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------

	public PersonaX login(String user, String password) {

		if (user == null) {
			return null;
		}

		user = user.trim();

		if (password == null) {
			return null;
		}

		password = password.trim();

		for (int i = 0; i < tabla_personal.length; i++) {
			if (tabla_personal[i][0].trim().equals(user) && tabla_personal[i][0].trim().equals(password)) {

				int c = 0;

				PersonaX p = new PersonaX();
				p.setId(tabla_personal[i][c]);
				c++;
//				p.setSeccion(new SeccionX(tabla_personal[i][c]));
				c++;
//				p.setPuesto(new PuestoX(tabla_personal[i][c]));
				c++;
				p.setCuil(tabla_personal[i][c]);
				c++;
				p.setApellido(tabla_personal[i][c]);
				c++;
				p.setNombre(tabla_personal[i][c]);

				return p;
			}
		}

		return null;

	}

	public List<OPTareaSinHacer> findOPTareasSinHacer() {

		List<OPTareaSinHacer> items = new ArrayList<OPTareaSinHacer>();

		for (int i = 0; i < 10; i++) {
			OPTareaSinHacer item = new OPTareaSinHacer();
			item.setId((i + 1) + "");
			item.setNumero((i + 1));
			item.setDetalle("OP " + ((char) (65 + i)));
			if (i != 5) {
				item.setTareas(findTareasSinHacer(item));
			}

			items.add(item);
		}

		return items;

	}

	private List<TareaSinHacer> findTareasSinHacer(OPTareaSinHacer op) {

		List<TareaSinHacer> items = new ArrayList<TareaSinHacer>();

		for (int i = 0; i < 25; i++) {
			TareaSinHacer item = new TareaSinHacer();
			item.setId((i + 1) + "");
			item.setNumero((i + 1));
			item.setDetalle("Tarea " + ((char) (65 + i)) + " - " + op);

			items.add(item);
		}

		return items;

	}

	private String[][] tabla_secciones = new String[][] {

			{ "1", "Administración" }, { "2", "Comercialización" }, { "18", "Maestranza" },
			{ "21", "Producción Cilindros" }, { "14", "Producción Control" }, { "10", "Producción Corte y Plegado" },
			{ "12", "Producción Depósito" }, { "8", "Producción Electric. y Refrig." },
			{ "3", "Producción Ensamblaje 1" }, { "4", "Producción Ensamblaje 2" }, { "5", "Producción Ensamblaje 3" },
			{ "6", "Producción Ensamblaje 4" }, { "7", "Producción Ensamblaje 5" }, { "17", "Producción Ensamblaje 6" },
			{ "11", "Producción Fondos" }, { "15", "Producción Gerencia Técnica" }, { "9", "Producción Mecanizado" },
			{ "16", "Producción Oficina Técnica" }, { "13", "Producción Pulido" }, { "20", "Servicio Externo" },
			{ "19", "Sistema gestión de calidad" }

	};

	private String[][] tabla_puestos = new String[][] { { "1", "Contabilidad" }, { "2", "Cuentas Corrientes" },
			{ "3", "Ventas Industriales" }, { "4", "Ventas Agro" }, { "5", "Líder" }, { "6", "Operario" },
			{ "7", "Ayudante" }, { "8", "Aprendiz" }, { "9", "Jefe de Fábrica" }, { "10", "Gerente Producción" },
			{ "11", "Jefe" }, { "12", "Diseñador" }, { "13", "Dibujante" }, { "14", "Maestranza" },
			{ "15", "Calidad" } };

	private String[][] tabla_personal = new String[][]

	{

			{ "1", "2", "4", "20241193463", "Alvarez", "Pablo César" },
			{ "2", "3", "5", "20142178339", "Arce", "Miguel Angel" },
			{ "4", "4", "5", "20107014919", "Auce", "José Antonio" },
			{ "5", "8", "5", "20266464933", "Bertholet", "Ivan Jesús" },
			{ "6", "5", "5", "20083635119", "Bringas", "Humberto José" },
			{ "7", "3", "6", "20130154337", "Caballero", "Luis Gregorio" },
			{ "9", "6", "5", "20130155260", "Camandona", "Jorge del Valle" },
			{ "10", "10", "7", "20066046088", "Cisneros", "Angel Marcelo" },
			{ "11", "13", "6", "20084975452", "Delgado", "Esteban Reyes" },
			{ "14", "14", "9", "20122750877", "Gobbi", "Miguel Angel" },
			{ "17", "4", "6", "20104492240", "Juncos", "Aldo Luis" },
			{ "18", "12", "5", "20250368721", "Lazarte", "Ernesto" },
			{ "20", "6", "8", "20181583518", "Novillo", "Jose Aldo" },
			{ "21", "5", "7", "20216460929", "Novillo", "Sergio Daniel" },
			{ "23", "3", "6", "20110257296", "Palavecino", "Roberto Enrique" },
			{ "25", "6", "6", "23274449209", "Peralta", "César José" },
			{ "27", "15", "10", "20202589503", "Pfaffen", "Francisco Santiago" },
			{ "28", "7", "5", "23146657419", "Ré", "Eduardo Tercilio" },
			{ "29", "16", "11", "20165750315", "Stefani", "Osvaldo Angel" },
			{ "31", "1", "2", "23263805674", "Zanichelli", "Gabriela Mariana" },
			{ "32", "9", "5", "20066074464", "Hinny", "Rafael Roque Oscar" },
			{ "33", "9", "6", "20247166301", "Moran", "Jorge Oscar" },
			{ "50", "4", "6", "20171453934", "Pailler", "Juan Alberto Ricardo" },
			{ "51", "2", "3", "20262072488", "Pfaffen", "Agustín Juan" },
			{ "52", "16", "12", "20255324889", "Melano", "Gustavo Daniel" },
			{ "53", "10", "5", "20203249021", "Vega", "Ramón del Valle" },
			{ "54", "11", "5", "20249195007", "Eusebio", "Pablo Ariel" },
			{ "56", "17", "5", "20250005734", "Ferreira", "Paublo César" },
			{ "57", "1", "1", "27280649800", "Mellano", "Cecilia Marcela" },
			{ "61", "19", "15", "20229991869", "Reynoso", "José María" },
			{ "63", "10", "7", "20332022114", "Montenegro", "Eduardo Omar" },
			{ "65", "12", "11", "20302850810", "Pochettino", "Fernando Luis" },
			{ "66", "16", "13", "20308180515", "Tarantola", "Mariano Javier" },
			{ "67", "9", "6", "20110996145", "Córdoba", "Leandro Alberto" },
			{ "68", "5", "7", "20314496583", "Gallegos", "Marcos Matías" },
			{ "69", "6", "7", "20286256857", "Izaguirre", "Rafael Fabián" },
			{ "70", "9", "7", "23178950819", "Rea Inostroza", "Carlos Alberto" },
			{ "71", "9", "7", "20327627407", "Gadara", "Franco Germán" },
			{ "72", "5", "7", "20280647579", "Tejada", "Alberto Omar" },
			{ "73", "7", "7", "20336959951", "Ventura", "David Emanuel" },
			{ "74", "17", "7", "20307718937", "Martinez", "Nolberto David" },
			{ "75", "8", "7", "23327961349", "Arras", "Mariano Martin" },
			{ "76", "2", "3", "23263809599", "Antonucci", "Claudio Antonio" },
			{ "77", "18", "14", "27343347486", "Orellano", "Yamila Noemi" },
			{ "78", "12", "7", "23270310389", "Chiappero", "Marcos Oscar" },
			{ "79", "11", "7", "20168402245", "Moreschini", "Gabriel Jose" },
			{ "80", "10", "8", "20267127949", "Ludueña", "Fabian Felix" },
			{ "81", "10", "8", "20340519419", "Olivares", "Pablo Sebastian" },
			{ "82", "12", "8", "20250996900", "Peralta", "Dario Oscar" },
			{ "83", "7", "8", "20317313919", "Videla", "Daniel Alejandro" },
			{ "84", "11", "8", "20330454580", "Sarasate", "Juan Carlos" },
			{ "85", "6", "8", "20266460601", "Caballero", "Juan Pablo" },
			{ "86", "8", "8", "20135411192", "Alonso", "Jorge Mario" },
			{ "87", "14", "11", "23164625699", "Fagonde", "Fernando Javier" },
			{ "88", "16", "8", "20342538534", "Sigliano", "Maico Alan" },
			{ "89", "16", "8", "20355561101", "Rinaudo", "Franco Omar" },
			{ "90", "16", "8", "20323897248", "Cuello", "Pedro Maximiliano" },
			{ "91", "10", "8", "20266464429", "Morcillo", "Luis Humberto" },
			{ "92", "10", "8", "20310800849", "Ayala", "Alejandro David" },
			{ "93", "11", "8", "20217573344", "Comini", "Fernando Luis" },
			{ "94", "9", "8", "20266339330", "Isonio", "Luis Alberto" },
			{ "95", "9", "8", "20272947253", "Teodori", "Juan Manuel" },
			{ "96", "9", "8", "20331988422", "Morales", "Alejandro Jose" },
			{ "97", "9", "8", "23291828949", "Guzman", "Mario Armando" },
			{ "98", "10", "8", "20214056780", "Baich", "Gustavo Ariel" },
			{ "99", "10", "8", "20280224112", "Esquivel", "Roque Alberto" },
			{ "100", "9", "8", "20299956246", "Segura", "Sergio Gustavo" },
			{ "101", "18", "8", "27301545636", "Marquez", "Andrea Soledad" },
			{ "102", "16", "8", "20349657121", "Siro", "Luciano" },
			{ "103", "9", "8", "20278980988", "Gamarra", "Claudio Ariel" },
			{ "104", "12", "8", "20355562949", "Bertello", "Daniel Alberto" },
			{ "105", "9", "8", "20384783814", "Alvarez", "Joaquin" },
			{ "106", "12", "8", "20356383150", "Leguizamon", "Cristian Esteban" },
			{ "107", "21", "8", "20327718127", "Capote", "Leonardo Ezequiel" }

	};

	private String[][] tabla_op = new String[][]

	{ { "1", "Conectores Fler" }, { "2", "MS Enfriadores 310512" }, { "3", "Accesorios varios" },
			{ "4", "Tableros enfriadores horizonta" }, { "5", "Acc. varios - 09-12" }, { "6", "Accesorios" },
			{ "100", "Tab. comando enfriad stock" }, { "101", "Tab. fuerza enfriadores stock" },
			{ "102", "Tab. comando tinas" }, { "103", "Tab. comando enfriadores stock" },
			{ "104", "Ampliacion electrica ETI" }, { "105", "TGBT ETI" }, { "106", "Accesorios nivel máximo" },
			{ "107", "Accesorios tinas queseras" }, { "108", "Tab. comando tinas" },
			{ "109", "Manten. soldadura vert. IMCAR" }, { "200", "Accesorios" }, { "300", "Tanque chasis 8000 lts" },
			{ "1001", "Diseño Tk y Silos STD" }, { "1153", "reparacion" }, { "1517", "Pasteurizador ESKIMO" },
			{ "2003", "Dosificador continuo cremoso" }, { "2010", "TRCS251AA-Termorecuperador" },
			{ "2032", "MDAQ123AA Desueradora Autom" }, { "2049", "Ampliación pasteurizador 3000" },
			{ "2091", "ELHC123AB Enfriad.leche 12300" }, { "2118", "CIP Automático" },
			{ "2156", "ELHC123AB Enfriad.leche 10000" }, { "2159", "ELVC682/1x7 Enfriad leche 6800" },
			{ "2199", "TRPA802AA Tanque tranp. 8000" }, { "2200", "TRPA802AA TK Transp 8000" },
			{ "2201", "TRPA802AA - Tanque trasp. 8000" }, { "2203", "LMBA201AD- Lavadora Bandejas" },
			{ "2212", "ELHC802AA Enfriador leche 8000" }, { "2218", "ELVC682AB Enfriador leche 6800" },
			{ "2220", "Enfriador leche" }, { "2221", "ELVC682AA Enfriador leche 6800" },
			{ "2222", "ELVC682/1x7 Enfriad leche 6800" }, { "2223", "ELVC682/1x7 Enfriad leche 6800" },
			{ "2224", "ELHC802AB - Enfriador 8000 lts" }, { "2225", "ELHC802AA Enfriador leche 8000" },
			{ "2226", "ELHC103AB Enfriad.leche 10000" }, { "2228", "ELHC123AC Enfriad. leche 12000" },
			{ "2229", "Enfriador 12000 lts" }, { "2247", "TSPL202AA Tanque asceptico" },
			{ "2257", "TRCS151AA-Termorecuperador" }, { "2260", "TRBC004AA Trozadora automatica" },
			{ "2271", "Planchadora n°1" }, { "2272", "LVQS001AA Lavadora quesos" },
			{ "2277", "TKBL501AA Tanque Balaza 500lts" }, { "2278", "TKBL102AA Tanque 1000 lts" },
			{ "2279", "FDLL380AA Filtro de linea" }, { "2280", "ENAG007AA Enfriador agua" },
			{ "2282", "TKAH301AA tanque agua 300l" }, { "2283", "IPLV102AB - Intercam a placas" },
			{ "2284", "ELVC302AB Enfriador leche 3000" }, { "2285", "ELVC302AB Enfriador leche 3000" },
			{ "2286", "TRCS301AA Termorecuperador 300" }, { "2288", "Instalación centro acopio" },
			{ "2289", "Bomba centrifuga" }, { "2290", "SECA001AA Tablero electrico" },
			{ "2291", "ELVA152AB enfriador leche 1500" }, { "2292", "TKBL501AA Tanque Balaza 500lts" },
			{ "2293", "TKBL102AA Tanque 1000 lts" }, { "2294", "FDLL380AA Filtro de linea" },
			{ "2295", "ENAG007AA Enfriador agua" }, { "2297", "TKAH301AA tanque agua 300l" },
			{ "2298", "IPLV102AB - Intercam a placas" }, { "2299", "ELVC302AB Enfriador leche 3000" },
			{ "2300", "ELVC302AB Enfriador leche 3000" }, { "2301", "TRCS301AA Termorecuperador 300" },
			{ "2303", "Instalación centro acopio" }, { "2304", "Bomba centrifuga" },
			{ "2305", "SECA001AA Tablero electrico" }, { "2306", "ELVA152AB enfriador leche 1500" },
			{ "2307", "TKBL501AA Tanque Balaza 500lts" }, { "2308", "TKBL102AA Tanque 1000 lts" },
			{ "2309", "FDLL380AA Filtro de linea" }, { "2310", "ENAG007AA Enfriador agua" },
			{ "2312", "TKAH301AA tanque agua 300l" }, { "2313", "IPLV102AB - Intercam a placas" },
			{ "2314", "ELVC302AB Enfriador leche 3000" }, { "2315", "ELVC302AB Enfriador leche 3000" },
			{ "2316", "TRCS301AA Termorecuperador 300" }, { "2318", "INSTALACION vENEZUELA" },
			{ "2319", "Bomba centrifuga" }, { "2320", "SECA001AA Tablero electrico" },
			{ "2321", "ELVA152AB enfriador leche 1500" }, { "2322", "TKBL501AA Tanque Balaza 500lts" },
			{ "2323", "TKBL102AA Tanque 1000 lts" }, { "2324", "FDLL380AA Filtro de linea" },
			{ "2325", "ENAG007AA Enfriador agua" }, { "2327", "TKAH301AA tanque agua 300l" },
			{ "2328", "IPLV102AB - Intercam a placas" }, { "2329", "ELVC302AB Enfriador leche 3000" },
			{ "2330", "ELVC302AB Enfriador leche 3000" }, { "2331", "TRCS301AA Termorecuperador 300" },
			{ "2333", "Instalación centro acopio" }, { "2334", "Bomba centrifuga" },
			{ "2335", "SECA001AA Tablero electrico" }, { "2336", "ELVA152AB enfriador leche 1500" },
			{ "2337", "TKBL501AA Tanque Balaza 500lts" }, { "2338", "TKBL102AA Tanque 1000 lts" },
			{ "2339", "FDLL380AA Filtro de linea" }, { "2340", "ENAG007AA Enfriador agua" },
			{ "2342", "TKAH301AA tanque agua 300l" }, { "2343", "IPLV102AB - Intercam a placas" },
			{ "2344", "ELVC302AB Enfriador leche 3000" }, { "2345", "ELVC302AB Enfriador leche 3000" },
			{ "2346", "TRCS301AA Termorecuperador 300" }, { "2348", "Instalación centro acopio" },
			{ "2349", "Bomba centrifuga" }, { "2350", "SECA001AA Tablero electrico" },
			{ "2351", "ELVA152AB enfriador leche 1500" }, { "2352", "TKBL501AA Tanque Balaza 500lts" },
			{ "2353", "TKBL102AA Tanque 1000 lts" }, { "2354", "FDLL380AA Filtro de linea" },
			{ "2355", "ENAG007AA Enfriador agua" }, { "2357", "TKAH301AA tanque agua 300l" },
			{ "2358", "IPLV102AB - Intercam a placas" }, { "2359", "ELVC302AB Enfriador leche 3000" },
			{ "2360", "ELVC302AB Enfriador leche 3000" }, { "2361", "TRCS301AA Termorecuperador 300" },
			{ "2363", "Instalación centro acopio" }, { "2364", "Bomba centrifuga" },
			{ "2365", "SECA001AA Tablero electrico" }, { "2366", "ELVA152AB enfriador leche 1500" },
			{ "2367", "TKBL501AA Tanque Balaza 500lts" }, { "2368", "TKBL102AA Tanque 1000 lts" },
			{ "2369", "FDLL380AA Filtro de linea" }, { "2370", "ENAG007AA Enfriador agua" },
			{ "2372", "TKAH301AA tanque agua 300l" }, { "2373", "IPLV102AB - Intercam a placas" },
			{ "2374", "ELVC302AB Enfriador leche 3000" }, { "2375", "ELVC302AB Enfriador leche 3000" },
			{ "2376", "TRCS301AA Termorecuperador 300" }, { "2378", "Instalación centro acopio" },
			{ "2379", "Bomba centrifuga" }, { "2380", "SECA001AA Tablero electrico" },
			{ "2381", "ELVA152AB enfriador leche 1500" }, { "2382", "TKBL501AA Tanque Balaza 500lts" },
			{ "2383", "TKBL102AA Tanque 1000 lts" }, { "2384", "FDLL380AA Filtro de linea" },
			{ "2385", "ENAG007AA Enfriador agua" }, { "2387", "TKAH301AA tanque agua 300l" },
			{ "2388", "IPLV102AB - Intercam a placas" }, { "2389", "ELVC302AB Enfriador leche 3000" },
			{ "2390", "ELVC302AB Enfriador leche 3000" }, { "2391", "TRCS301AA Termorecuperador 300" },
			{ "2393", "Instalación centro acopio" }, { "2394", "Bomba centrifuga" },
			{ "2395", "SECA001AA Tablero electrico" }, { "2396", "ELVA152AB enfriador leche 1500" },
			{ "2397", "TKBL501AA Tanque Balaza 500lts" }, { "2398", "TKBL102AA Tanque 1000 lts" },
			{ "2399", "FDLL380AA Filtro de linea" }, { "2400", "ENAG007AA Enfriador agua" },
			{ "2402", "TKAH301AA tanque agua 300l" }, { "2403", "IPLV102AB - Intercam a placas" },
			{ "2404", "ELVC302AB Enfriador leche 3000" }, { "2405", "ELVC302AB Enfriador leche 3000" },
			{ "2406", "TRCS301AA Termorecuperador 300" }, { "2408", "INST000AA Instalacion Acopio" },
			{ "2409", "Bomba centrifuga" }, { "2410", "SECA001AA Tablero electrico" },
			{ "2411", "ELVA152AB enfriador leche 1500" }, { "2412", "ELHC303AA Enf de leche 30.000" },
			{ "2415", "TRCS151AA-Termorecuperador" }, { "2418", "TRCS151AA-Termorecuperador" },
			{ "2421", "SVAC3403AA Silo 40000" }, { "2422", "Bases para bandejas" }, { "2423", "Mesada con bacha" },
			{ "2424", "pileta para tambo" }, { "2425", "pileta para tambo" }, { "2426", "PQVP004AA Prensa 4 cuerpos" },
			{ "2427", "TQCE1232AB Tina 12000" }, { "2428", "TQCE1232AB Tina 12000" },
			{ "2429", "PLTR123BA Plataforma trabajo" }, { "2430", "MDAQ123AB Mesa desueradora aut" },
			{ "2431", "CTQR420AA Cinta transportadora" }, { "2432", "CTRR401AA - Cinta transportado" },
			{ "2433", "Cinta transportadora" }, { "2434", "CTRC401AA - Cinta transportado" },
			{ "2435", "CTRC401AA - Cinta transportado" }, { "2437", "CTRC401AA - Cinta transportado" },
			{ "2438", "CCIP302AC Central CIP 3x3000" }, { "2439", "MTCB003AA Mesa Acidificación" },
			{ "2440", "MTCB003AA Mesa Acidificación" }, { "2441", "MTCB003AA Mesa Acidificación" },
			{ "2442", "MTCB003AA Mesa Acidificación" }, { "2443", "MTCB003AA Mesa Acidificación" },
			{ "2448", "SRVC201AA Recup Condensado" }, { "2449", "TCAS302AA Tanque agua Caliente" },
			{ "2453", "TTCS351AB Tunel termocontrac" }, { "2454", "TTCS351AB Tunel termocontrac" },
			{ "2455", "Balanza piso" }, { "2456", "SIFR270AA  Frio 2x7 Hp (Oreo)" },
			{ "2457", "SIFR270AA  Frio 2x7 Hp(Camara)" }, { "2458", "SIFR170AA  Frio1x7Hp(despacho)" },
			{ "2459", "SIFR170AA Frio1x7 Hp(Envasado)" }, { "2460", "MTCR180AA-Mesa de trabajo" },
			{ "2461", "MTCR180AA-Mesa de trabajo" }, { "2462", "ELVC502AA Enfriador 5000" },
			{ "2463", "ELVC502AA Enfriador 5000" }, { "2464", "ELVC502AA Enfriador 5000" },
			{ "2465", "ELVC502AA Enfriador 5000" }, { "2466", "ELVC502AA Enfriador 5000" },
			{ "2467", "ELVC502AA Enfriador 5000" }, { "2468", "ELVC502AA Enfriador 5000" },
			{ "2469", "ELVC502AA Enfriador 5000" }, { "2470", "ELVC502AA Enfriador 5000" },
			{ "2471", "ELVC502AA Enfriador 5000" }, { "2472", "ELVC502AA Enfriador 5000" },
			{ "2473", "ELVC502AA Enfriador 5000" }, { "2474", "ELVC502AA Enfriador 5000" },
			{ "2475", "ELVC502AA Enfriador 5000" }, { "2476", "ELVC502AA Enfriador 5000" },
			{ "2477", "ELVC502AA Enfriador 5000" }, { "2478", "ELVC502AA Enfriador 5000" },
			{ "2479", "ELVC502AA Enfriador 5000" }, { "2480", "ELVC502AA Enfriador 5000" },
			{ "2481", "ELVC502AA Enfriador 5000" }, { "2482", "ELVC502AA Enfriador 5000" },
			{ "2483", "ELVC502AA Enfriador 5000" }, { "2484", "ELVC502AA Enfriador 5000" }, { "2485", "T-313 Tanque" },
			{ "2490", "TQCE352AA - Tina 3500 lts" }, { "2491", "TQCE352AA - Tina 3500 lts" },
			{ "2492", "PLTR352AA - Plataforma trabajo" }, { "2493", "LMBC251AA Lavadora bandejas" },
			{ "2494", "LMBA251AA Lavadora de bandejas" }, { "2495", "PQVN006AA Prensa quesos" },
			{ "2496", "PQVN006AA Prensa quesos" }, { "2497", "Moldes plasticos reforzados" },
			{ "2498", "CASA010AA  Canastos saladero" }, { "2499", "Desgrasadora" },
			{ "2501", "ELVC582AA enfriador leche 5800" }, { "2502", "ELVC582AA enfriador leche 5800" },
			{ "2503", "ELVC582AA enfriador leche 5800" }, { "2504", "Enfriador leche 3600 usado" },
			{ "2505", "Enfriador 3600 1 x 5 usado" }, { "2506", "Plataforma trabajo tina 3000" },
			{ "2507", "TPMA202AA TK de proceso" }, { "2508", "Eje silo A Cabral" }, { "2511", "Cámaras sifonicas" },
			{ "2512", "Enfriador leche 8000 usado" }, { "2513", "Enfriador leche 3600 usado" },
			{ "2514", "Enfriador leche 5000 usado" }, { "2515", "BAND500AA Bandejas quesos" },
			{ "2516", "EBOM001AA Bomba 7,5 hP" }, { "2517", "reparación enfriador 6000 lts" },
			{ "2518", "PTLE502AA Pasteuriz 5000 l/h" }, { "2519", "PQVN006AA Prensa quesos" },
			{ "2520", "Sist Refrigeración Cámara" }, { "2521", "TRCS251AA Termorecuperador 250" },
			{ "2522", "Enfriador leche usado" }, { "2523", "TRCS251AA Termorecuperador 250" },
			{ "2524", "TRCS251AA Termorecuperador 250" }, { "2525", "Enfriador leche usado" },
			{ "2526", "TQCE602AE - Tina 6000 lts" }, { "2527", "TQCE602AE - Tina 6000 lts" },
			{ "2528", "MDMQ302AA Mesa desueradora man" }, { "2529", "MDMQ302AA Mesa desueradora man" },
			{ "2530", "Plataforma trabajo tinas 6000L" }, { "2531", "Electrobomba centrífuga" },
			{ "2532", "SFIN760AA -Sinfin envasadora" }, { "2533", "Gancho aparejo saladero" },
			{ "2534", "Enfriador usado Muller" }, { "2535", "ELHC103AB Enfriad.leche 10000" },
			{ "2536", "ELHC103AB Enfriad.leche 10000" }, { "2537", "Central CIP 3000 lts" },
			{ "2538", "Sistema Frio 2x10 Hp camara" }, { "2539", "DSTR123AA Filtro alim. masa" },
			{ "2540", "TULA001AA Lavadora carros" }, { "2541", "TULA001AA Lavadora" }, { "2542", "repuesto" },
			{ "2543", "Aparejo 2 tn Arroyo Cabral" }, { "2544", "Repuesto" }, { "2545", "Montaje Coop.Cabral" },
			{ "2546", "Stand Mercoláctea 2010" }, { "2547", "BASE SOLADURA VERTICAL" },
			{ "2548", "SVAC803AA Silo 80.000 l" }, { "2549", "SVAC803AA Silo 80.000 l" },
			{ "2550", "SVAC803AA Silo 80.000 l" }, { "2551", "Reparación Tolva AGD" },
			{ "2552", "TRPA103AA Unid Transp 10000" }, { "2553", "TKBL501AA Tanque Balaza 500lts" },
			{ "2554", "TKBL102AA Tanque 1000 lts" }, { "2555", "FDLL380AA Filtro de linea" },
			{ "2556", "Enfriador agua glicolada" }, { "2557", "Tanque agua helada 300 lts" },
			{ "2558", "Intercambiador a placas" }, { "2559", "TRCS301AA Termorecuperador 300" },
			{ "2560", "Instalaciones CVA" }, { "2561", "Electrobomba salida de leche" },
			{ "2562", "Tablero control electrobombas" }, { "2563", "ELVA152AB enfriador leche 1500" },
			{ "2564", "TKBL501AA Tanque Balaza 500lts" }, { "2565", "TKBL102AA Tanque 1000 lts" },
			{ "2566", "FDLL380AA Filtro de linea" }, { "2567", "Enfriador agua glicolada" },
			{ "2568", "Tanque agua helada 300 lts" }, { "2569", "Intercambiador a placas" },
			{ "2570", "TRCS301AA Termorecuperador 300" }, { "2571", "Instalaciones CVA" },
			{ "2572", "Electrobomba salida de leche" }, { "2573", "Tablero control electrobombas" },
			{ "2574", "ELVA152AB enfriador leche 1500" }, { "2575", "TKBL501AA Tanque Balaza 500lts" },
			{ "2576", "TKBL102AA Tanque 1000 lts" }, { "2577", "FDLL380AA Filtro de linea" },
			{ "2578", "TRCS301AA Termorecuperador 300" }, { "2579", "Tanque agua helada 300 lts" },
			{ "2580", "Intercambiador a placas" }, { "2581", "TRCS301AA Termorecuperador 300" },
			{ "2582", "Instalaciones CVA" }, { "2583", "Electrobomba salida de leche" },
			{ "2584", "Tablero control electrobombas" }, { "2585", "ELVA152AB enfriador leche 1500" },
			{ "2586", "TKBL501AA Tanque Balaza 500lts" }, { "2587", "TKBL102AA Tanque 1000 lts" },
			{ "2588", "FDLL380AA Filtro de linea" }, { "2589", "Enfriador de agua glicolada" },
			{ "2590", "Tanque agua helada 300 lts" }, { "2591", "Intercambiador a placas" },
			{ "2592", "TRCS301AA Termorecuperador 300" }, { "2593", "Instalaciones CVA" },
			{ "2594", "Electrobomba salida de leche" }, { "2595", "Tablero control electrobombas" },
			{ "2596", "ELVA152AB enfriador leche 1500" }, { "2597", "ampliacion past eskimo" },
			{ "2598", "SVAS403AD Silo 40.000 lts" }, { "2599", "SVAS603AC Silo 60.000 lts" },
			{ "2600", "Electrobomba centrifuga" }, { "2601", "Filtro doble de línea" },
			{ "2602", "Soldadora cilindros ETI" }, { "2603", "Caudalimetro" },
			{ "2604", "Enfriador a placas 15000 lts" }, { "2605", "SVAS104AB Silo 100.000 lts" },
			{ "2606", "Sistema eléctrico para silos" }, { "2607", "Lactofermentador 1500 lts" },
			{ "2608", "Pasteurizador 15000 lts" }, { "2609", "Tanque balanceador para crema" },
			{ "2610", "Bomba para crema" }, { "2611", "Enfriador a placas" },
			{ "2612", "TK almacenamiento crema 3000 l" }, { "2613", "TK almacenamiento crema 1500 l" },
			{ "2614", "Tablero eléctrico" }, { "2615", "Bomba para crema" }, { "2616", "Bomba para crema" },
			{ "2617", "Cinta trnasportadora 2500 mm" }, { "2618", "Cinta transportadora 1000 mm" },
			{ "2619", "soplador p/tunel termocontrac" }, { "2620", "Trozadora para block" },
			{ "2621", "Pasteurizador 5000 l/h" }, { "2622", "Trozadora doble estación" },
			{ "2623", "Enfriador de suero" }, { "2624", "Carreta movimiento de material" },
			{ "2625", "Carreta movimiento de material" }, { "2626", "Pileta para salado" },
			{ "2627", "Pileta para salado" }, { "2628", "Pileta para salado" }, { "2629", "Mesa de trabajo" },
			{ "2630", "Mesa de trabajo" }, { "2631", "Mesa de trabajo" }, { "2632", "Mesa de trabajo" },
			{ "2633", "Tunel de termocontracción" }, { "2634", "Balanza de piso" }, { "2635", "Contenedor plástico" },
			{ "2636", "Contenedor plástico" }, { "2637", "Tablero eléctrico" }, { "2638", "Paila 1000 lts" },
			{ "2639", "Sistema frío prensas" }, { "2640", "Sistema frío cámara oreo" },
			{ "2641", "Sistema frío cámara envasado" }, { "2642", "Sistema frío cámara con deshum" },
			{ "2643", "Sistema frío cámara con deshum" }, { "2644", "Enfriador de líquidos 40 HP" },
			{ "2645", "Lavado químico" }, { "2646", "Reparación termo Capilla del S" },
			{ "2649", "Trozadora para block" }, { "2651", "Lavadora de bandejas 3 etapas" },
			{ "2652", "Tina quesera 6000 lts" }, { "2653", "Silo 80000 lts sin patas" }, { "2654", "Paila 30 lts" },
			{ "2655", "Enfriador 1 x 7" }, { "2656", "Silo 40000 lts" }, { "2657", "Sistema eléctrico para silos" },
			{ "2658", "Enfriador vertical 5800 lts" }, { "2659", "Lavadora de bandejas 3 etapas" },
			{ "2660", "Lavadora de bandejas 3 etapas" }, { "2662", "Enfriador 10000 lts usado" },
			{ "2663", "Montaje Cerutti" }, { "2664", "Enfriador vertical 6800 lts" },
			{ "2665", "Enfriador vertical 6800 lts" }, { "2666", "Enfriador vertical 6800 lts" },
			{ "2667", "Enfriador vertical 6800 lts" }, { "2668", "Enfriador vertical 6800 lts" },
			{ "2669", "Enfriador vertical 6800 lts" }, { "2670", "Enfriador horizontal 12000 lts" },
			{ "2671", "Enfriador horizontal 12000 lts" }, { "2672", "Enfriador horizontal 12000 lts" },
			{ "2673", "Enfriador horizontal 12000 lts" }, { "2674", "Enfriador horizontal 10000 lts" },
			{ "2675", "Tanque balanza 500 lts" }, { "2676", "Tanque balanceador 1000 lts" },
			{ "2677", "Filtro de linea" }, { "2678", "Enfriador agua glicolada" },
			{ "2679", "Tanque agua helada 300 lts" }, { "2680", "Intercambiador a placas" },
			{ "2681", "Recuperador de calor 300 lts" }, { "2682", "Inst. Electromecánica centros" },
			{ "2683", "Electrobomba salida leche" }, { "2684", "Tablero control electrobombas" },
			{ "2685", "Enfriador de leche 1500 lts" }, { "2686", "Tanque balanza 500 lts" },
			{ "2687", "Tanque balanceador 1000 lts" }, { "2688", "Filtro de línea" },
			{ "2689", "Enfriador agua glicolada" }, { "2690", "Tanque agua helada 300 lts" },
			{ "2691", "Intercambiador a placas" }, { "2692", "Recuperador de calor 300 lts" },
			{ "2693", "Inst. electromecánica centros" }, { "2694", "Electrobomba salida de leche" },
			{ "2695", "Tablero control electrobombas" }, { "2696", "Enfriador de leche 1500 lts" },
			{ "2697", "Tanque balanza 500 lts" }, { "2698", "Tanque balanceador 1000 lts" },
			{ "2699", "Filtro de línea" }, { "2700", "Enfriador agua glicolada" },
			{ "2701", "Tanque agua helada 300 lts" }, { "2702", "Intercambiador a placas" },
			{ "2703", "Recuperador de calor 300 lts" }, { "2704", "Ins. Electromecánica Centros" },
			{ "2705", "Electrobomba salida leche" }, { "2706", "Tablero control electrobombas" },
			{ "2707", "Enfriador de leche 1500 lts" }, { "2708", "Tanque balanza 500 lts" },
			{ "2709", "Tanque balanceador 500 lts" }, { "2710", "Filtro de línea centros" },
			{ "2711", "Enfriador agua glicolada" }, { "2712", "Tanque agua helada 300 lts" },
			{ "2713", "Intercambiador a placas centro" }, { "2714", "Recuperador de calor 300 lts" },
			{ "2715", "Inst. electromecánica centro" }, { "2716", "Electrobomba salida leche Cent" },
			{ "2717", "Tab. Electrob. Centro" }, { "2718", "ELVA152AB" }, { "2719", "ELVC502AA" },
			{ "2720", "ELVC502AA" }, { "2721", "ELVC502AA" }, { "2722", "Enfriador de leche 4800 lts" },
			{ "2723", "Recuperador de calor 250 lts" }, { "2724", "Recuperador de calor 250 lts" },
			{ "2725", "Recuperador de calor 250 lts" }, { "2726", "Recuperador de calor 250 lts" },
			{ "2727", "Recuperador de calor 250 lts" }, { "2728", "Reparacion Tk 5500" },
			{ "2729", "ELHC602AA - Enfriador de leche" }, { "2730", "Mesa para moldes" },
			{ "2731", "Mesa para moldes" }, { "2732", "Mesa para moldes" }, { "2733", "Paila reprocesadora de queso" },
			{ "2734", "Silo 80000 lts" }, { "2735", "Sistema eléctrico para silo" },
			{ "2736", "TRCS401AA-Termorecuperador 400" }, { "2737", "Paila reprocesadora de queso" },
			{ "2738", "Paila reprocesadora de queso" }, { "2739", "Tina quesera 8000 lts" },
			{ "2740", "Tina quesera 8000 lts" }, { "2741", "Tina quesera 8000 lts" },
			{ "2742", "Plataforama tina 8000" }, { "2743", "Tablero Neumatico Kumey P2" }, { "2744", "Gabinete Kumey" },
			{ "2749", "Cañerias Pre-ensambladas Kumey" }, { "2750", "Mesa chedarizadora 8000 lts" },
			{ "2751", "Dosificador automático" }, { "2752", "Estación pone tapas" }, { "2753", "Gabinete Kumey" },
			{ "2761", "Estación sacatapas" }, { "2763", "Estación volteo y vaciado" },
			{ "2765", "Batea enfriamiento de quesos" }, { "2766", "Batea enfriamiento de quesos" },
			{ "2767", "Lavadora de moldes y tapas" }, { "2769", "TRCS151AA - Termorecuperador" },
			{ "2770", "TRCS151AA - Termorecuperador" }, { "2771", "TRCS151AA - Termorecuperador" },
			{ "2772", "TRCS151AA - Termorecuperador" }, { "2773", "TRCS151AA - Termorecuperador" },
			{ "2774", "TRCS151AA - Termorecuperador" }, { "2775", "TRCS151AA - Termorecuperador" },
			{ "2776", "TRCS151AA - Termorecuperador" }, { "2777", "TRCS151AA - Termorecuperador" },
			{ "2778", "TRCS151AA - Termorecuperador" }, { "2779", "TRCS251AA - Termorecuperador" },
			{ "2780", "TRCS251AA - Termorecuperador" }, { "2781", "TRCS251AA - Termorecuperador" },
			{ "2782", "TRCS251AA - Termorecuperador" }, { "2783", "TRCS251AA - Termorecuperador" },
			{ "2784", "TRCS251AA - Termorecuperador" }, { "2785", "TRCS251AA - Termorecuperador" },
			{ "2786", "TRCS251AA - Termorecuperador" }, { "2787", "TRCS251AA - Termorecuperador" },
			{ "2788", "TRCS151AA - Termorecuperador" }, { "2789", "ELVC502AA - Enfriador de leche" },
			{ "2790", "Tina quesera 6000 lrs" }, { "2791", "Plataforma tina 6000 lts" },
			{ "2792", "Batea chorro de agua" }, { "2793", "Tina quesera 3000 lts" },
			{ "2794", "Tina quesera 3000 lts" }, { "2795", "Plataforma tinas 3000 lts" },
			{ "2796", "Desueradora 3000 lts" }, { "2797", "Lavado automático para enfriad" },
			{ "2798", "Ampliación preprensa" }, { "2799", "Tanque de proceso - Crema" },
			{ "2800", "Tanque proceso - Suero" }, { "2801", "Tanque proceso - Agua caliente" },
			{ "2802", "Lavado automático para enfriad" }, { "2803", "Lavado automático para enfriad" },
			{ "2804", "Pasteurizador 5000 lts" }, { "2805", "CTCR401AB - Cinta transportado" },
			{ "2806", "CTRC401AA - Cinta transportado" }, { "2807", "CTRC401AA - Cinta transportado" },
			{ "2808", "CTCR401AA - Cinta transportado" }, { "2809", "CTRR401AA - Cinta transportado" },
			{ "2810", "CTRC401AA - Cinta transportado" }, { "2811", "Tina quesera 12000 lts" },
			{ "2812", "Tina quesera 12000 lts" }, { "2813", "Tina quesera 12000 lts" },
			{ "2814", "Prensa de platos 7 cuerpos" }, { "2815", "Sistema frig. cámaras 12 HP" },
			{ "2816", "Sistema frig. cámaras 12 HP" }, { "2817", "Silo 30000 lts" }, { "2818", "Silo 30000 lts" },
			{ "2819", "ELVC682AA" }, { "2820", "ELVC682AA" }, { "2821", "Sistema frig cámaras 5 HP" },
			{ "2822", "Lavamanos 3 posiciones" }, { "2823", "Lavabotas 2 posiciones" }, { "2824", "Paila de ricota" },
			{ "2825", "Tanque balanceador 100 lt" }, { "2826", "Tanque balanceador 100 lt" },
			{ "2827", "Electrobomba 3 HP" }, { "2828", "Interc a placas para suero" }, { "2829", "Plataforma" },
			{ "2830", "Interc a placas 5000 l/h" }, { "2831", "Reparación pasteurizador" },
			{ "2832", "Reparación intercambiador" }, { "2833", "Pasteurizador para suero 15000" },
			{ "2834", "Filtro recuperador de solidos" }, { "2835", "Filtro recuperador de solidos" },
			{ "2836", "Filtro recuperador de solidos" }, { "2839", "Tina quesera 500 lts" },
			{ "2840", "Plataforma de trabajo tina 500" }, { "2841", "Mesa desueradora 500 lts" },
			{ "2842", "Electrobomba" }, { "2843", "Plataforma tanque transporte" },
			{ "2844", "Tanque proceso 15000 lts" }, { "2845", "Tanque proceso 15000 lts" },
			{ "2848", "Electrobomba para agua" }, { "2849", "Tanque hidroneumático 370 lts" },
			{ "2850", "Silo 30000 lts" }, { "2851", "Electrobomba 4HP para recibo" }, { "2852", "Central CIP 500 lts" },
			{ "2853", "Pasteurizador 5000 l/h" }, { "2854", "Desnatadora usada 5000 l/h" },
			{ "2855", "Tina quesera 3000 lts" }, { "2856", "Tina quesera 3000 lts" },
			{ "2857", "Plataforma trabajo tina 3000 l" }, { "2858", "Mesa desueradora 3000 l" },
			{ "2859", "Electrobomba para suero" }, { "2860", "Tanque almacenamiento de suero" },
			{ "2861", "Electrobomba para suero" }, { "2862", "Prensa neumática 6 cuerpos" },
			{ "2863", "Prensa neumática 6 cuerpos" }, { "2864", "Mesa de moldeo" }, { "2865", "Mesa de moldeo" },
			{ "2866", "Mesa transporte de moldes" }, { "2867", "Batea de limpieza" }, { "2868", "Mesa de trabajo" },
			{ "2869", "Mesa de trabajo" }, { "2870", "Envasadora vacío" }, { "2871", "Batea de termocontracción" },
			{ "2872", "Balanza de piso" }, { "2873", "Zorra manual" }, { "2874", "Bins plástico" },
			{ "2875", "Moldes cuartirolo (240)" }, { "2876", "Moldes Typo (150)" }, { "2877", "Moldes sardo (100)" },
			{ "2878", "Bandejas cremoso (1000)" }, { "2879", "Caldera 700 Kgvapor/h - BOILER" },
			{ "2880", "Tanque para agua 500 l" }, { "2881", "Compresor de aire 2 HP" },
			{ "2882", "Sistema frigorífico 5HP - Sala" }, { "2883", "Sistema frigorífico 5HP - Sala" },
			{ "2884", "Sistema frigorífico 5HP - Oreo" }, { "2885", "Sistema frigorífico 2HP - Enva" },
			{ "2886", "Sistema frigorífico 5HP - C.Fr" }, { "2887", "Sistema frigorífico 5HP - Mad" },
			{ "2888", "Saladero compacto manual" }, { "2889", "Electrobomba para CIP" },
			{ "2890", "Bandejas sardo (300)" }, { "2891", "Tolva de la Breitner" }, { "2892", "Enfriador de salmuera" },
			{ "2893", "Mesa desueradora 3000 lts" }, { "2894", "ELVC502AA" }, { "2895", "ELVC502AA" },
			{ "2896", "ELVC502AA" }, { "2897", "ELVC502AA" }, { "2898", "ELHC103AB - 2x6" },
			{ "2899", "Mesa de moldeo" }, { "2901", "Tk Hidroneumático 2000 lts" },
			{ "2903", "Elevador tijera despacho" }, { "2904", "Tanque CIP 8000 lts aislado" },
			{ "2905", "Tanque CIP 8000 lts aislado" }, { "2906", "Tanque CIP 8000 lts aislado" },
			{ "2907", "Tanque CIP 8000 lts sin aislar" }, { "2908", "Tanque mezcla ácido 30000 lts" },
			{ "2909", "Tanque hidroneumático 370 lts" }, { "2910", "Montaje Lácteos San Lucas" },
			{ "2911", "Lavado químico automático" }, { "2912", "Lavado químico automático" },
			{ "2913", "Lavado químico automático" }, { "2914", "Lavado químico automático" },
			{ "2915", "Lavado químico automático" }, { "2916", "Silo 100000 lts - SVAS104AB" },
			{ "2917", "Tunel termocontracción" }, { "2918", "Cámara sifónica" }, { "2919", "Montaje La Cenobia" },
			{ "2920", "ELVC582AA - 1x7" }, { "2921", "ELVC682AA - 1x7" }, { "2922", "Tanque proceso 9000 lts - NIZA" },
			{ "2923", "Intercambiador 15000 lts/h" }, { "2925", "Plegados" },
			{ "2926", "Tanque proceso cristalización" }, { "2927", "Tanque proceso cristalización" },
			{ "2928", "Tanque proceso cristalización" }, { "2929", "Central CIP" },
			{ "2930", "Recuperador de calor 250 l" }, { "2931", "ELHC802 - 2 x 6" },
			{ "2932", "Termorecuperador 150 lts" }, { "2933", "Cinta transportadora 2,5 mts" },
			{ "2934", "Cinta transportadora 1m" }, { "2935", "Enfriador 15000 2 x 7" }, { "2936", "ELHC103AB 2x6HP" },
			{ "2937", "TRCS251AA" }, { "2938", "ELVC682AA-1x7HP" }, { "2939", "Instalación Eq Osmosis Inversa" },
			{ "2940", "Inst. pasteurizador BALMOR" }, { "2941", "Descarga Tina 6000  usada" },
			{ "2942", "Mezclador Z" }, { "2943", "TRCS251AA" }, { "2944", "TRCS251AA" }, { "2945", "TRCS251AA" },
			{ "2946", "TRCS251AA" }, { "2947", "TRCS251AA" }, { "2948", "TRCS251AA" }, { "2949", "TRCS251AA" },
			{ "2950", "TRCS251AA" }, { "2951", "TRCS251AA" }, { "2952", "TRCS251AA" },
			{ "2953", "Monoriel Deposito liquidos inf" }, { "2954", "Tina quesera 1500 lts" },
			{ "2955", "Mesa desueradora 1500 lts" }, { "2956", "Plataforma tina 1500 lts" },
			{ "2957", "Silo crema 40000 lts" }, { "2958", "Enfriador vertical 5800 lts" },
			{ "2959", "Tanque pulmón crema 350 lts" }, { "2960", "Mezclador tambor excéntrico" },
			{ "2961", "Batea chorro de agua" }, { "2962", "Desueradora 3000 lts" },
			{ "2963", "Plat de trabajo tina 3000" }, { "2964", "Tina quesera 3000 lts" }, { "2965", "Silo 80000 lts" },
			{ "2966", "Enfriador 5500 lts usado" }, { "2967", "Paila reprocesadora de queso" },
			{ "2968", "Recuperador de calor 400 lts" }, { "2969", "Tina quesera 3000 lts" },
			{ "2970", "Paila reprocesadora de queso" }, { "2971", "Alimentador de barras" },
			{ "2972", "Distribuidor de masa cuartirol" }, { "2973", "Montaje Tatay" },
			{ "2974", "Enfriador hor 8000 lts 2 x 6" }, { "2975", "Enfriador hor 15000 - 2x7" },
			{ "2976", "Trozadora quesos" }, { "2977", "Pulidora tanques" }, { "2978", "Torre de moldeo" },
			{ "2979", "Elevador soldadora cilindros" }, { "2980", "Recuperador de calor 400 lts" },
			{ "2981", "Enfriador Bauducco usado 4000" }, { "2982", "Enfriador de leche usado 3600" },
			{ "2983", "Tanque p/suero 4200 lts" }, { "2984", "Tina 12000 usada" },
			{ "2985", "Dispositivos mesa banda interc" }, { "2986", "Filtro de mangas" },
			{ "2987", "Filtro de mangas" }, { "2988", "Plataforma chorro de agua" }, { "2989", "TRCS151AA" },
			{ "2990", "TRCS151AA" }, { "2991", "TRCS151AA" }, { "2992", "TRCS151AA" }, { "2993", "TRCS151AA" },
			{ "2994", "TRCS251AA" }, { "2995", "TRCS251AA" }, { "2996", "TRCS251AA" }, { "2997", "TRCS251AA" },
			{ "2998", "TRCS251AA" }, { "2999", "TRCS251AA" }, { "3000", "TRCS251AA" }, { "3001", "TRCS251AA" },
			{ "3002", "TRCS251AA" }, { "3003", "TRCS251AA" }, { "3004", "Tanque proceso 15000 lts" },
			{ "3005", "Enfriador horizontal 8000 lts" }, { "3006", "Prensa vertical a pesas 6 cuer" },
			{ "3007", "Enfriador ELHC802AB - 2x5" }, { "3008", "Tina quesera 3500 lts" },
			{ "3009", "Tina quesera 3500 lts" }, { "3010", "Plataf. de trabajo tina 3500" },
			{ "3011", "Central CIP 6000 lts 4x1500" }, { "3012", "Tanque proceso aceite 10000 lt" },
			{ "3013", "Templadora de chocolate" }, { "3014", "Intercambiador placas p/ suero" },
			{ "3015", "Recuperador de calor TRCS401AA" }, { "3016", "Pasteurizador 500 l/h" },
			{ "3017", "Torre Astori - ETI" }, { "3018", "Carro para fondos" }, { "3019", "Mesa corte de discos" },
			{ "3020", "Mesa para fondos" }, { "3021", "Banco soldadura de accesorios" },
			{ "3022", "Serie Stock PI 0001" }, { "3023", "Freezer 175 lts" },
			{ "3024", "Tina quesera 12000 lts ex NOAL" }, { "3025", "Montaje Silo OP 2916" },
			{ "3026", "Tanque almacenamiento 15000 lt" }, { "3027", "Tanque almacenamiento 20000 lt" },
			{ "3028", "Tanque proceso 15000 lts" }, { "3029", "Tanque proceso 15000 lts" },
			{ "3030", "Tanque p/acoplado 21000 lts" }, { "3031", "Enfriador ELVC682/1x7" },
			{ "3032", "Automatización Tinas Chile" }, { "3033", "Cañerias pre ensambladas" },
			{ "3034", "Distribuidor de cuajada" }, { "3035", "Distribuidor de cuajada" }, { "3036", "Silo 60000 lts" },
			{ "3037", "Silo 60000 lts" }, { "3038", "Silo 30000 lts" }, { "3039", "Tanque vertical 180 lts" },
			{ "3040", "Tanque vertical 180 lts" }, { "3041", "Tanque vertical 180 lts" },
			{ "3042", "Tanque vertical 180 lts" }, { "3043", "Tanque vertical 180 lts" },
			{ "3044", "Esterilizador p/leche 3000 l/h" }, { "3045", "Silo 100 m³" },
			{ "3046", "Pasteurizador 10000 l/h" }, { "3047", "Intercambiador 10000 l/h" },
			{ "3048", "Tanque condensado AGD" }, { "3049", "Enfriador horizontal 10000 lts" },
			{ "3050", "Tanque transporte 8000 lts" }, { "3051", "Tanque transporte 21000 lts" },
			{ "3052", "Tina quesera 8000 lts" }, { "3053", "Plataforma tina 8000 lts" }, { "3054", "ELVC682AA - 1X7" },
			{ "3055", "Silo 50000 lts" }, { "3056", "ELHC103AB - 2X6" }, { "3057", "TRCS401AA" },
			{ "3058", "TRCS401AA" }, { "3059", "TRCS401AA" }, { "3060", "TRCS401AA" },
			{ "3061", "Pasteurizador 500 l/h" }, { "3062", "Tolva" }, { "3063", "Tolva" },
			{ "3064", "Montaje IPEM 293" }, { "3065", "ELVC682AC - 1x7" }, { "3066", "ELVC682AC - 1x7" },
			{ "3067", "ELVC682AC - 1x7" }, { "3068", "ELVC682AC - 1x7" }, { "3069", "ELVC682AC - 1x7" },
			{ "3070", "Carro para cilindros ETI" }, { "3071", "Tanque elevado para agua" },
			{ "3072", "SALADERO - Canasto saladero" }, { "3073", "SALADERO - Estructura portante" },
			{ "3074", "SALADERO - Circulador salmuera" }, { "3075", "SALADERO - Transportador queso" },
			{ "3076", "SALADERO - Transportador queso" }, { "3077", "SALADERO - Tablero eléctrico" },
			{ "3078", "SALADERO - Montaje" }, { "3079", "SALADERO - Saturador salmuera" },
			{ "3080", "SALADERO - Batea pulmón" }, { "3081", "SALADERO - Filtro de malla" },
			{ "3082", "SALADERO - Bomba salmuera" }, { "3083", "SALADERO - Cañerias conexión" },
			{ "3084", "Reparación tinas ECOLAT" }, { "3085", "Prensa para fondos" },
			{ "3086", "Puente grua sección cortes" }, { "3087", "Tanque 5000 lts" }, { "3088", "Tanque 5000 lts" },
			{ "3089", "Tanque 5000 lts" }, { "3090", "Plataforma recibo" }, { "3091", "Tanque 20000 lts - PRFV" },
			{ "3092", "Tina 12000 lts - Lacteos del S" }, { "3093", "Tanque para combustible" },
			{ "3094", "Termorecuperador 150 lts" }, { "3095", "Termorecuperador 150 lts" },
			{ "3096", "Termorecuperador 150 lts" }, { "3097", "Termorecuperador 150 lts" },
			{ "3098", "Termorecuperador 150 lts" }, { "3099", "Termorecuperador 150 lts" },
			{ "3100", "Termorecuperador 150 lts" }, { "3101", "Termorecuperador 150 lts" },
			{ "3102", "Termorecuperador 150 lts" }, { "3103", "Termorecuperador 150 lts" },
			{ "3104", "Termorecuperador 250 lts" }, { "3105", "Termorecuperador 250 lts" },
			{ "3106", "Termorecuperador 250 lts" }, { "3107", "Termorecuperador 250 lts" },
			{ "3108", "Termorecuperador 250 lts" }, { "3109", "Termorecuperador 250 lts" },
			{ "3110", "Termorecuperador 250 lts" }, { "3111", "Termorecuperador 250 lts" },
			{ "3112", "Termorecuperador 250 lts" }, { "3113", "Termorecuperador 250 lts" },
			{ "3114", "Termorecuperador 400 lts" }, { "3115", "Termorecuperador 400 lts" },
			{ "3116", "Termorecuperador 400 lts" }, { "3117", "Termorecuperador 400 lts" },
			{ "3118", "Sistema eléctrico silo" }, { "3119", "Enfriador 10000 2x5" },
			{ "3120", "Tanque para químico 200 lts" }, { "3121", "Tanque para químico 500 lts" },
			{ "3122", "Tanque para químico 500 lts" }, { "3123", "Dispersor con lanzas" },
			{ "3124", "Enfriador Muller usado" }, { "3125", "Enfriador ETI usado 6800 lts" },
			{ "3126", "Mezclador doble Z invertida" }, { "3127", "Tanque proceso 1000 lts" },
			{ "3128", "Enfriador usado 4650" }, { "3129", "Enfriador usado 4000" }, { "3130", "Enfriador usado 4000" },
			{ "3131", "Enfriador usado 1950" }, { "3132", "Enfriador usado 4000 Bauducco" },
			{ "3133", "Dispositivo grinfado ETI" }, { "3134", "Balanza sector corte y plegado" },
			{ "3135", "Ampliación nave 5 ETI" }, { "3136", "Tina quesera 3000 lts" },
			{ "3137", "Plataforma tina 3000 lts" }, { "3138", "Mesa desueradora" }, { "3140", "Caballetes ETI" },
			{ "3141", "Enfriador usado 7000 lts horiz" }, { "3142", "Enfriador usado 4000 lts horiz" },
			{ "3143", "Enfriador horizontal 10000 lts" }, { "3144", "Enfriador horizontal 10000 lts" },
			{ "3145", "Mesa soldado de bandejas" }, { "3146", "Enfriador 12000 lts - 2x6" },
			{ "3147", "Enfriador 12000 lts - 2x6" }, { "3148", "Enfriador 8000 lts - 2x5" },
			{ "3149", "Silo 30 m³ - Glucosa" }, { "3150", "Silo 30 m³ - Suero" }, { "3151", "Silo 30 m³ - Suero" },
			{ "3152", "Tanque para chasis 8000 lts" }, { "3153", "Sistema medición silos (2)" },
			{ "3154", "Intercambiador calor 2000 l/h" }, { "3155", "Silo 100 m³ - Fondo plano" },
			{ "3156", "Silo 50 m³ - Fondo plano" }, { "3157", "Silo 50 m³ - Fondo plano" },
			{ "3158", "Tanque proceso 1200 lts" }, { "3159", "Tanque transp. acoplado 21000" },
			{ "3160", "Tanque transporte chasis 4000" }, { "3161", "Tanque transporte chasis 4000" },
			{ "3162", "Tanque transporte chasis 4000" }, { "3163", "Tina quesera 6000 lts" },
			{ "3164", "Plataforma tina 6000 lts" }, { "3165", "Enfriador usado 5500 lts" },
			{ "3166", "Desaireador 40000 l/h" }, { "3167", "Desaireador 40000 l/h" },
			{ "3168", "Enfriador usado 5500 lts" }, { "3169", "Tanque para crema 1000 lts" },
			{ "3170", "Pulmón de cuajada" }, { "3171", "Pulmón de suero" }, { "3172", "Batea lavado de columnas" },
			{ "3173", "Estación volteo moldes-ponetap" }, { "3174", "Prensa horizontal 160 pos." },
			{ "3175", "Estación vaciado de moldes" }, { "3176", "Lavadora de moldes microperfor" },
			{ "3177", "Torre de moldeo" }, { "3178", "Trozadora automática" }, { "3179", "Tanque crema 8000 lts" },
			{ "3180", "Reparación drenoprensa Chile" }, { "3181", "Reparación drenoprensa Chile" },
			{ "3182", "Plataforma tina 3000 lts" }, { "3183", "Soporte silos" }, { "3184", "Paila dulce 1000 lts" },
			{ "3185", "Plataforma paila" }, { "3186", "Tina quesera 3500 lts" }, { "3187", "Tina quesera 3500 lts" },
			{ "3188", "Plataforma tina 3500 lts" }, { "3189", "Tina 3000 lts" }, { "3190", "Montaje Punta del Agua" },
			{ "3191", "Tanque para chasis 8000 lts" }, { "3192", "Tina quesera 6500 lts" }, { "3193", "Caballetes" },
			{ "3194", "Tanque proceso 800 lts" }, { "3195", "Tolva 10m³" }, { "3196", "Tanque proceso 15000 lts" },
			{ "3197", "Tanque proceso 15000 lts" }, { "3198", "Central CIP 18000 lts" },
			{ "3199", "Tina quesera 3500 lts" }, { "3200", "Tina quesera 3500 lts" },
			{ "3201", "Plataforma tina 3500 lts" }, { "3202", "Mesa desueradora 3500 lts" },
			{ "3203", "Tina quesera 6500 lts" }, { "3204", "Tina quesera 6500 lts" },
			{ "3205", "Plataforma tinas 6500 lts" }, { "3206", "Mesa desueradora 3000lts - c/P" },
			{ "3207", "Mesa desueradora 3000lts - s/P" }, { "3208", "Central CIP integrada 4500 lts" },
			{ "3209", "Lavadora de bandejas" }, { "3210", "Tunel termocontracción" }, { "3211", "Matriz repusaje" },
			{ "3212", "Mezclador sóilido-líquido" }, { "3213", "Cinta moldes vacios 1" },
			{ "3214", "Cinta moldes llenos 1" }, { "3215", "Cinta gatillo entrada prensa" },
			{ "3216", "Cinta salida prensas" }, { "3217", "Cinta desmoldeo" }, { "3218", "Cinta tapas a lavadora" },
			{ "3219", "Cinta quesos a trozadora" }, { "3220", "Cinta moldes a lavadora" },
			{ "3221", "Tanque CIP 5000 lts" }, { "3222", "Tanque proceso - maduración" },
			{ "3223", "Desmigador usado" }, { "3224", "Tanque usado 4000 lts" }, { "3225", "Tanque usado 4000 lts" },
			{ "3226", "Tanque usado 6000 lts" }, { "3227", "Lavado automático enfriador" },
			{ "3228", "Lavado automático enfriador" }, { "3229", "Lavado automático enfriador" },
			{ "3230", "Lavado automático enfriador" }, { "3231", "Enfriador 10000 lts 2x6" },
			{ "3232", "Enfriador 8000 - 2x5" }, { "3233", "Enfriador 12000 - 2x6" }, { "3234", "Enfriador 6800 - 1x7" },
			{ "3235", "Enfriador 6800 - 1x7" }, { "3236", "Acoplado 21000 lts" },
			{ "3239", "Pasteurizador 5000 l/h - suero" }, { "3240", "Enfriador 12000 lts - 2x6" },
			{ "3241", "Enfriador 5600 lts - usado" }, { "3242", "Silo 100 m³" }, { "3243", "Silo 100 m³" },
			{ "3244", "Silo 100 m³" }, { "3245", "Silo 100 m³" }, { "3246", "Silo 150 m³" }, { "3247", "Silo 150 m³" },
			{ "3249", "Enfriador 15000 lts - 3x7 HP" }, { "3250", "Accesorio corte espuma poliret" },
			{ "3251", "Banco de pruebas de caudal" }, { "3252", "Cinta retorno lateral a gatill" },
			{ "3253", "Cinta gatillo molde lleno 2" }, { "3254", "Cinta tapa limpia" },
			{ "3255", "Volteador molde limpio esqu" }, { "3256", "Volteador molde lleno lateral" },
			{ "3257", "5 Tab. Fuerza para enfriadores" }, { "3258", "8 Tab. Fuerza enfriadores 2UF" },
			{ "3259", "Silo 20000 lts" }, { "3260", "Silo 20000 lts" }, { "3261", "Silo 25000 lts" },
			{ "3262", "Silo 25000 lts" }, { "3263", "Silo 40000 lts" }, { "3264", "Canasto saladero" },
			{ "3266", "Silo 80000 lts" }, { "3268", "Silo 60000 litros" }, { "3269", "Tina quesera 6000 lts" },
			{ "3270", "Silo 80000 lts" }, { "3271", "Soporte transporte silo" }, { "3272", "Silo 80000 lts" },
			{ "3273", "Silo 40000 litros" }, { "3274", "Silo 60000 litros" }, { "3275", "Enfriador 10000 lts 2x6" } };

	String[][] tabla_tareas;

}
