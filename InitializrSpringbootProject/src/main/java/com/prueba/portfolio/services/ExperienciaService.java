
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Experiencia;
import com.prueba.portfolio.repository.ExperienciaRepo;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.repository.UsuarioRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaService {
    private final ExperienciaRepo experienciaRepo;
    private final UsuarioRepository usuarioLoginRepo;

    @Autowired
     public ExperienciaService(ExperienciaRepo experienciaRepo, UsuarioRepository usuarioLoginRepo) {
        this.experienciaRepo = experienciaRepo;
        this.usuarioLoginRepo = usuarioLoginRepo;
    }

    public Experiencia addExperienciaAUsuario(Experiencia experiencia, String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        usuario.addExperiencia(experiencia);
        return experienciaRepo.save(experiencia);
    }

    public List<Experiencia> getAllExperienciaPorUsuario(String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        Long usuarioId = usuario.getId();
        return experienciaRepo.findByUsuarioId(usuarioId);
    }

    public Experiencia getExperiencia(Long id) {
        return experienciaRepo.findById(id).orElse(null);
    }

    public Experiencia editExperiencia(Long id, Experiencia experiencia) {
        Experiencia experienciaAEditar = experienciaRepo.findById(id).orElse(null);
        experienciaAEditar.setDescripcionExperiencia(experiencia.getDescripcionExperiencia());
        experienciaAEditar.setAnioInicio(experiencia.getAnioInicio());
        experienciaAEditar.setAnioFin(experiencia.getAnioFin());
        experienciaAEditar.setActualidad(experiencia.isActualidad());
        experienciaAEditar.setTituloExperiencia(experiencia.getTituloExperiencia());
        
        return experienciaRepo.save(experienciaAEditar);
    }

    public void deleteExperiencia(Long id) {
        experienciaRepo.deleteById(id);
    }
}
