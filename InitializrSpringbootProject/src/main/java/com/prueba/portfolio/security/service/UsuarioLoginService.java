
package com.prueba.portfolio.security.service;

import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.repository.UsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioLoginService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Optional<UsuarioLogin> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    
    public void save(UsuarioLogin usuario){
        usuarioRepository.save(usuario);
    }
}
