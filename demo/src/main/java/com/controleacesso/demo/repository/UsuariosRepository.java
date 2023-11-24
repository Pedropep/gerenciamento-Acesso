package com.controleacesso.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controleacesso.demo.models.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
    public List<Usuarios>findByNomeContainingIgnoreCase(String nome);

    public Optional<Usuarios> findByEmail(String email); 

}
