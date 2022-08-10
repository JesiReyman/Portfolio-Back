
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Skill;
import com.prueba.portfolio.repository.SkillRepo;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.repository.UsuarioRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SkillService {
    private final SkillRepo skillRepo;
    private final UsuarioRepository usuarioLoginRepo;
    
    @Autowired
    public SkillService(SkillRepo skillRepo, UsuarioRepository usuarioLoginRepo) {
        this.skillRepo = skillRepo;
        this.usuarioLoginRepo = usuarioLoginRepo;
    }
    
    public Skill addSkillAUsuario(Skill skill, String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        usuario.addSkill(skill);
        return skillRepo.save(skill);
    }

    public List<Skill> getAllSkillPorUsuario(String nombreUsuario) {
        UsuarioLogin usuario = usuarioLoginRepo.findByNombreUsuario(nombreUsuario).orElse(null);
        Long usuarioId = usuario.getId();
        return skillRepo.findByUsuarioId(usuarioId);
    }

    public Skill getSkill(Long id) {
        return skillRepo.findById(id).orElse(null);
    }

    public Skill editSkill(Long id, Skill skill) {
        Skill skillAEditar = skillRepo.findById(id).orElse(null);
        skillAEditar.setNivelSkill(skill.getNivelSkill());
        skillAEditar.setNombreSkill(skill.getNombreSkill());
        
        return skillRepo.save(skillAEditar);
    }

    public void deleteSkill(Long id) {
        skillRepo.deleteById(id);
    }
}
