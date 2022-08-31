package com.tecnologia.carro.controller;

import com.tecnologia.carro.entity.Carro;
import com.tecnologia.carro.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/carros")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public ResponseEntity<List<Carro>> getAll(){
		List<Carro> carros=carroService.getAll();
		if(carros.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Carro> getById(@PathVariable("id") Long id){
		Carro carro=carroService.getById(id);
		if(carro==null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carro);
	}

	@PostMapping
	public ResponseEntity<Carro> add(@RequestBody Carro carro){
		Carro created=carroService.add(carro);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping(value = "/usuarios/{idUsuario}")
	public ResponseEntity<List<Carro>> getByIdUsuario(@PathVariable("idUsuario") Long idUsuario){
		List<Carro> carros=carroService.getByUsuarioId(idUsuario);
		if(carros.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

}
