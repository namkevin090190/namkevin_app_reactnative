package Utils.API_Services;



import Common.Log;
import Utils.PropertiesFile;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Line {

    public static void pushNotifyToLine(String message) {
        PropertiesFile.setPropertiesFile("configs.properties");

        String URL_Line = PropertiesFile.getPropValue("url_line");
        String acc_token = PropertiesFile.getPropValue("accessToken_line");

        RequestSpecification request = given();
        request.baseUri(URL_Line);
        request.header(new Header("Content-type", "application/json; charset=UTF-8"));
        request.header(new Header("Authorization", "Bearer " + acc_token));

        request.queryParams("message", message);
        Response reponse = request.post("/notify");
        Log.info(reponse.prettyPrint());
    }

}
