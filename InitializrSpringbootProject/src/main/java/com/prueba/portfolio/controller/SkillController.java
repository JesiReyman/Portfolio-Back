
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Skill;
import com.prueba.portfolio.services.SkillService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")

public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
    
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Skill> traerSkill(@PathVariable(value = "id") Long id) {
        Skill skill = skillService.getSkill(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @GetMapping(value = {"/lista"})
    public ResponseEntity<List<Skill>> traerTodaSkill() {
        List<Skill> listaSkill = skillService.getAllSkill();
        return new ResponseEntity<>(listaSkill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = {"{id}/edit"})
    public ResponseEntity<Skill> editSkill(@PathVariable(value = "id") Long id, @RequestBody Skill skill) {
        Skill skillEditada = skillService.editSkill(id, skill);
        return new ResponseEntity<>(skillEditada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = {"/add"})
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill) {
        Skill nuevaSkill = skillService.addSkill(skill);
        return new ResponseEntity<>(nuevaSkill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = {"/delete/{id}"})
    public void deleteSkill(@PathVariable(value = "id") Long id) {
        skillService.deleteSkill(id);
    }
}
