package com.controleacesso.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controleacesso.demo.models.Acessos;
import com.controleacesso.demo.models.Usuarios;

@Repository
public interface AcessosRepository extends JpaRepository<Acessos, Long>{
    public Optional<Acessos> findById(Long id);

	public List<Acessos> findByUsuarios(Usuarios usuarios);

}
