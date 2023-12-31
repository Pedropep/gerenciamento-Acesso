package com.controleacesso.demo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.controleacesso.demo.models.Usuarios;

public class UsuarioDetailsImpl implements UserDetails{
    private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;	
	
	public UsuarioDetailsImpl() {}
	
	public UsuarioDetailsImpl(Usuarios user) {
		this.username = user.getNome();
		this.password = user.getSenha();
	} 
	
	@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return null;
		}

	@Override
		public String getPassword() {
			return password;
		}

	@Override
		public String getUsername() {
			return username;
		}

	@Override
		public boolean isAccountNonExpired() {
			return true;
		}

	@Override
		public boolean isAccountNonLocked() {
			return true;
		}

	@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

	@Override
		public boolean isEnabled() {
			return true;
		}

}
