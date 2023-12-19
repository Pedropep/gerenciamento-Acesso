package com.controleacesso.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controleacesso.demo.models.UsuarioLogin;
import com.controleacesso.demo.models.Usuarios;
import com.controleacesso.demo.service.UsuariosService;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuariosController {
    
    @Autowired
    private UsuariosService service;

    @GetMapping("/")
	public ResponseEntity<List<Usuarios>> buscarTodos() {
		return service.buscarTodos().size() == 0 ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : 
			ResponseEntity.status(HttpStatus.OK).body(service.buscarTodos());
	}
    
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Optional<Usuarios>> buscarCpf(@PathVariable String cpf){
    	return service.buscarCpf(cpf).isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : 
    		ResponseEntity.status(HttpStatus.OK).body(service.buscarCpf(cpf));
    }

    @PostMapping("/cadastrar")
	public ResponseEntity<Usuarios> cadastrarUsuario (@Validated @RequestBody Usuarios user){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(user));
	}
    
    @PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody @Validated Optional<UsuarioLogin> user){
		return service.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.build());
	}

    @PutMapping
	public ResponseEntity<Usuarios> atualizaUsuario (@Validated @RequestBody Usuarios user){
		return ResponseEntity.ok(service.criarUsuario(user));
	}

    @DeleteMapping("/apaga/{id}")
	public void delete(@PathVariable long id) {
		service.deletarUsuario(id);
	}
}
