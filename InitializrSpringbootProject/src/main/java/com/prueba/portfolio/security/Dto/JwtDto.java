
package com.prueba.portfolio.security.Dto;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class JwtDto {
    private String token;
    

    public JwtDto(String token) {
        this.token = token;
    }
    
    
}
