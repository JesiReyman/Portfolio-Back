
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.services.EducacionService;
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
@RequestMapping("/educacion")
public class EducacionController {
    private final EducacionService educacionService;

    public EducacionController(EducacionService educacionService) {
        this.educacionService = educacionService;
    }

   /* @GetMapping(value = {"/{id}"})
    public ResponseEntity<Educacion> traerEducacion(@PathVariable(value = "id") Long id) {
        Educacion educacion = educacionService.getEducacion(id);
        return new ResponseEntity<>(educacion, HttpStatus.OK);
    }*/

    
    @GetMapping(value = {"/{nombreUsuario}/lista"})
    public ResponseEntity<List<Educacion>> traerTodaEducacionPorUsuario(@PathVariable(value = "nombreUsuario") String nombreUsuario) {
        List<Educacion> listaEducacion = educacionService.getAllEducacionPorUsuario(nombreUsuario);
        return new ResponseEntity<>(listaEducacion, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PostMapping(value = {"/{nombreUsuario}/add"})
    public ResponseEntity<Educacion> addEducacionAUsuario(@RequestBody Educacion educacion, 
                                                          @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Educacion nuevaEducacion = educacionService.agregarEducacionAUsuario(educacion, nombreUsuario);
        return new ResponseEntity<>(nuevaEducacion, HttpStatus.OK);
    }
    

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PutMapping(value = {"/{nombreUsuario}/edit/{id}"})
    public ResponseEntity<Educacion> editEducacion(@PathVariable(value = "id") Long id, 
                                                   @RequestBody Educacion educacion,
                                                   @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Educacion educacionEditada = educacionService.editEducacion(id, educacion);
        return new ResponseEntity<>(educacionEditada, HttpStatus.OK);
    }
/*
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = {"/add"})
    public ResponseEntity<Educacion> addEducacion(@RequestBody Educacion educacion) {
        Educacion nuevaEducacion = educacionService.addEducacion(educacion);
        return new ResponseEntity<>(nuevaEducacion, HttpStatus.OK);
    }
*/
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @DeleteMapping(value = {"/{nombreUsuario}/delete/{id}"})
    public void deleteEducacion(@PathVariable(value = "id") Long id, @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        educacionService.deleteEducacion(id);
    }
}
