/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.juliac.OSApiApplication.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sesi3dia
 */
@Getter //Cria os gets
@Setter //Cria os sets 
@Entity //Vira tabela no banco
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/*Etqueta técnica que serve para o computador 
saber comparar se um comentário é igual a outro (os () dizem: só usa oq eu marcar com @Include 
para comparar)*/
public class Comentario {

    @Schema(description = "ID do comentário", example = "1")
    @EqualsAndHashCode.Include //diz que o ID define se é igual, para que se for o mesmo ID o comentário é o mesmo
    @Id //diz que esse campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ordem_servico_id")
    private OrdemServico ordemServico;

    @Schema(description = "Conteúdo do comentário", example = "Peça em falta, aguardando fornecedor", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank //não deixa o comentário ser salvo se estiver vazio ou só com espaços.
    private String descricao;

    @Schema(description = "Data em que o comentário foi feito", example = "2024-03-20T10:30:00")
    private LocalDateTime dataEnvio;
}
