
package com.prueba.portfolio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prueba.portfolio.security.entity.UsuarioLogin;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name="SKILL")
public class Skill {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long skillId;
    
    private String nombreSkill;
    private int nivelSkill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable=false)
    @JsonIgnore
    private UsuarioLogin usuario;

    public Skill(String nombreSkill, int nivelSkill) {
        this.nombreSkill = nombreSkill;
        this.nivelSkill = nivelSkill;
    }
    
    
}
