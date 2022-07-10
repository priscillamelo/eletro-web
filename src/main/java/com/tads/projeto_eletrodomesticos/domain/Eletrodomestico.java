package com.tads.projeto_eletrodomesticos.domain;

import com.tads.projeto_eletrodomesticos.error_handling.ErrorsMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Eletrodomestico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = ErrorsMessages.ErroCampoVazio)
    private String nome;
    @NotBlank(message = ErrorsMessages.ErroCampoVazio)
    private String marca;
    @NotBlank(message = ErrorsMessages.ErroCampoVazio)
    private String descricao;
    // @NotBlank(message = ErrorsMessages.ErroCampoVazio)
    private String imageUri;
    @PositiveOrZero(message = ErrorsMessages.ErroNumeroPositivo)
    private float preco;
    private float avaliacao;
    @PositiveOrZero(message = ErrorsMessages.ErroNumeroPositivo)
    private int quantidade;
    private Date deleted;

}
