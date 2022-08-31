package com.tecnologia.carro.repository;

import com.tecnologia.carro.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {
	List<Carro> getByUsuarioId(Long usuarioId);
}
