
package com.prueba.portfolio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Table(name="PERFIL")
@Entity
public class Perfil {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(nullable = false, updatable = false)
    private Long id;

    private String nombre;
    private String apellido;
    private String descripcion;
    private String urlBanner;
    private String urlFoto;
    private String urlGitHub;
    private String urlLinkedIn;
    
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private UsuarioLogin usuario;

    public Perfil(String nombre, String apellido, String descripcion, String urlBanner, String urlFoto, String urlGitHub, String urlLinkedIn) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.urlBanner = urlBanner;
        this.urlFoto = urlFoto;
        this.urlGitHub = urlGitHub;
        this.urlLinkedIn = urlLinkedIn;
    }

    
}
