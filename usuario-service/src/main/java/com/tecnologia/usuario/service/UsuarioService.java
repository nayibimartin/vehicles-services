package com.tecnologia.usuario.service;

import com.tecnologia.usuario.config.RestTemplateConfig;
import com.tecnologia.usuario.entity.Usuario;
import com.tecnologia.usuario.feignclients.CarroFeignClient;
import com.tecnologia.usuario.feignclients.MotoFeignClient;
import com.tecnologia.usuario.models.Carro;
import com.tecnologia.usuario.models.Moto;
import com.tecnologia.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RestTemplateConfig restTemplateConfig;

	@Autowired
	private CarroFeignClient carroFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public List<Carro> getCarrosByIdUsuario(Long id) {
		List<Carro> carros = restTemplateConfig.restTemplate()
			.getForObject("http://carro-service/carros/usuarios/" + id, List.class);
		return carros;
	}

	public List<Moto> getMotosByIdUsuario(Long id) {
		List<Moto> motos = restTemplateConfig.restTemplate()
			.getForObject("http://moto-service/motos/usuarios/" + id, List.class);
		return motos;
	}

	public Carro addCarro(Long usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro add = carroFeignClient.save(carro);
		return carro;
	}

	public Moto addMoto(Long usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto add = motoFeignClient.save(moto);
		return moto;
	}

	public Map<String, Object> getUsuariosAndVehiculos(Long id) {
		Map<String, Object> vehicles = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		vehicles.put("Usuario", usuario);

		List<Carro> carros = carroFeignClient.getCarros(id);
		if(carros.isEmpty()){
			vehicles.put("Carros", "No hay carros para ese usuario");
		}else{
			vehicles.put("Carros",carros);
		}

		List<Moto> motos = motoFeignClient.getMotos(id);
		if(motos.isEmpty()){
			vehicles.put("Motos", "No hay motos para ese usuario");
		}else{
			vehicles.put("Motos",motos);
		}
		return vehicles;

	}

	public Usuario getById(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		return usuario;
	}

	public Usuario update(Usuario usuario) {
		Usuario update = getById(usuario.getId());
		if(update == null) {
			return null;
		}
		else {
			update.setName(usuario.getName());
			update.setEmail(usuario.getEmail());
			return usuarioRepository.save(update);
		}
	}

	public Usuario add(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public boolean delete(Long id) {
		Usuario delete = getById(id);
		if(delete == null) {
			return false;
		}
		else {
			usuarioRepository.delete(delete);
			return true;
		}
	}
}
