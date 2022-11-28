
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
@Table(name="EDUCACION")
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id_Edu;
    
    private String tituloEdu;
  
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Temporal(TemporalType.DATE)
    private Date anioInicio;
    private String estado;
    private String descripcionEdu;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    @JsonIgnore
    private UsuarioLogin usuario;

    public Educacion(String tituloEdu, Date anioInicio, String estado, String descripcionEdu) {
        this.tituloEdu = tituloEdu;
        this.anioInicio = anioInicio;
        this.estado = estado;
        this.descripcionEdu = descripcionEdu;
    }

}
