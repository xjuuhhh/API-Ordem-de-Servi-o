/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.juliac.OSApiApplication.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Aluno                                                                                    
 */


@Entity        
public class Cliente {
    
    @Schema(description = "Código identificador do cliente", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Schema(description = "Nome completo do cliente", example = "João da Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 60)
    private String nome;
    
    @Schema(description = "E-mail de contato", example = "joao@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    
    @Schema(description = "Telefone para contato", example = "11 98888-7777", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 20)
    @Column(name = "telefone")
    private String fone;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.id == other.id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Cliente(long id, String nome, String email, String fone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    public Cliente() {
         // Construtor Default
    }
    
    
    }
    
    
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          

