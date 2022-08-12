
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.models.Perfil;
import com.prueba.portfolio.repository.EducacionRepo;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.repository.UsuarioRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.portfolio.repository.PerfilRepo;

@Service
@Transactional
public class PerfilService {
    private final PerfilRepo perfilRepo;
    private final UsuarioRepository usuarioLoginRepo;
    
   // private final EducacionRepo educacionRepo;
    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    public PerfilService(PerfilRepo perfilRepo, UsuarioRepository usuarioLoginRepo) {
        this.perfilRepo = perfilRepo;
        this.usuarioLoginRepo = usuarioLoginRepo;
       // this.educacionRepo = educacionRepo;
    }
    
    /*public Perfil addPerfil(Perfil perfil, Long nombreUsuario) {
        UsuarioLogin usuarioLogin = entityManager.find(UsuarioLogin.class, nombreUsuario);
        perfil.setUsuarioLogin(usuarioLogin);
        usuarioLogin.setUsuario(perfil);
        entityManager.persist(perfil);
        return perfilRepo.save(perfil);
    }*/
    
    public Perfil addPerfil(Perfil perfil, String nombreUsuario) {
        UsuarioLogin usuarioLogin = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        perfil.setUsuario(usuarioLogin);
        usuarioLogin.setPerfil(perfil);
        entityManager.persist(perfil);
        return perfilRepo.save(perfil);
    }

    public Perfil getPerfil(String nombreUsuario) {
        UsuarioLogin usuarioLogin = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        Long id = usuarioLogin.getId();
        return perfilRepo.findById(id).orElse(null);
    }

    public Perfil editPerfil(Long id, Perfil perfil) {
        //UsuarioLogin usuarioLogin = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        //Long id = usuarioLogin.getId();
        Perfil perfilAEditar = perfilRepo.findById(id).orElse(null);
        perfilAEditar.setApellido(perfil.getApellido());
        perfilAEditar.setDescripcion(perfil.getDescripcion());
        perfilAEditar.setUrlFoto(perfil.getUrlFoto());
        perfilAEditar.setNombre(perfil.getNombre());
        perfilAEditar.setUrlBanner(perfil.getUrlBanner());
        
        return perfilRepo.save(perfilAEditar);
    }
/*
    public void deletePerfil(String nombreUsuario) {
        UsuarioLogin usuarioLogin = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        Long id = usuarioLogin.getId();
        System.out.println("el id a eliminar es: " + id);
        perfilRepo.deleteById(id);
        
    }*/
    
    public void deletePerfil(Long id) {
        perfilRepo.deleteById(id); 
    }
    
    /*
    public Perfil addEducacion(Educacion educacion, Long usuarioId){
        Perfil usuario = this.getPerfil(usuarioId);
        usuario.addEducacion(educacion);
        return perfilRepo.save(usuario);
    }*/
    
    /*
    public Perfil deleteEducacion(Long usuarioId, Long educacionId){
        Educacion educacion = educacionRepo.findById(educacionId).orElse(null);
        educacionRepo.delete(educacion);
        Perfil usuario = this.getPerfil(usuarioId);
        //usuario.removeEducacion(educacion);
        return perfilRepo.save(usuario);
    }
    */
}
