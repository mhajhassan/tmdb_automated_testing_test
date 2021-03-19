package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BaseResponse;
import utils.ConfigManager;

import java.util.logging.Logger;

public class BaseServiceHelper {

    public static final int STATUS_OK = 200;
    public static final String BASE_URL = ConfigManager.getInstance().getPropertyValue("BaseUrl");
    public static final String API_KEY = ConfigManager.getInstance().getPropertyValue("ApiKey");

    private static Logger LogMessage = Logger.getLogger(BaseResponse.class.getName());

    public static void logInfo(String message) {
        LogMessage.info("\r\n" + message + "\r\n");
        System.out.println(message);
    }

    public static RequestSpecification setupAuthorizedRequest() {
        return setupURLRequest(API_KEY);
    }

    public static RequestSpecification setupUnauthorizedRequest() {
        return setupURLRequest("InvalidToken");
    }

    private static RequestSpecification setupURLRequest(String key) {
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        request.param("api_key", key);
        return request;
    }

    public static Response createGetResourceStatusOK(String path, RequestSpecification request) {
        logInfo("");
        return request.given().log().all().when().get(path).then().log().ifValidationFails().statusCode(STATUS_OK).extract()
                .response();
    }

    public static Response createGetResourceStatusOK(String path, String parameterName, String parameterValue, RequestSpecification request) {
        logInfo("");
        return request.given().log().all().when().contentType(ContentType.JSON).pathParam(parameterName, parameterValue).get(path).then().log().ifValidationFails().statusCode(STATUS_OK).extract()
                .response();
    }
}
