package com.tecnologia.usuario.controller;

import com.tecnologia.usuario.config.RestTemplateConfig;
import com.tecnologia.usuario.entity.Usuario;
import com.tecnologia.usuario.models.Carro;
import com.tecnologia.usuario.models.Moto;
import com.tecnologia.usuario.service.UsuarioService;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> usuarios=usuarioService.getAll();
		if(usuarios.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable("id") Long id){
		Usuario usuario=usuarioService.getById(id);
		if(usuario==null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
		Usuario created=usuarioService.add(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping(value = "/{id}/carros")
	public ResponseEntity<List<Carro>> getCarrosByIdUsuario(@PathVariable("id") Long id){
		Usuario usuario=usuarioService.getById(id);
		if(usuario==null){
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros=usuarioService.getCarrosByIdUsuario(id);
		if(carros.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}

	@GetMapping(value = "/{id}/motos")
	public ResponseEntity<List<Moto>> getMotosByIdUsuario(@PathVariable("id") Long id){
		Usuario usuario=usuarioService.getById(id);
		if(usuario==null){
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos=usuarioService.getMotosByIdUsuario(id);
		if(motos.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}

	@PostMapping(value = "/{id}/carros")
	public ResponseEntity<Carro> addCarroByUsuarioId(@PathVariable("id") Long id, @RequestBody Carro carro){
		Usuario usuario=usuarioService.getById(id);
		if(usuario==null){
			return ResponseEntity.notFound().build();
		}
		Carro addCarro=usuarioService.addCarro(id,carro);

		return ResponseEntity.status(HttpStatus.CREATED).body(addCarro);
	}

	@PostMapping(value = "/{id}/motos")
	public ResponseEntity<Moto> addMotoByUsuarioId(@PathVariable("id") Long id, @RequestBody Moto moto){
		Usuario usuario=usuarioService.getById(id);
		if(usuario==null){
			return ResponseEntity.notFound().build();
		}
		Moto add=usuarioService.addMoto(id,moto);

		return ResponseEntity.status(HttpStatus.CREATED).body(add);
	}

	@GetMapping(value = "/{id}/vehicles")
	public ResponseEntity<Map<String, Object>> getVehicles(@PathVariable("id") Long id){
		Usuario usuario=usuarioService.getById(id);
		if(usuario==null){
			return ResponseEntity.notFound().build();
		}
		Map<String,Object> vehicles=usuarioService.getUsuariosAndVehiculos(id);

		return ResponseEntity.ok(vehicles);
	}
}
