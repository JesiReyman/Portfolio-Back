
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Experiencia;
import com.prueba.portfolio.services.ExperienciaService;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/experiencia")
public class ExperienciaController {
    private final ExperienciaService experienciaService;

    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }
    
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Experiencia> traerExperiencia(@PathVariable(value = "id") Long id) {
        Experiencia experiencia = experienciaService.getExperiencia(id);
        return new ResponseEntity<>(experiencia, HttpStatus.OK);
    }

    @GetMapping(value = {"/lista"})
    public ResponseEntity<List<Experiencia>> traerTodaExperiencia() {
        List<Experiencia> listaExperiencia = experienciaService.getAllExperiencia();
        return new ResponseEntity<>(listaExperiencia, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = {"{id}/edit"})
    public ResponseEntity<Experiencia> editExperiencia(@PathVariable(value = "id") Long id, @RequestBody Experiencia experiencia) {
        Experiencia experienciaEditada = experienciaService.editExperiencia(id, experiencia);
        return new ResponseEntity<>(experienciaEditada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = {"/add"})
    public ResponseEntity<Experiencia> addEducacion(@RequestBody Experiencia experiencia) {
        Experiencia nuevaExperiencia = experienciaService.addExperiencia(experiencia);
        return new ResponseEntity<>(nuevaExperiencia, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = {"/delete/{id}"})
    public void deleteExperiencia(@PathVariable(value = "id") Long id) {
        experienciaService.deleteExperiencia(id);
    }
}
