
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Proyecto;
import com.prueba.portfolio.services.ProyectoService;
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
@RequestMapping("/proyecto")
public class ProyectoController {
    
    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    
    @GetMapping(value = {"/{nombreUsuario}/lista"})
    public ResponseEntity<List<Proyecto>> traerTodoProyectoPorUsuario(@PathVariable(value = "nombreUsuario") String nombreUsuario) {
        List<Proyecto> listaProyecto = proyectoService.getAllProyectoPorUsuario(nombreUsuario);
        return new ResponseEntity<>(listaProyecto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PostMapping(value = {"/{nombreUsuario}/add"})
    public ResponseEntity<Proyecto> addProyectoAUsuario(@RequestBody Proyecto proyecto, 
                                                          @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Proyecto nuevoProyecto = proyectoService.agregarProyectoAUsuario(proyecto, nombreUsuario);
        return new ResponseEntity<>(nuevoProyecto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PutMapping(value = {"/{nombreUsuario}/edit/{id}"})
    public ResponseEntity<Proyecto> editProyecto(@PathVariable(value = "id") Long id, 
                                                   @RequestBody Proyecto proyecto,
                                                   @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Proyecto proyectoEditado = proyectoService.editProyecto(id, proyecto);
        return new ResponseEntity<>(proyectoEditado, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @DeleteMapping(value = {"/{nombreUsuario}/delete/{id}"})
    public void deleteEducacion(@PathVariable(value = "id") Long id, @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        proyectoService.deleteProyecto(id);
    }
    
}
