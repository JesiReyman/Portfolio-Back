
package com.prueba.portfolio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Experiencia {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long id_Experiencia;
    private String tituloExperiencia;
    private int fechaExperiencia;
    private String descripcionExperiencia;
    
}
