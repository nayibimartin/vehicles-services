package com.tecnologia.usuario.feignclients;

import com.tecnologia.usuario.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003", path = "/motos")
public interface MotoFeignClient {
	@PostMapping
	Moto save(@RequestBody Moto moto);

	@GetMapping(value = "/usuarios/{idUsuario}")
	List<Moto> getMotos(@PathVariable("idUsuario") Long idUsuario);
}
