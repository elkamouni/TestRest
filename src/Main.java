import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException, UnirestException {
        System.out.println("Hello World!");
        //Get method using HttpURLConnection library
        URL obj = new URL("https://staging.cloud-elements.com/elements/api-v2/contacts");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept","application/json");
        con.setRequestProperty("Authorization","User HEcDKNeZ0PCsSaTA+vd+AKPWEZdJGWvXpLD6lAyxRx4=, Organization 8e992359d67431dac53f402dd90ef22e, Element d9COOZJ7bK+AxHMZQKjjNQmgH+pYPvYTHutdJaIeW/Q=");
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);

        String output = con.getInputStream().toString();
        System.out.println(output);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response);

        //Get method using Unirest library

        HttpResponse<JsonNode> unirestresponse =  Unirest.get("http://localhost:8080/Orders")
                .queryString("OrderId", "123456")
                .asJson();

        System.out.println(unirestresponse.getBody());

        //Post method using Unirest library
        JSONObject json = new JSONObject();
        json.put("id","1357");
        json.put("description","aaaaa");
        HttpResponse<String> unirestresponse1 =Unirest.post("http://localhost:8080/Orders")
                .header("Content-Type","application/json")
                .body(json)
                .asString();
        System.out.println(unirestresponse1.getBody());

        //Get method using Unirest library

        HttpResponse<JsonNode> response1 = Unirest.get("https://staging.cloud-elements.com/elements/api-v2/contacts")
                .header("Accept","application/json")
                .header("Authorization","User HEcDKNeZ0PCsSaTA+vd+AKPWEZdJGWvXpLD6lAyxRx4=, Organization 8e992359d67431dac53f402dd90ef22e, Element d9COOZJ7bK+AxHMZQKjjNQmgH+pYPvYTHutdJaIeW/Q=")
                .asJson();
        System.out.println(response1.getStatus());
        System.out.println(response1.getBody());

    }

}
