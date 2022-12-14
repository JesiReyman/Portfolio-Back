
package com.prueba.portfolio.services;


import com.prueba.portfolio.models.Perfil;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.repository.UsuarioRepository;

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
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    public PerfilService(PerfilRepo perfilRepo, UsuarioRepository usuarioLoginRepo) {
        this.perfilRepo = perfilRepo;
        this.usuarioLoginRepo = usuarioLoginRepo;
    }
    
    
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
        
        Perfil perfilAEditar = perfilRepo.findById(id).orElse(null);
        perfilAEditar.setApellido(perfil.getApellido());
        perfilAEditar.setDescripcion(perfil.getDescripcion());
        perfilAEditar.setUrlFoto(perfil.getUrlFoto());
        perfilAEditar.setNombre(perfil.getNombre());
        perfilAEditar.setUrlBanner(perfil.getUrlBanner());
        perfilAEditar.setUrlGitHub(perfil.getUrlGitHub());
        perfilAEditar.setUrlLinkedIn(perfil.getUrlLinkedIn());
        
        return perfilRepo.save(perfilAEditar);
    }
    
    public void deletePerfil(Long id) {
        perfilRepo.deleteById(id); 
    }
    
    
}
