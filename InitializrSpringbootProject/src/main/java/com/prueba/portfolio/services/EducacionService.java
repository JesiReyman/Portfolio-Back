
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.repository.EducacionRepo;
import java.util.List;
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

    public Educacion addEducacion(Educacion educacion) {
        return eduRepo.save(educacion);
    }

    public List<Educacion> getAllEducacion() {
        return eduRepo.findAll();
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
