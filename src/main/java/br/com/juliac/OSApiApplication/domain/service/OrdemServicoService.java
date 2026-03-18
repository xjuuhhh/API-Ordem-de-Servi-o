/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.juliac.OSApiApplication.domain.service;

import br.com.juliac.OSApiApplication.domain.model.OrdemServico;
import br.com.juliac.OSApiApplication.domain.model.StatusOrdemServico;
import br.com.juliac.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sesi3dia
 */
@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

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

    public List<OrdemServico> listarPorCliente(Long clienteId) {
        return ordemServicoRepository.findByClienteId(clienteId);
    }
}
