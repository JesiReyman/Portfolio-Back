
package com.prueba.portfolio.controller;

import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.models.Usuario;
import com.prueba.portfolio.services.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Usuario> traerUsuario(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioService.getUsuario(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    @PostMapping(value = {"/{usuarioId}/nuevaEducacion"})
    public ResponseEntity<Usuario> addEducacionByIdUsuario(@RequestBody Educacion educacion, @PathVariable(value = "usuarioId") Long usuarioId) {
        Usuario usuario = usuarioService.addEducacion(educacion, usuarioId);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
    /*
    @DeleteMapping(value = {"/{usuarioId}/deleteEducacion/{educacionId}"})
    public ResponseEntity<Usuario> deleteEducacionByIdUsuario(@PathVariable(value = "usuarioId") Long usuarioId, @PathVariable(value = "educacionId") Long educacionId) {
        Usuario usuario = usuarioService.deleteEducacion(usuarioId, educacionId);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }*/
    
    
    @GetMapping(value = {"/lista"})
    public ResponseEntity<List<Usuario>> traerTodoUsuario() {
        List<Usuario> listaUsuario = usuarioService.getAllUsuario();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = {"{id}/edit"})
    public ResponseEntity<Usuario> editUsuario(@PathVariable(value = "id") Long id, @RequestBody Usuario usuario) {
        Usuario usuarioEditado = usuarioService.editUsuario(id, usuario);
        return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = {"/add"})
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.addUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = {"/delete/{id}"})
    public void deleteUsuario(@PathVariable(value = "id") Long id) {
        usuarioService.deleteUsuario(id);
    }
    
    
}
