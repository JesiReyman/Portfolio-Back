
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Skill;
import com.prueba.portfolio.services.SkillService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-9dcf2.web.app")
@RequestMapping("/skill")

public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }
    
   /* @GetMapping(value = {"/{id}"})
    public ResponseEntity<Skill> traerSkill(@PathVariable(value = "id") Long id) {
        Skill skill = skillService.getSkill(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }*/

    @GetMapping(value = {"/{nombreUsuario}/lista"})
    public ResponseEntity<List<Skill>> traerTodaSkillDeUsuario(@PathVariable(value = "nombreUsuario") String nombreUsuario) {
        List<Skill> listaSkill = skillService.getAllSkillPorUsuario(nombreUsuario);
        return new ResponseEntity<>(listaSkill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PutMapping(value = {"/{nombreUsuario}/edit/{id}"})
    public ResponseEntity<Skill> editSkill(@PathVariable(value = "id") Long id, 
                                           @RequestBody Skill skill,
                                           @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Skill skillEditada = skillService.editSkill(id, skill);
        return new ResponseEntity<>(skillEditada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PostMapping(value = {"/{nombreUsuario}/add"})
    public ResponseEntity<Skill> addSkillAUnUsuario(@RequestBody Skill skill, @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Skill nuevaSkill = skillService.addSkillAUsuario(skill, nombreUsuario);
        return new ResponseEntity<>(nuevaSkill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @DeleteMapping(value = {"/{nombreUsuario}/delete/{id}"})
    public void deleteSkill(@PathVariable(value = "id") Long id, @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        skillService.deleteSkill(id);
    }
}
