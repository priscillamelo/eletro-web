package com.tads.projeto_eletrodomesticos.domain;

import com.tads.projeto_eletrodomesticos.error_handling.ErrorsMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = ErrorsMessages.ErroCampoVazio)
    //@Min(value = 4, message = ErrorsMessages.ErroMinUsername)
    private String username;
    //@NotBlank(message = ErrorsMessages.ErroCampoVazio)
    //@Min(value = 6, message = ErrorsMessages.ErroMinPassword)
    private String password;
    //@NotBlank(message = ErrorsMessages.ErroCampoVazio)
    private String nome;
    //@NotBlank(message = ErrorsMessages.ErroCampoVazio)
    //@Email(message = ErrorsMessages.ErroEmail)
    private String email;
    private boolean admin;
}
