package com.controleacesso.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleacesso.demo.models.Usuarios;
import com.controleacesso.demo.repository.UsuariosRepository;

@Service
public class UsuariosService {
    @Autowired
    private UsuariosRepository repository;

    public List<Usuarios> buscarTodos(){
        return repository.findAll();
    }

    public List<Usuarios> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Usuarios criarUsuario(Usuarios usuario){
        return repository.save(usuario);
    }

    public void deletarUsuario(Long id){
        repository.deleteById(id);;
    }

    
}
