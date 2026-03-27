/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.juliac.OSApiApplication.api.controller;

import br.com.juliac.OSApiApplication.domain.model.Cliente;
import br.com.juliac.OSApiApplication.domain.repository.ClienteRepository;
import br.com.juliac.OSApiApplication.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;

    /**
     * Endpoint clientes
     * Lista os clientes da base de dados
     * @return Lista Clientes.
     */
    
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista completa de clientes cadastrados")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Lista de clientes recuperada com sucesso")
    })
    @GetMapping("/clientes")
    public List<Cliente> listas() {

        return clienteRepository.findAll();
        //return clienteRepository.findByNome("KGe");
        //return clienteRepository.findByNomeContaining("Silva");
    }

    
    /**
     * Clientes por ID
     * @param clienteID
     * @return Cliente correspondente.
     */
    @Operation(summary = "Busca o cliente pelo ID", description = "Retorna detalhes de um cliente a partir do id")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> buscar(
            @PathVariable 
            @Parameter(name = "clienteID", description = "número de identificação(id) do cliente para pesquisa", example = "1")
            Long clienteID
    ) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
       
    }
    /**
     * 
     * @param cliente
     * @return 
     */
    @Operation(summary = "Adiciona um novo cliente", description = "Cria um novo registro de cliente")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados do cliente inválidos")
    })
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(
            @Parameter(description = "Representação de um novo cliente", required = true)
            @Valid 
            @RequestBody 
            Cliente cliente) {
        return clienteService.salvar(cliente);
    }
    
    /**
     * 
     * @param clienteID
     * @param cliente
     * @return 
     */
    @Operation(summary = "Atualiza um cliente", description = "Atualiza os dados de um cliente ja existente")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Cliente atualzado com sucesso"),  
        @ApiResponse(responseCode = "400", description = "Dados do cliente inválidos"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(
            //Etiqueta para o id que vai na URL
            @Parameter(name = "id", description = "ID do cliente", example= "1")
            @PathVariable Long clienteID,
            
            //Etiqueta para os dados que vai atualizar
            @Parameter(description = "Dados atualizados do cliente", required = true)
            @Valid @RequestBody Cliente cliente
    ) {
        //Verifica se o cliente existe
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        
        cliente.setId(clienteID);
        cliente = clienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }
    
    
    /**
     * 
     * @param clienteID
     * @return 
     */
    @Operation(summary="Deleta cliente", description="Deleta um cliente e seus dados")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "204", description="Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description="Cliente não encontrado")
    })
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(
            @Parameter(name="clienteID", description = "ID do cliente para exclusão", example = "1")
            @PathVariable Long clienteID) {
        //Verifica se cliente existe ou não
        
        if (!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }
        
        clienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
        
    }

    
    
}
