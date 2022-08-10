
package com.prueba.portfolio.repository;

import com.prueba.portfolio.models.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long>{
    List<Skill> findByUsuarioId(Long usuarioId);
}
