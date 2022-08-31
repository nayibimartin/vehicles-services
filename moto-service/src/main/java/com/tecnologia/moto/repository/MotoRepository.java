package com.tecnologia.moto.repository;

import com.tecnologia.moto.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto,Long> {
	List<Moto> getByUsuarioId(Long usuarioId);
}
