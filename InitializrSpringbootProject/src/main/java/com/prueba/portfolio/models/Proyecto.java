
package com.prueba.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name="PROYECTO")
public class Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long proyectoId;
    
    private String titulo;
    private String descripcion;
    private String urlImagen;
    private String urlProyecto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    @JsonIgnore
    private UsuarioLogin usuario;

    public Proyecto(String titulo, String descripcion, String urlImagen, String urlProyecto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.urlProyecto = urlProyecto;
    }
    
    
    
}
