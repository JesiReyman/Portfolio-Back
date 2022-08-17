
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Educacion;
//import com.prueba.portfolio.models.Perfil;
import com.prueba.portfolio.repository.EducacionRepo;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducacionService {
    private final EducacionRepo eduRepo;
    private final UsuarioRepository usuarioLoginRepo;
    
    @Autowired
    public EducacionService(EducacionRepo eduRepo, UsuarioRepository usuarioLoginRepo) {
        this.eduRepo = eduRepo;
        this.usuarioLoginRepo = usuarioLoginRepo;
    }
    
    /*@PersistenceContext
    EntityManager entityManager;*/
   /*
    public Educacion addEducacion(Educacion educacion) {
        return eduRepo.save(educacion);
    }*/
    
    public Educacion agregarEducacionAUsuario(Educacion educacion, String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        //educacion.setUsuario(usuario);
        usuario.addEducacion(educacion);
        //entityManager.persist(educacion);
        return eduRepo.save(educacion);
    }
    
/*
    public List<Educacion> getAllEducacionByUserId(Long userId) {
        //return eduRepo.findByUser(userId);
        List<Educacion> filteredValues = new ArrayList();
        List<Educacion> educations = eduRepo.findAll();
        
        for(Educacion edu : educations)
        {
            if(edu.usuario.getId() == userId)
            {
                filteredValues.add(edu);
            }
        }
        
        return filteredValues;
    }*/
    
    public List<Educacion> getAllEducacionPorUsuario(String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        Long usuarioId = usuario.getId();
        return eduRepo.findByUsuarioId(usuarioId);
    }

    public Educacion getEducacion(Long id) {
        return eduRepo.findById(id).orElse(null);
    }

    public Educacion editEducacion(Long id, Educacion educacion) {
        Educacion educacionAEditar = eduRepo.findById(id).orElse(null);
        educacionAEditar.setDescripcionEdu(educacion.getDescripcionEdu());
        educacionAEditar.setAnioInicio(educacion.getAnioInicio());
        educacionAEditar.setEstado(educacion.getEstado());
        educacionAEditar.setTituloEdu(educacion.getTituloEdu());
        
        return eduRepo.save(educacionAEditar);
    }

    public void deleteEducacion(Long id) {
        eduRepo.deleteById(id);
    }
}
