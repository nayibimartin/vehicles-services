package com.tecnologia.moto.controller;

import com.tecnologia.moto.entity.Moto;
import com.tecnologia.moto.service.MotoService;
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
@RequestMapping(value = "/motos")
public class MotoController {
	@Autowired
	private MotoService motoService;

	@GetMapping
	public ResponseEntity<List<Moto>> getAll() {
		List<Moto> motos = motoService.getAll();
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Moto> getById(@PathVariable("id") Long id) {
		Moto moto = motoService.getById(id);
		if(moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}

	@PostMapping
	public ResponseEntity<Moto> add(@RequestBody Moto moto) {
		Moto created = motoService.add(moto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping(value = "/usuarios/{idUsuario}")
	public ResponseEntity<List<Moto>> getByIdUsuario(@PathVariable("idUsuario") Long idUsuario) {
		List<Moto> motos = motoService.getByUsuarioId(idUsuario);
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}
}
