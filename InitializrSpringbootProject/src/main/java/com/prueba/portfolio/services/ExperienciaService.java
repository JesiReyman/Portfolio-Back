
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Experiencia;
import com.prueba.portfolio.repository.ExperienciaRepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaService {
    private final ExperienciaRepo experienciaRepo;

    @Autowired
     public ExperienciaService(ExperienciaRepo experienciaRepo) {
        this.experienciaRepo = experienciaRepo;
    }

    public Experiencia addExperiencia(Experiencia experiencia) {
        return experienciaRepo.save(experiencia);
    }

    public List<Experiencia> getAllExperiencia() {
        return experienciaRepo.findAll();
    }

    public Experiencia getExperiencia(Long id) {
        return experienciaRepo.findById(id).orElse(null);
    }

    public Experiencia editExperiencia(Long id, Experiencia experiencia) {
        Experiencia experienciaAEditar = experienciaRepo.findById(id).orElse(null);
        experienciaAEditar.setDescripcionExperiencia(experiencia.getDescripcionExperiencia());
        experienciaAEditar.setFechaExperiencia(experiencia.getFechaExperiencia());
        experienciaAEditar.setTituloExperiencia(experiencia.getTituloExperiencia());
        
        return experienciaRepo.save(experienciaAEditar);
    }

    public void deleteExperiencia(Long id) {
        experienciaRepo.deleteById(id);
    }
}
