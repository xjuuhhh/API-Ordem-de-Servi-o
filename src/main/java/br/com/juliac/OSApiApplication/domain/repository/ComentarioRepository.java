/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.juliac.OSApiApplication.domain.repository;

import br.com.juliac.OSApiApplication.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sesi3dia
 */
@Repository
//<Comentario, Long> Isso conecta o repositório à classe 
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
