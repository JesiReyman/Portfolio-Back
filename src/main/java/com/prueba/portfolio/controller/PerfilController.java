
package com.prueba.portfolio.controller;


import com.prueba.portfolio.models.Perfil;
import com.prueba.portfolio.services.PerfilService;
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
@RequestMapping("/perfil")
public class PerfilController {
    private final PerfilService perfilService;
    
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
        
    }
    
    @GetMapping(value = {"/{nombreUsuario}"})
    public ResponseEntity<Perfil> traerPerfil(@PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Perfil perfil = perfilService.getPerfil(nombreUsuario);
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }
    

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PutMapping(value = {"/{nombreUsuario}/edit/{id}"})
    public ResponseEntity<Perfil> editPerfil(@PathVariable(value = "id") Long id, 
                                               @RequestBody Perfil perfil, 
                                               @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Perfil perfilEditado = perfilService.editPerfil(id, perfil);
        return new ResponseEntity<>(perfilEditado, HttpStatus.OK);
    }

    
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PostMapping(value = {"/{nombreUsuario}/add"})
    public ResponseEntity<Perfil> addPerfil(@RequestBody Perfil perfil, @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Perfil nuevoPerfil = perfilService.addPerfil(perfil, nombreUsuario);
        return new ResponseEntity<>(nuevoPerfil, HttpStatus.OK);
    }

    
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @DeleteMapping(value = {"/{nombreUsuario}/delete/{id}"})
    public void deletePerfil(@PathVariable(value = "nombreUsuario") String nombreUsuario, @PathVariable(value = "id") Long id) {
        perfilService.deletePerfil(id);
    }
    
}
