
package com.prueba.portfolio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="EXPERIENCIA")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long experienciaId;
    
    private String tituloExperiencia;
    private int fechaExperiencia;
    private String descripcionExperiencia;

    @ManyToOne
    @JoinColumn(name="id_Usr", nullable=false)
    private Usuario usuario;
}
