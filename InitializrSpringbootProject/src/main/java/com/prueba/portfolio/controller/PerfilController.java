
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.models.Perfil;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import com.prueba.portfolio.security.entity.UsuarioPrincipal;
import com.prueba.portfolio.security.service.UserDetailsImplements;
import com.prueba.portfolio.security.service.UsuarioLoginService;
import com.prueba.portfolio.services.PerfilService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    
    /*
    @PostMapping(value = {"/{usuarioId}/nuevaEducacion"})
    public ResponseEntity<Usuario> addEducacionByIdUsuario(@RequestBody Educacion educacion, @PathVariable(value = "usuarioId") Long usuarioId) {
        Perfil perfil = perfilService.addEducacion(educacion, usuarioId);
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }*/
    
    /*
    @DeleteMapping(value = {"/{usuarioId}/deleteEducacion/{educacionId}"})
    public ResponseEntity<Usuario> deleteEducacionByIdUsuario(@PathVariable(value = "usuarioId") Long usuarioId, @PathVariable(value = "educacionId") Long educacionId) {
        Perfil perfil = perfilService.deleteEducacion(usuarioId, educacionId);
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }*/
    
    
    /*
    @GetMapping(value = {"/lista"})
    public ResponseEntity<List<Usuario>> traerTodoUsuario() {
        List<Usuario> listaUsuario = perfilService.getAllUsuario();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }*/

    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @PutMapping(value = {"/{nombreUsuario}/edit/{id}"})
    public ResponseEntity<Perfil> editPerfil(@PathVariable(value = "id") Long id, 
                                               @RequestBody Perfil perfil, 
                                               @PathVariable(value = "nombreUsuario") String nombreUsuario) {
        Perfil perfilEditado = perfilService.editPerfil(id, perfil);
        return new ResponseEntity<>(perfilEditado, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
   /* @PostMapping(value = {"{nombreUsuario}/add"})
    public ResponseEntity<Usuario> addPerfil(@RequestBody Perfil perfil, @PathVariable(value = "nombreUsuario") Long nombreUsuario) {
        Perfil nuevoPerfil = perfilService.addPerfil(perfil, nombreUsuario);
        return new ResponseEntity<>(nuevoPerfil, HttpStatus.OK);
    }*/
    
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
    
    /*
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @DeleteMapping(value = {"/{nombreUsuario}/delete"})
    public void deletePerfil(@PathVariable(value = "nombreUsuario") String nombreUsuario) {
        perfilService.deletePerfil(nombreUsuario);
    }*/
    /*
    @PreAuthorize("hasRole('ADMIN') || #nombreUsuario == authentication.principal.username")
    @GetMapping(value = {"/{nombreUsuario}/principal"})
    public String getPrincipal(@PathVariable(value = "nombreUsuario") String nombreUsuario){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
         String name = "Jesi";
        return name;
}*/
    
    
}
