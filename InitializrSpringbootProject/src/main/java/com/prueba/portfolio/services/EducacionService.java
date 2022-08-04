
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.models.Usuario;
import com.prueba.portfolio.repository.EducacionRepo;

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
    
    @Autowired
    public EducacionService(EducacionRepo eduRepo) {
        this.eduRepo = eduRepo;
    }
    
    /*@PersistenceContext
    EntityManager entityManager;*/
   /*
    public Educacion addEducacion(Educacion educacion) {
        return eduRepo.save(educacion);
    }*/
    
    /*public void addEducacionByIdUsuario(Educacion educacion, Long usuarioId) {
        Usuario usuario = entityManager.getReference(Usuario.class, usuarioId);
        educacion.setUsuario(usuario);
        usuario.addEducacion(educacion);
        entityManager.persist(educacion);
        return eduRepo.save(educacion);
    }*/
    
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
    
    public List<Educacion> getAllEducacionPorUsuarioId(Long usuarioId) {
        return eduRepo.findByUsuarioId(usuarioId);
    }

    public Educacion getEducacion(Long id) {
        return eduRepo.findById(id).orElse(null);
    }

    public Educacion editEducacion(Long id, Educacion educacion) {
        Educacion educacionAEditar = eduRepo.findById(id).orElse(null);
        educacionAEditar.setDescripcionEdu(educacion.getDescripcionEdu());
        educacionAEditar.setFechaEdu(educacion.getFechaEdu());
        educacionAEditar.setTituloEdu(educacion.getTituloEdu());
        
        return eduRepo.save(educacionAEditar);
    }

    public void deleteEducacion(Long id) {
        eduRepo.deleteById(id);
    }
}
