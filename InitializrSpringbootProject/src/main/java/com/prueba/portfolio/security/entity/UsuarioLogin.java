
package com.prueba.portfolio.security.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prueba.portfolio.models.Educacion;
import com.prueba.portfolio.models.Experiencia;
import com.prueba.portfolio.models.Perfil;
import com.prueba.portfolio.models.Skill;
import com.sun.istack.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
//@Data
@NoArgsConstructor
@Entity
@Table(name="USUARIO")
public class UsuarioLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", 
                joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "rol_id") )
    
    private Set<Rol> roles = new HashSet<>();

    @JsonManagedReference
    @OneToOne(mappedBy="usuario", cascade = {CascadeType.MERGE}, orphanRemoval = true)
    private Perfil perfil;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE,
        orphanRemoval = true)
    //@JsonIgnore
    private List<Educacion> educacionList;
     
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE,
        orphanRemoval = true)
    //@JsonIgnore
    private List<Experiencia> experienciaList;
     
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE,
        orphanRemoval = true)
    //@JsonIgnore
    private List<Skill> skillList; 
     
    public UsuarioLogin(String nombre, String nombreUsuario, String email, String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }
    
    public void addEducacion(Educacion educacion) {
        educacionList.add(educacion);
        educacion.setUsuario(this);
    }
 
    public void removeEducacion(Educacion educacion) {
        educacionList.remove(educacion);
        educacion.setUsuario(null);
    }
    
    public void addExperiencia(Experiencia experiencia) {
        experienciaList.add(experiencia);
        experiencia.setUsuario(this);
    }
    
    public void addSkill(Skill skill) {
        skillList.add(skill);
        skill.setUsuario(this);
    }
    
    public void addPerfil(Perfil perfil) {
       // perfil.add(perfil);
        perfil.setUsuario(this);
    }
}
