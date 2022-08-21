
package com.prueba.portfolio.repository;

import com.prueba.portfolio.models.Educacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepo extends JpaRepository<Educacion, Long>{
    List<Educacion> findByUsuarioId(Long usuarioId);
    
    List<Educacion> findByUsuarioNombreUsuario(String nombreUsuario);
}
