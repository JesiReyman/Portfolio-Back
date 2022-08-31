
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
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-9dcf2.web.app")
@RequestMapping("/experiencia")
public class ExperienciaController {
    private final ExperienciaService experienciaService;

    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @GetMapping(value = {"/{nombreUsuario}/lista"})
    public ResponseEntity<List<Experiencia>> traerTodaExperienciaDeUsuario(@PathVariable(value = "nombreUsuario") String nombreUsuario) {
        List<Experiencia> listaExperiencia = experienciaService.getAllExperienciaPorUsuario(nombreUsuario);
        return new ResponseEntity<>(listaExperiencia, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PutMapping(value = {"/{nombreUsuario}/edit/{id}"})
    public ResponseEntity<Experiencia> editExperiencia(@PathVariable(value = "id") Long id, 
                                                       @RequestBody Experiencia experiencia, 
                                                       @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Experiencia experienciaEditada = experienciaService.editExperiencia(id, experiencia);
        return new ResponseEntity<>(experienciaEditada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PostMapping(value = {"/{nombreUsuario}/add"})
    public ResponseEntity<Experiencia> addExperienciaAUsuario(@RequestBody Experiencia experiencia, 
                                                              @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Experiencia nuevaExperiencia = experienciaService.addExperienciaAUsuario(experiencia, nombreUsuario);
        return new ResponseEntity<>(nuevaExperiencia, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @DeleteMapping(value = {"/{nombreUsuario}/delete/{id}"})
    public void deleteExperiencia(@PathVariable(value = "id") Long id, @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        experienciaService.deleteExperiencia(id);
    }
}
