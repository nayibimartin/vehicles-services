package com.tecnologia.moto.service;

import com.tecnologia.moto.entity.Moto;
import com.tecnologia.moto.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

	@Autowired
	private MotoRepository motoRepository;

	public List<Moto> getAll(){
		return motoRepository.findAll();
	}

	public Moto getById(Long id){
		Moto moto=motoRepository.findById(id).orElse(null);

		return moto;
	}

	public Moto update(Moto carro){
		Moto update=getById(carro.getId());
		if(update==null){
			return null;
		}else{
			update.setMarca(carro.getMarca());
			update.setModelo(carro.getModelo());
			return motoRepository.save(update);
		}
	}

	public Moto add(Moto carro){
		return motoRepository.save(carro);
	}

	public boolean delete(Long id){
		Moto delete=getById(id);
		if(delete == null) {
			return false;
		}else{
			motoRepository.delete(delete);
			return true;
		}
	}

	public List<Moto> getByUsuarioId(Long usuarioId){
		return motoRepository.getByUsuarioId(usuarioId);
	}
}
