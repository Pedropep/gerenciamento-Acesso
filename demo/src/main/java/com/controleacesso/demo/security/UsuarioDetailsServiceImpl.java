package com.controleacesso.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.controleacesso.demo.models.Usuarios;
import com.controleacesso.demo.repository.UsuariosRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService  {
    @Autowired
	private UsuariosRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuarios> user = usuarioRepository.findByNome(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
		
		return user.map(UsuarioDetailsImpl::new).get();
	}
}
