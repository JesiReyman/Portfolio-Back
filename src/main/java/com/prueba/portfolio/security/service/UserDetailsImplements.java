
package com.prueba.portfolio.security.service;

import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class UserDetailsImplements implements UserDetailsService {

    @Autowired
    UsuarioLoginService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        UsuarioLogin usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();

        return UsuarioPrincipal.build(usuario);
    }
}
