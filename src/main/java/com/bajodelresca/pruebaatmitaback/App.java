/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bajodelresca.pruebaatmitaback;

import com.bajodelresca.pruebaatmitaback.models.Asteroide;
import com.bajodelresca.pruebaatmitaback.models.Converter;
import com.bajodelresca.pruebaatmitaback.models.Asteroids;
import com.bajodelresca.pruebaatmitaback.models.NearEarthObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        System.out.println(response.body());
        Asteroids asteroids = Converter.fromJsonString(response.body());
        List<Asteroids> asteroidsList = Collections.singletonList(asteroids);
        Map<String, List<NearEarthObject>> nearEarthObjects = asteroidsList.get(0).getNearEarthObjects();

        Collection<List<NearEarthObject>> list = nearEarthObjects.values();
        List<NearEarthObject> nearEarthObjectList = new ArrayList<>();
        list.forEach(nearEarthObjectList::addAll);

        //Filter true
        List<NearEarthObject> nearEarthObjectListWhenDangerous = nearEarthObjectList.stream().filter(NearEarthObject::getIsPotentiallyHazardousAsteroid).collect(Collectors.toList());
        //Sorting por Magnitud
         nearEarthObjectListWhenDangerous.sort(Comparator.comparing(NearEarthObject::getAbsoluteMagnitudeH).reversed());
        System.out.println("array");
        System.out.println(nearEarthObjectListWhenDangerous);
        //Recoger los 3 asteroides priemeros en la lista
        List<NearEarthObject> threeDangerous= new ArrayList<>();
        threeDangerous.add(nearEarthObjectListWhenDangerous.get(0));
        threeDangerous.add(nearEarthObjectListWhenDangerous.get(1));
        threeDangerous.add(nearEarthObjectListWhenDangerous.get(2));
        
        List<Asteroide> finalObject= new ArrayList<>();
        
        for (NearEarthObject element: threeDangerous){
            Asteroide asteroide=new Asteroide(element.getName(),(element.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax()+element.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin()),Double.parseDouble(element.getCloseApproachData().get(0).getRelativeVelocity().getKilometersPerHour()),element.getCloseApproachData().get(0).getCloseApproachDate().toString(),element.getCloseApproachData().get(0).getOrbitingBody().toString());
            finalObject.add(asteroide);
        }
        System.out.println("JSON->>>>>>>>>>>>>>>>>");
        System.out.println(finalObject);
    }
}
