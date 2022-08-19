
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Proyecto;
import com.prueba.portfolio.repository.ProyectoRepo;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.repository.UsuarioRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectoService {
    private final ProyectoRepo proyectoRepo;
    private final UsuarioRepository usuarioLoginRepo;
    
    @Autowired
    public ProyectoService(ProyectoRepo proyectoRepo, UsuarioRepository usuarioLoginRepo) {
        this.proyectoRepo = proyectoRepo;
        this.usuarioLoginRepo = usuarioLoginRepo;
    }
    
    public Proyecto agregarProyectoAUsuario(Proyecto proyecto, String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        
        usuario.addProyecto(proyecto);
        
        return proyectoRepo.save(proyecto);
    }
    
    public List<Proyecto> getAllProyectoPorUsuario(String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        Long usuarioId = usuario.getId();
        return proyectoRepo.findByUsuarioId(usuarioId);
    }
    
    public Proyecto editProyecto(Long id, Proyecto proyecto) {
        Proyecto proyectoAEditar = proyectoRepo.findById(id).orElse(null);
        proyectoAEditar.setTitulo(proyecto.getTitulo());
        proyectoAEditar.setDescripcion(proyecto.getDescripcion());
        proyectoAEditar.setUrlImagen(proyecto.getUrlImagen());
        proyectoAEditar.setUrlProyecto(proyecto.getUrlProyecto());
        
        return proyectoRepo.save(proyectoAEditar);
    }
    
    public void deleteProyecto(Long id) {
        proyectoRepo.deleteById(id);
    }
}
