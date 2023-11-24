package com.controleacesso.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controleacesso.demo.models.Acessos;
import com.controleacesso.demo.models.Usuarios;
import com.controleacesso.demo.repository.AcessosRepository;
import com.controleacesso.demo.repository.UsuariosRepository;

@Service
public class AcessosService {

    @Autowired
    private AcessosRepository repositoryAcessos;
    @Autowired
    private UsuariosRepository repositoryUsuarios;

    public List<Acessos> buscarTudo(){
        return repositoryAcessos.findAll();
    }

    public Acessos save(Acessos acesso) {
		return repositoryAcessos.save(acesso);
	}

    public List<Acessos> acessosPorUsuarios(Long id){
       Optional<Usuarios> a = repositoryUsuarios.findById(id);

        if (a.isPresent()) {
            return repositoryAcessos.findByUsuarios(a.get());
        }else{
            List<Acessos> listEmpty = new ArrayList<>();
            return listEmpty;
            
        }
    }
}
