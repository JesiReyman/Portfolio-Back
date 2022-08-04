
package com.prueba.portfolio.models;

//import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
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
    //@Column(nullable = false, updatable = false)
    private Long educacionId;
    
    private String tituloEdu;
    private int fechaEdu;
    private String descripcionEdu;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    @JsonIgnore
    public Usuario usuario;

    public Educacion(String tituloEdu, int fechaEdu, String descripcionEdu) {
        this.tituloEdu = tituloEdu;
        this.fechaEdu = fechaEdu;
        this.descripcionEdu = descripcionEdu;
    }
    
    
}
