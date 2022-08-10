
package com.prueba.portfolio.repository;

import com.prueba.portfolio.models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepo extends JpaRepository<Perfil, Long>{
    
}
