import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

public class AuthorizationClass {

    private void authToken() throws IOException {
        URL url = new URL("https://accounts.spotify.com/api/token");
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Authorization", "Basic YTEwNTAwZDQ1NjM2NDAyMjk3NzlhN2YzOWQyY2QwYjk6NjljMDgzZjkzMjRlNDZlMWE4YWRkNzFlOTkxNjViNjE=");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("grant_type", "client_credentials");
        con.setDoOutput(true);
        con.setDoInput(true);

        DataOutputStream output = new DataOutputStream(con.getOutputStream());

        output.close();

        DataInputStream input = new DataInputStream(con.getInputStream());

        String jString = "";

        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext())
        {
            jString+=sc.nextLine();
        }

        JSONParser parse = new JSONParser();
        JSONObject jobj = (JSONObject)parse.parse(jString);
        System.out.println(input.access_token.value);
    }

    public String getAuthToken(){
        return authToken();
    }
}
