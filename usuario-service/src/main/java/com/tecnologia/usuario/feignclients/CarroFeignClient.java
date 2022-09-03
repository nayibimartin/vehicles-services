package com.tecnologia.usuario.feignclients;

import com.tecnologia.usuario.models.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "carro-service", path = "/carros")
public interface CarroFeignClient {

	@PostMapping
	Carro save(@RequestBody Carro carro);

	@GetMapping(value = "/usuarios/{idUsuario}")
	List<Carro> getCarros(@PathVariable("idUsuario") Long idUsuario);
}
