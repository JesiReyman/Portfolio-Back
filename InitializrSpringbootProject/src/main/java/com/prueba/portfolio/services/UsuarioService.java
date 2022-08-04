
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.models.Usuario;
import com.prueba.portfolio.repository.EducacionRepo;
import com.prueba.portfolio.repository.UsuarioRepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;
    
   // private final EducacionRepo educacionRepo;
    
    @Autowired
    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
       // this.educacionRepo = educacionRepo;
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
    
    public Usuario addEducacion(Educacion educacion, Long usuarioId){
        Usuario usuario = this.getUsuario(usuarioId);
        usuario.addEducacion(educacion);
        return usuarioRepo.save(usuario);
    }
    
    /*
    public Usuario deleteEducacion(Long usuarioId, Long educacionId){
        Educacion educacion = educacionRepo.findById(educacionId).orElse(null);
        educacionRepo.delete(educacion);
        Usuario usuario = this.getUsuario(usuarioId);
        //usuario.removeEducacion(educacion);
        return usuarioRepo.save(usuario);
    }
    */
}
