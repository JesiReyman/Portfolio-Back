
package com.prueba.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter @Setter
@NoArgsConstructor
@Table(name="USUARIO")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String nombreUsr;
    private String apellidoUsr;
    private String descripcionUsr;
    private String fotoUsr;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.MERGE,
        orphanRemoval = true)
    //@JsonIgnore
    private List<Educacion> educacionList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Skill> skillList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Experiencia> experienciaList;

    public Usuario(String nombreUsr, String apellidoUsr, String descripcionUsr, String fotoUsr) {
        this.nombreUsr = nombreUsr;
        this.apellidoUsr = apellidoUsr;
        this.descripcionUsr = descripcionUsr;
        this.fotoUsr = fotoUsr;
    }

    
    
    public void addEducacion(Educacion educacion) {
        educacionList.add(educacion);
        educacion.setUsuario(this);
    }
 
    public void removeEducacion(Educacion educacion) {
        educacionList.remove(educacion);
        educacion.setUsuario(null);
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsr() {
        return nombreUsr;
    }

    public void setNombreUsr(String nombreUsr) {
        this.nombreUsr = nombreUsr;
    }

    public String getApellidoUsr() {
        return apellidoUsr;
    }

    public void setApellidoUsr(String apellidoUsr) {
        this.apellidoUsr = apellidoUsr;
    }

    public String getDescripcionUsr() {
        return descripcionUsr;
    }

    public void setDescripcionUsr(String descripcionUsr) {
        this.descripcionUsr = descripcionUsr;
    }

    public String getFotoUsr() {
        return fotoUsr;
    }

    public void setFotoUsr(String fotoUsr) {
        this.fotoUsr = fotoUsr;
    }

    
}
