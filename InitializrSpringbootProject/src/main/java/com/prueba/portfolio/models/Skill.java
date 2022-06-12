
package com.prueba.portfolio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Skill {
    
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long id_Skill;
    private String nombreSkill;
    private int nivelSkill;
    
}
