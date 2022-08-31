package com.tecnologia.carro.service;

import com.tecnologia.carro.entity.Carro;
import com.tecnologia.carro.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
	@Autowired
	private CarroRepository carroRepository;

	public List<Carro> getAll(){
		return carroRepository.findAll();
	}

	public Carro getById(Long id){
		Carro carro=carroRepository.findById(id).orElse(null);

		return carro;
	}

	public Carro update(Carro carro){
		Carro update=getById(carro.getId());
		if(update==null){
			return null;
		}else{
			update.setMarca(carro.getMarca());
			update.setModelo(carro.getModelo());
			return carroRepository.save(update);
		}
	}

	public Carro add(Carro carro){
		return carroRepository.save(carro);
	}

	public boolean delete(Long id){
		Carro delete=getById(id);
		if(delete == null) {
			return false;
		}else{
			carroRepository.delete(delete);
			return true;
		}
	}

	public List<Carro> getByUsuarioId(Long usuarioId){
		return carroRepository.getByUsuarioId(usuarioId);
	}
}
