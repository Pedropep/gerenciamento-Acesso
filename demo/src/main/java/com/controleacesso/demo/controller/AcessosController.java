package com.controleacesso.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controleacesso.demo.models.Acessos;
import com.controleacesso.demo.models.UserDTO;
import com.controleacesso.demo.repository.UsuarioProjection;
import com.controleacesso.demo.service.AcessosService;

@RestController
@RequestMapping("/api/acesso")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AcessosController {
    
    @Autowired
    private AcessosService service;

    @GetMapping
    public ResponseEntity<List<Acessos>> buscarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTudo());
    }

    @GetMapping("/user/{id}")  
	public ResponseEntity<List<Acessos>> findByUser(@PathVariable Long id){    	
		return service.acessosPorUsuarios(id).isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
			ResponseEntity.status(HttpStatus.OK).body(service.acessosPorUsuarios(id));
	}

    @PostMapping
	public ResponseEntity<Acessos> post (@Validated @RequestBody Acessos acessos){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(acessos));
	}

    @PutMapping("/")
	public ResponseEntity<Acessos> put (@RequestBody Acessos acessos){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(acessos));
	}

}
