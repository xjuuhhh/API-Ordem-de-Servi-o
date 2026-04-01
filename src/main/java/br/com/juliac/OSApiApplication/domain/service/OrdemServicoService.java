/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.juliac.OSApiApplication.domain.service;

import br.com.juliac.OSApiApplication.domain.exception.DomainException;
import br.com.juliac.OSApiApplication.domain.model.Comentario;
import br.com.juliac.OSApiApplication.domain.model.OrdemServico;
import br.com.juliac.OSApiApplication.domain.model.StatusOrdemServico;
import br.com.juliac.OSApiApplication.domain.repository.ComentarioRepository;
import br.com.juliac.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sesi3dia
 */

@Service//avisa a spring que essa classe tem regras de negócio
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status) {

    Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);

    if (optOrdemServico.isPresent()) {

        OrdemServico ordemServico = optOrdemServico.get();

        // Verifica se ordem está ABERTA.
        if (ordemServico.getStatus() == StatusOrdemServico.ABERTA
                && status != StatusOrdemServico.ABERTA) {

            ordemServico.setStatus(status);
            ordemServico.setDataFinalizacao(LocalDateTime.now());
            ordemServicoRepository.save(ordemServico);
            return Optional.of(ordemServico);

        } else {
            // ops.. ordem FINALIZADA ou CANCELADA. Não alterar.
            return Optional.empty();
        }

    } else {
        // Lança exception se ID não encontrado.
        throw new DomainException("Não existe OS com o id " + ordemServicoID);
    }
}

    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico atualizar(Long id, OrdemServico novaOrdem) {
        OrdemServico ordem = ordemServicoRepository.findById(id).orElse(null);
        if (ordem != null) {
            ordem.setDescricao(novaOrdem.getDescricao());
            ordem.setPreco(novaOrdem.getPreco());
            return ordemServicoRepository.save(ordem);
        }
        return null;
    }

    public void remover(Long id) {
        ordemServicoRepository.deleteById(id);
    }

    public OrdemServico buscar(Long id) {
        return ordemServicoRepository.findById(id).orElse(null);
    }

    /**
     * Lista OS por Cliente
     * @param clienteId
     * @return 
     */
    public List<OrdemServico> listarPorCliente(Long clienteId) {
        return ordemServicoRepository.findByClienteId(clienteId);
    }
    
    
    /**
     * Adiciona comentario a Ordem de Serviço.
     * @param ordemServicoId
     * @param descricao
     * @return 
     */
    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscar(ordemServicoId); //busca a ordem de serviço que o usuario fonece
        
        if (ordemServico == null) {
            throw new DomainException("Ordem de serviço não encontrada");
        } else {
            Comentario comentario = new Comentario();
            //salvar no banco de dados
            comentario.setDescricao(descricao);
            comentario.setDataEnvio(LocalDateTime.now());
            comentario.setOrdemServico(ordemServico);
            
            return comentarioRepository.save(comentario);
        }
    }
}
