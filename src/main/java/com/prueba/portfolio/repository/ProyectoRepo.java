
package com.prueba.portfolio.repository;

import com.prueba.portfolio.models.Proyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepo extends JpaRepository<Proyecto, Long>{
    List<Proyecto> findByUsuarioId(Long usuarioId);
    
    List<Proyecto> findByUsuarioNombreUsuario(String nombreUsuario);
}
