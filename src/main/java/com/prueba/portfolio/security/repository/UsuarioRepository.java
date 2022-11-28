
package com.prueba.portfolio.security.repository;

import com.prueba.portfolio.security.entity.UsuarioLogin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioLogin, Integer>{
    Optional<UsuarioLogin> findByNombreUsuario(String nombreUsuario);
    
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
}
