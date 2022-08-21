
package com.prueba.portfolio.repository;

import com.prueba.portfolio.models.Experiencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepo extends JpaRepository<Experiencia, Long>{
    List<Experiencia> findByUsuarioId(Long usuarioId);
    
    List<Experiencia> findByUsuarioNombreUsuario(String nombreUsuario);
}
