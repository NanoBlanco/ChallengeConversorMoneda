package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ApiCliente {
	
	private static final String API_KEY = "75d180f0c381fac720b2ca7c";
	private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
	
	private static final HttpClient httpClient = HttpClient.newHttpClient();

	public static double obtenerTasa(String base, String destino) throws Exception {
		
		String urlStr = URL_BASE + API_KEY + "/pair/" + base + "/" + destino;
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(urlStr))
				.GET()
				.build();
		
		HttpResponse<String> response = httpClient
				.send(request, HttpResponse.BodyHandlers.ofString());
		
		if(response.statusCode() != 200) {
			throw new RuntimeException("Error al obtener los datos: código "+response.statusCode());
		}
		
		JSONObject json = new JSONObject(response.body());
		
		if (!json.getString("result").equals("success")) {
            throw new RuntimeException("Error en la respuesta de la API: " + json.toString());
        }
		
		return json.getDouble("conversion_rate");
	}
}
