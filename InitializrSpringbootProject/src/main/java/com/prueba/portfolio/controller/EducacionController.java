
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.services.EducacionService;
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
@RequestMapping("/educacion")
public class EducacionController {
    private final EducacionService educacionService;

    public EducacionController(EducacionService educacionService) {
        this.educacionService = educacionService;
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Educacion> traerEducacion(@PathVariable(value = "id") Long id) {
        Educacion educacion = educacionService.getEducacion(id);
        return new ResponseEntity<>(educacion, HttpStatus.OK);
    }

    @GetMapping(value = {"/lista"})
    public ResponseEntity<List<Educacion>> traerTodaEducacion() {
        List<Educacion> listaEducacion = educacionService.getAllEducacion();
        return new ResponseEntity<>(listaEducacion, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = {"{id}/edit"})
    public ResponseEntity<Educacion> editEducacion(@PathVariable(value = "id") Long id, @RequestBody Educacion educacion) {
        Educacion educacionEditada = educacionService.editEducacion(id, educacion);
        return new ResponseEntity<>(educacionEditada, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = {"/add"})
    public ResponseEntity<Educacion> addEducacion(@RequestBody Educacion educacion) {
        Educacion nuevaEducacion = educacionService.addEducacion(educacion);
        return new ResponseEntity<>(nuevaEducacion, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = {"/delete/{id}"})
    public void deleteEducacion(@PathVariable(value = "id") Long id) {
        educacionService.deleteEducacion(id);
    }
}
