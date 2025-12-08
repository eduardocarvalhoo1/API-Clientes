package com.pags1.CadastroClientes.Clientes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    @Operation(summary = "Cria um novo cliente",
            description = "Cria um novo cliente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> createCliente(@RequestBody ClienteDTO cliente){
        ClienteDTO clienteDTO = clienteService.createCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente criado com sucesso!");
    }

    @GetMapping()
    @Operation(summary = "Retorna todos os clientes",
            description = "Busca todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Clientes encontrados com sucesso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<ClienteDTO> clientes = clienteService.findAll();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um cliente especifico por ID",
            description = "Busca um cliente pelo seu identificador único")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> findById(@PathVariable Long id){

        ClienteDTO cliente = clienteService.findById(id);

        if (cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de ID " + id + " não encontrado");
        }

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um cliente",
            description = "Atualiza os dados de um cliente existente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        if (clienteService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de ID " + id + " não encontrado");
        }

        cliente = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um cliente",
            description = "Deleta um cliente com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {

        if (clienteService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de ID " + id + " não encontrado");
        }

        clienteService.deleteCliente(id);
        return ResponseEntity.ok("Cliente de ID " + id + " deletado com sucesso");
    }
}
