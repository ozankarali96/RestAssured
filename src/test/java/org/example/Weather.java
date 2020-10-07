package org.example;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.SSLConfig;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import groovy.json.JsonOutput;
import org.junit.Assert;
import org.junit.Test;


public class Weather {
    @Test
    public void testMGMApiWeather() {

        //RestAssured.baseURI = "https://servis.mgm.gov.tr/";
        RestAssured.baseURI = "https://servis.mgm.gov.tr/";

        RequestSpecification request = RestAssured

                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));

        Response response = request

                .when()
                .get("api/tahminler/gunluk?istno=90601")

                .then()
                .statusCode(200)
                .extract().response();

        int responseCode = response.getStatusCode();
        ResponseBody responseBody = response.getBody();

        String tarihGun1 = response.jsonPath().getString("tarihGun1");
        String enYuksekGun1 = response.jsonPath().getString("enYuksekGun1");
        String enDusukGun1 = response.jsonPath().getString("enDusukGun1");

        System.out.println(responseCode);
        System.out.println(responseBody.asString());

        System.out.println("---------------------");
        System.out.println(tarihGun1);
        System.out.println(enYuksekGun1);
        System.out.println(enDusukGun1);

        assert responseBody.asString() != null;

    }


    @Test
    public void GetCall(){
        RequestSpecification request = RestAssured.given();
        request.baseUri("https://reqres.in");

        Response response = request.get("/api/users");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getBody().prettyPrint());
        System.out.println(response.getHeaders());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine().toString(),"HTTP/1.1 200 OK");
    }
}
