package arep.lab2;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;


import java.io.IOException;

public class SportsHTTPService {
    public static final String linkAPI = "https://sport-highlights-api.p.rapidapi.com/";
    public static SportsHTTPService instance;

    private SportsHTTPService(){}

    public static SportsHTTPService getInstance(){
        if(instance != null) instance = new SportsHTTPService();
        return instance;
    }

    public static String getCountries(String sport) throws IOException {
        String jsonResponse = "";
        try (AsyncHttpClient client = Dsl.asyncHttpClient()) {
            // Realizar una solicitud HTTP asíncrona
            jsonResponse = client.prepareGet(linkAPI+sport+"/countries?")
                    .setHeader("x-rapidapi-key", "da223439bdmshcc5c12f5597cb70p1ae9d6jsn68e71d872114")
                    .setHeader("x-rapidapi-host", "sport-highlights-api.p.rapidapi.com")
                    .execute()
                    .toCompletableFuture()
                    .thenApply(Response::getResponseBody) // Obtiene el cuerpo de la respuesta como JSON
                    .join(); // Bloquea hasta que la solicitud se complete

            // Retorna o utiliza el JSON de la respuesta
            System.out.println(jsonResponse.length()); // Aquí se imprime, pero puedes retornarlo o manipularlo como desees
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
