package com.pags1.CadastroClientes.Clientes;

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
    public ClienteDTO createCliente(@RequestBody ClienteDTO cliente){
        return clienteService.createCliente(cliente);
    }

    @GetMapping()
    public List<ClienteDTO> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @PutMapping("/{id}")
    public ClienteDTO updateCliente(@PathVariable Long id, @RequestBody ClienteDTO cliente){
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
    }
}
