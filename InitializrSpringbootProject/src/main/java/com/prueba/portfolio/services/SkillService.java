
package com.prueba.portfolio.services;

import com.prueba.portfolio.models.Skill;
import com.prueba.portfolio.repository.SkillRepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SkillService {
    private final SkillRepo skillRepo;
    
    @Autowired
    public SkillService(SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }
    
    public Skill addSkill(Skill skill) {
        return skillRepo.save(skill);
    }

    public List<Skill> getAllSkill() {
        return skillRepo.findAll();
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
