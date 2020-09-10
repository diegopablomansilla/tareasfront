package com.ms.front.commons.services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;

import com.ms.front.EnvVars;
import com.ms.front.commons.model.ByIdArgs;
import com.ms.front.commons.model.IdDesc;
import com.ms.front.commons.model.IdDescArgs;
import com.ms.front.commons.model.NoPagin;
import com.ms.front.commons.model.NoPaginArgs;
import com.ms.front.commons.model.ObjectModel;
import com.ms.front.commons.model.Pagin;
import com.ms.front.commons.model.PaginArgs;
import com.ms.front.commons.model.PostArgs;
import com.ms.front.commons.model.ServiceArgs;
//import com.ms.front.view.JavaFXUtil;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class Service {

	public static final String TYPE_RPC = "RPC";

	public static String LOGIN(String urlString, PostArgs args, ServiceArgs arg)
			throws IOException, URISyntaxException {

//		args.setDb("eti");

		Map<String, String> headers = new HashMap<String, String>();

		if (args.getDb() != null) {
			headers.put(args.KEY_DB, args.getDb());
		}

		Map<String, String> queryParams = new HashMap<String, String>();

//		if (args != null) {
//			queryParams.putAll(args.toMap());
//		}

		ResponseJsonObject r = POST(TYPE_RPC, urlString, headers, queryParams, arg);

		if (r.getStatus() == 500) {

			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en el servidor.",
					3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

		} else if (r.getStatus() == 201) {
			return r.getPayload();
		} else if (r.getStatus() == 204) {
			return null;
		}

		throw new IllegalStateException("Illegal state response, code " + r.getStatus());
	}

	public static String GET(String urlString) throws IOException, URISyntaxException {

		Map<String, String> headers = new HashMap<String, String>();

		Map<String, String> queryParams = new HashMap<String, String>();

		ResponseJsonObject r = GET(TYPE_RPC, urlString, headers, queryParams);

		if (r.getStatus() == 500) {

			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en el servidor.",
					3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

		} else if (r.getStatus() == 204) {
			return null;
		} else if (r.getStatus() == 200) {
			return r.getPayload();
		}

		throw new IllegalStateException("Illegal state response, code " + r.getStatus());
	}

	public static String GETById(String urlString, ByIdArgs byIdArgs, ServiceArgs args)
			throws IOException, URISyntaxException {

		byIdArgs.setDb("eti");

		Map<String, String> headers = new HashMap<String, String>();

		if (byIdArgs.getDb() != null) {
			headers.put(byIdArgs.KEY_DB, byIdArgs.getDb());
		}

		Map<String, String> queryParams = new HashMap<String, String>();

		if (args != null) {
			queryParams.putAll(args.toMap());
		}

		ResponseJsonObject r = GET(TYPE_RPC, urlString, headers, queryParams);

		if (r.getStatus() == 500) {

			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en el servidor.",
					3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

		} else if (r.getStatus() == 204) {
			return null;
		} else if (r.getStatus() == 200) {
			return r.getPayload();
		}

		throw new IllegalStateException("Illegal state response, code " + r.getStatus());
	}

	public static boolean POST(String urlString, PostArgs args) throws IOException, URISyntaxException {
		return POST(urlString, args, null);
	}

	public static boolean POST(String urlString, PostArgs args, ServiceArgs arg)
			throws IOException, URISyntaxException {

//		args.setDb("eti");

		Map<String, String> headers = new HashMap<String, String>();

		if (args.getDb() != null) {
			headers.put(args.KEY_DB, args.getDb());
		}

		Map<String, String> queryParams = new HashMap<String, String>();

//		if (args != null) {
//			queryParams.putAll(args.toMap());
//		}

		ResponseJsonObject r = POST(TYPE_RPC, urlString, headers, queryParams, arg);

		if (r.getStatus() == 500) {

			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en el servidor.",
					3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

		} else if (r.getStatus() == 201) {
			return true;
		}

		throw new IllegalStateException("Illegal state response, code " + r.getStatus());
	}

	public static NoPagin GETNoPagin(String urlString, NoPaginArgs noPaginArgs) throws IOException, URISyntaxException {
		return GETNoPagin(urlString, noPaginArgs, null);
	}

	public static NoPagin GETNoPagin(String urlString, NoPaginArgs noPaginArgs, ServiceArgs args)
			throws IOException, URISyntaxException {

		noPaginArgs.setDb("eti");

		Map<String, String> headers = new HashMap<String, String>();

		if (noPaginArgs.getDb() != null) {
			headers.put(noPaginArgs.KEY_DB, noPaginArgs.getDb());
		}

		Map<String, String> queryParams = new HashMap<String, String>();

		if (args != null) {
			queryParams.putAll(args.toMap());
		}

		ResponseJsonObject r = GET(TYPE_RPC, urlString, headers, queryParams);

		if (r.getStatus() == 500) {

			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en el servidor.",
					3000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

		} else if (r.getStatus() == 204) {
			return NoPagin.fromJson(r.getPayload());
		} else if (r.getStatus() == 200) {
			return NoPagin.fromJson(r.getPayload());
		}

		throw new IllegalStateException("Illegal state response, code " + r.getStatus());
	}

	public static IdDesc GETIdDesc(String urlString, IdDescArgs idDescArgs) throws IOException, URISyntaxException {
		return GETIdDesc(urlString, idDescArgs, null);
	}

	public static IdDesc GETIdDesc(String urlString, IdDescArgs idDescArgs, ServiceArgs args)
			throws IOException, URISyntaxException {

//		idDescArgs.setDb("eti");

		Map<String, String> headers = new HashMap<String, String>();

		if (idDescArgs.getDb() != null) {
			headers.put(idDescArgs.KEY_DB, idDescArgs.getDb());
		}

		Map<String, String> queryParams = new HashMap<String, String>();

		if (idDescArgs.getText() != null) {
			queryParams.put(idDescArgs.KEY_TEXT, idDescArgs.getText());
		}

		if (args != null) {
			queryParams.putAll(args.toMap());
		}

		ResponseJsonObject r = GET(TYPE_RPC, urlString, headers, queryParams);

		if (r.getStatus() == 500) {

			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en el servidor.",
					1000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

		} else if (r.getStatus() == 204) {

			return IdDesc.fromJson(r.getPayload());
		} else if (r.getStatus() == 200) {
			return IdDesc.fromJson(r.getPayload());
		}

		throw new IllegalStateException("Illegal state response, code " + r.getStatus());
	}

	public static Pagin GETPagin(String urlString, PaginArgs paginArgs) throws IOException, URISyntaxException {
		return GETPagin(urlString, paginArgs, null);
	}

	public static Pagin GETPagin(String urlString, PaginArgs paginArgs, ServiceArgs args)
			throws IOException, URISyntaxException {

//		paginArgs.setDb("eti");

		Map<String, String> headers = new HashMap<String, String>();

		if (paginArgs.getDb() != null) {
			headers.put(paginArgs.KEY_DB, paginArgs.getDb());
		}

		Map<String, String> queryParams = new HashMap<String, String>();

		if (paginArgs.getPageRequest() != null) {
			queryParams.put(paginArgs.KEY_PAGE_REQUEST, paginArgs.getPageRequest());
		}

		if (paginArgs.getLastIndexOld() != null) {
			queryParams.put(paginArgs.KEY_LAST_INDEX_OLD, paginArgs.getLastIndexOld().toString());
		}

		if (args != null) {
			queryParams.putAll(args.toMap());
		}

		ResponseJsonObject r = GET(TYPE_RPC, urlString, headers, queryParams);

		if (r.getStatus() == 500) {

			Notification notification = new Notification("Lo sentimos, ha ocurrido un error interno en el servidor.",
					1000, Position.BOTTOM_END);
			notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notification.open();

		} else if (r.getStatus() == 204) {

			return Pagin.fromJson(r.getPayload());
		} else if (r.getStatus() == 200) {
			return Pagin.fromJson(r.getPayload());
		}

		throw new IllegalStateException("Illegal state response, code " + r.getStatus());
	}

	private static ResponseJsonObject POST(String type, String urlString, Map<String, String> headers,
			Map<String, String> queryParams, ObjectModel body) throws IOException, URISyntaxException {

//		URI baseUri = new URI(EnvVars.getApiHome() + "/" + type + "/" + EnvVars.getApiVersion() + "/" + urlString);
		URI baseUri = new URI(EnvVars.getApiHome() + urlString);

		URI uri = applyParameters(baseUri, queryParams);

		HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
		conn.setConnectTimeout(5000);
//		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Content-Type", "application/json; charset=ISO-8859-1");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");

		headers.forEach((k, v) -> conn.setRequestProperty(k, v));
		
		OutputStream os = conn.getOutputStream();
//		os.write(body.toJson().toString().getBytes("UTF-8"));
		os.write(body.toJson().toString().getBytes());
		os.close();

//		conn.connect();
//		
//		try (OutputStream os = conn.getOutputStream()) {
//			byte[] input = body.toJson().toString().getBytes("utf-8");
//			os.write(input, 0, input.length);
//		}

		int responseCode = conn.getResponseCode();

		// ---------------------------------------------------
		StringBuffer payload = new StringBuffer();

		if (responseCode != HttpURLConnection.HTTP_INTERNAL_ERROR) {
			InputStream in = new BufferedInputStream(conn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				payload.append(line);
			}
		}

		// ---------------------------------------------------

		conn.disconnect();

//		return new ResponseJsonObject(responseCode, new String(payload.toString().getBytes(), "UTF-8"));
		return new ResponseJsonObject(responseCode, payload.toString());

	}

	private static ResponseJsonObject GET(String type, String urlString, Map<String, String> headers,
			Map<String, String> queryParams) throws IOException, URISyntaxException {

//		URI baseUri = new URI(EnvVars.getApiHome() + "/" + type + "/" + EnvVars.getApiVersion() + "/" + urlString);
		URI baseUri = new URI(EnvVars.getApiHome() + urlString);

		URI uri = applyParameters(baseUri, queryParams);

		HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
		conn.setRequestMethod("GET");

		conn.setRequestProperty("'Content-Type", "application/json; charset=UTF-8");
		headers.forEach((k, v) -> conn.setRequestProperty(k, v));

		conn.connect();
		int responseCode = conn.getResponseCode();

		// ---------------------------------------------------
		StringBuffer payload = new StringBuffer();

		if (responseCode != HttpURLConnection.HTTP_INTERNAL_ERROR) {
			InputStream in = new BufferedInputStream(conn.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				payload.append(line);
			}
		}

		// ---------------------------------------------------

		conn.disconnect();

//		return new ResponseJsonObject(responseCode, new String(payload.toString().getBytes(), "UTF-8"));
		return new ResponseJsonObject(responseCode, payload.toString());

	}

	private static URI applyParameters(URI baseUri, Map<String, String> queryParams) {

		String[] urlParameters = new String[queryParams.size() * 2];

		Set<String> keys = queryParams.keySet();

		int i = 0;
		for (String key : keys) {
			urlParameters[i] = key;
			i++;
			urlParameters[i] = queryParams.get(key);
			i++;
		}

		return applyParameters(baseUri, urlParameters);
	}

	private static URI applyParameters(URI baseUri, String... urlParameters) {

		if (urlParameters == null || urlParameters.length == 0) {
			return baseUri;
		}

		StringBuilder query = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < urlParameters.length; i += 2) {
			if (first) {
				first = false;
			} else {
				query.append("&");
			}
			try {

				if (urlParameters[i + 1] != null) {
					query.append(urlParameters[i]).append("=").append(URLEncoder.encode(urlParameters[i + 1], "UTF-8"));
				}
			} catch (UnsupportedEncodingException ex) {
				/*
				 * As URLEncoder are always correct, this exception should never be thrown.
				 */
				throw new RuntimeException(ex);
			}
		}
		try {
			return new URI(baseUri.getScheme(), baseUri.getAuthority(), baseUri.getPath(), query.toString(), null);
		} catch (URISyntaxException ex) {
			/*
			 * As baseUri and query are correct, this exception should never be thrown.
			 */
			throw new RuntimeException(ex);
		}
	}

	public static JsonArray toJsonArray(String payload) {

		return Json.createReader(new StringReader(payload)).readArray();
	}

//	public static ResponseError500Object toResponseError500Object(String payload) {
//
//		ResponseError500Object r = new ResponseError500Object();
//		
//		System.out.println(payload);
//
//		JsonObject json = Json.createReader(new StringReader(payload)).readObject();
//
//		if (json.containsKey("message") && json.isNull("message") == false) {
//			r.setMessage(json.getString("message"));
//		}
//
//		if (json.containsKey("reason") && json.isNull("reason") == false) {
//			r.setReason(json.getString("reason"));
//		}
//
//		return r;
//	}

}
