/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.juliac.OSApiApplication.domain.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO Auxiliar para receber a descrição do comentario;
 * @author sesi3dia
 */
//serve para o usuário não ter que enviar data ou ID, apenas o texto
public record ComentarioInput(@NotBlank String descricao) {}
