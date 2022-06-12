
package com.prueba.portfolio.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id_Usr; 
    
    private String nombreUsr;
    private String apellidoUsr;
    private String descripcionUsr;
    private String fotoUsr;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_Edu")
    private List<Educacion> educacionList;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_Skill")
    private List<Skill> skillList;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_Experiencia")
    private List<Experiencia> experienciaList;

    public Long getId_Usr() {
        return id_Usr;
    }

    public void setId_Usr(Long id_Usr) {
        this.id_Usr = id_Usr;
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
