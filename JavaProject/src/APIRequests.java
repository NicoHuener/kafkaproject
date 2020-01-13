import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRequests {
    static void sendPOST() throws IOException, InterruptedException, ParseException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()

                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .setHeader("Authorization", "Basic YTEwNTAwZDQ1NjM2NDAyMjk3NzlhN2YzOWQyY2QwYjk6NjljMDgzZjkzMjRlNDZlMWE4YWRkNzFlOTkxNjViNjE=")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());



        System.out.println(response.body().contains("\"access_token\""));

        System.out.println(response.body());
        String StrResponse = response.body();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(StrResponse);
        System.out.println(json);
        String token = json.get("access_token").toString();
        System.out.println(token);
    }
    /*public static URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        return newUri;
    }*/
    static void sendGet() throws IOException, InterruptedException {
        String firstname = "eminem";
        String secondname = "";
        String searchname = createsearchname(firstname, secondname);
        System.out.println(searchname);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/search?q=" +searchname +"&type=artist"))
                .setHeader("Authorization", "Bearer BQDteWDQ4hRDXSxMWOoBlfJqqhgTkBQLSoPnOTe2OAimHCa8nYzhn8FSBHF9RShu-JD48lW9M_UUnyYAzl8")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();


        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

     public static String createsearchname(String firstname, String secondname) {
        if (firstname.equals(""))
        {
            return secondname;
        }
        else if (secondname.equals(""))
        {
            return firstname;
        }
         return firstname + "20%" + secondname;
     }

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
    //sendPOST();
        sendGet();
    }


}
