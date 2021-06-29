/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bajodelresca.pruebaatmitaback.controllers;

import com.bajodelresca.pruebaatmitaback.Exceptions.RecordNotFoundException;
import com.bajodelresca.pruebaatmitaback.models.Asteroide;
import com.bajodelresca.pruebaatmitaback.services.AsteroidsService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author espin
 */
@RestController
@RequestMapping("/asteroids")
public class AsteroidsController {
    @Autowired
	private AsteroidsService asteroidsService;
	@GetMapping()
	public ResponseEntity<List<Asteroide>> getAsteroides(@RequestParam(name = "planeta", defaultValue ="Earth") String planeta) throws IOException, InterruptedException{
            System.out.println(planeta);
		if(planeta.equals("")){
			throw new RecordNotFoundException("El campo Planeta no puede estar vacío");
		}
	
		List<Asteroide> asteroidList=new ArrayList();
		
		asteroidList.addAll(this.asteroidsService.getAsteroides(planeta));
		
		return new ResponseEntity<List<Asteroide>>(asteroidList,new HttpHeaders(),HttpStatus.OK);
	}

}
