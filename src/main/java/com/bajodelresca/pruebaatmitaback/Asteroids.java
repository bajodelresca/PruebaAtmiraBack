package com.bajodelresca.pruebaatmitaback;
import com.fasterxml.jackson.annotation.*;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asteroids {
    private String greeting;
    private List<Instruction> instructions;

    @JsonProperty("greeting")
    public String getGreeting() { return greeting; }
    @JsonProperty("greeting")
    public void setGreeting(String value) { this.greeting = value; }

    @JsonProperty("instructions")
    public List<Instruction> getInstructions() { return instructions; }
    @JsonProperty("instructions")
    public void setInstructions(List<Instruction> value) { this.instructions = value; }
}
