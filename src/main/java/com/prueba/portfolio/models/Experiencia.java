
package com.prueba.portfolio.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import java.util.Date;
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
@Table(name="EXPERIENCIA")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long experienciaId;
    
    private String tituloExperiencia;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date anioInicio;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date anioFin;
    private boolean actualidad;
    private String descripcionExperiencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    @JsonIgnore
    private UsuarioLogin usuario;

    public Experiencia(String tituloExperiencia, Date anioInicio, Date anioFin, boolean actualidad, String descripcionExperiencia) {
        this.tituloExperiencia = tituloExperiencia;
        this.anioInicio = anioInicio;
        this.anioFin = anioFin;
        this.actualidad = actualidad;
        this.descripcionExperiencia = descripcionExperiencia;
    }

}
