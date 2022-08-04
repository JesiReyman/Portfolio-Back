
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
@Table(name="SKILL")
public class Skill {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long skillId;
    
    private String nombreSkill;
    private int nivelSkill;

    @ManyToOne
    @JoinColumn(name="id_Usr", nullable=false)
    private Usuario usuario;
}
