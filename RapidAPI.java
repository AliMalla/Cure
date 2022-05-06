package recipesearch;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RapidAPI {
    static String hostURLString = "https://edamam-recipe-search.p.rapidapi.com/search?q=chicken";
    String charset = "UTF-8";

    String apiURL = "";
    String endURL = "";
    String param1 = "";
    String value1 = "";
    String param2 = "";
    String value2 = "";
    String param3 = "";
    String value3 = "";

    static URL callURL;
    static HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();


    public RapidAPI() {
        httpConnect();
    }



    public static void httpConnect(){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://edamam-food-and-grocery-database.p.rapidapi.com/parser?ingr=apple"))
                .header("X-RapidAPI-Host", "edamam-food-and-grocery-database.p.rapidapi.com")
                .header("X-RapidAPI-Key", "11aee9949amsh8d0f9e173dc88c3p13baa8jsn9317b6fe4249")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println(response.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
    }

    public static void connect() {
        {
            try {
                callURL = new URL(hostURLString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }


        URLConnection connection = null;

        {
            try {
                connection = callURL.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String header1 = "";
            String header2 = "";
            connection.setRequestProperty("header1", header1);
            connection.setRequestProperty("header2", header2);
            //Get Response
            try {
                InputStream is = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(connection.getContentType());
        }
    }
}
