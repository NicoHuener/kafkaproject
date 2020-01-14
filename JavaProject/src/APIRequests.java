import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRequests {
    public static String APIToken;
    public static String artistid;
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



        //System.out.println(response.body().contains("\"access_token\""));

        var json = createjson(response.body());
        APIToken = json.get("access_token").toString();
        System.out.println(APIToken);
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
    static void sendGetartistID() throws IOException, InterruptedException, ParseException {
        //Request for looking up the artist ID
        String firstname = "madonna";
        String secondname = "";
        String searchname = createsearchname(firstname, secondname);
        System.out.println(searchname);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/search?q=" +searchname +"&type=artist&limit=1"))
                .setHeader("Authorization", "Bearer "+ APIToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();


        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
//---------------------not working ------------------------
        JSONObject artistjson = createjson(response.body());
        //System.out.println(artistjson);
        String artistobject = artistjson.get("total").toString();
        //JSONParser parser = new JSONParser();
        //JSONObject json = (JSONObject) parser.parse(artistobject);
       // artistid = json.get("id").toString();
        System.out.println("JSON: "+artistobject);

        //-----------------------------------------------------------
    }

    public static JSONObject createjson (String httpResponse) throws ParseException {
        //Method to parse HTTP response to a JSON object
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(httpResponse);
        System.out.println(json);
        return json;
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

    static void sendGetartisttoptracks() throws IOException, InterruptedException {
        //Request for looking up the artist ID
        String artistID = "7dGJo4pcD2V6oG8kP0tJRR";


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/artists/"+ artistID + "/top-tracks?country=DE"))
                .setHeader("Authorization", "Bearer "+ APIToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();


        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }


    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
    sendPOST();
       // sendGetartistID();
        sendGetartisttoptracks();
    }


}
