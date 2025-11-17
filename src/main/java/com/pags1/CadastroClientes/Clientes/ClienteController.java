package com.pags1.CadastroClientes.Clientes;

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
    public ResponseEntity<String> createCliente(@RequestBody ClienteDTO cliente){
        ClienteDTO clienteDTO = clienteService.createCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente criado com sucesso!");
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<ClienteDTO> clientes = clienteService.findAll();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        ClienteDTO cliente = clienteService.findById(id);

        if (cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de ID " + id + " não encontrado");
        }

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        if (clienteService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de ID " + id + " não encontrado");
        }

        cliente = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {

        if (clienteService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente de ID " + id + " não encontrado");
        }

        clienteService.deleteCliente(id);
        return ResponseEntity.ok("Cliente de ID " + id + " deletado com sucesso");
    }
}
