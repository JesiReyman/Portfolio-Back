
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Usuario;
import com.prueba.portfolio.repository.UsuarioRepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;
    
    @Autowired
    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
    
    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> getAllUsuario() {
        return usuarioRepo.findAll();
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    public Usuario editUsuario(Long id, Usuario usuario) {
        Usuario usuarioAEditar = usuarioRepo.findById(id).orElse(null);
        usuarioAEditar.setApellidoUsr(usuario.getApellidoUsr());
        usuarioAEditar.setDescripcionUsr(usuario.getDescripcionUsr());
        usuarioAEditar.setFotoUsr(usuario.getFotoUsr());
        usuarioAEditar.setNombreUsr(usuario.getNombreUsr());
        
        return usuarioRepo.save(usuarioAEditar);
    }

    public void deleteUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }
    
}
