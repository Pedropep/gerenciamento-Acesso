package com.controleacesso.demo.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.controleacesso.demo.models.UsuarioLogin;
import com.controleacesso.demo.models.Usuarios;
import com.controleacesso.demo.repository.UsuariosRepository;

@Service
public class UsuariosService {
    @Autowired
    private UsuariosRepository repository;

    public List<Usuarios> buscarTodos(){
        return repository.findAll();
    }
    
    public Optional<Usuarios> buscarCpf(String cpf){
    	return repository.findByCpf(cpf);
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

    public Usuarios cadastrarUsuario(Usuarios usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEnconder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		
		return repository.save(usuario);
	}

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuarios> usuario = repository.findByNome(user.get().getNome());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getNome() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);

				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setTipo(usuario.get().getTipo());
				user.get().setEmail(usuario.get().getEmail());
				user.get().setCpf(usuario.get().getCpf());
				
				return user;
			}
		}
		return null;

    }
}
