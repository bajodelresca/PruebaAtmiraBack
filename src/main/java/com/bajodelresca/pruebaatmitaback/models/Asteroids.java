package com.bajodelresca.pruebaatmitaback.models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.Map;

public class Asteroids {
    private AsteroidsLinks links;
    private long elementCount;
    private Map<String, List<NearEarthObject>> nearEarthObjects;

    @JsonProperty("links")
    public AsteroidsLinks getLinks() { return links; }
    @JsonProperty("links")
    public void setLinks(AsteroidsLinks value) { this.links = value; }

    @JsonProperty("element_count")
    public long getElementCount() { return elementCount; }
    @JsonProperty("element_count")
    public void setElementCount(long value) { this.elementCount = value; }

    @JsonProperty("near_earth_objects")
    public Map<String, List<NearEarthObject>> getNearEarthObjects() { return nearEarthObjects; }
    @JsonProperty("near_earth_objects")
    public void setNearEarthObjects(Map<String, List<NearEarthObject>> value) { this.nearEarthObjects = value; }
}
