/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bajodelresca.pruebaatmitaback;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author espin
 */
public class App {
    public static final String POSTS_API_URL = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2020-09-09&end_date=2020-09-16&api_key=zdUP8ElJv1cehFM0rsZVSQN7uBVxlDnu4diHlLSb";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Converter con=new Converter();
        System.out.println(con.fromJsonString(response.body()));
    }
}
