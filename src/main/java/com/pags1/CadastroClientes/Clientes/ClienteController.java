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

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Primeira msg";
    }

    @PostMapping("/create")
    public ClienteModel createCliente(@RequestBody ClienteModel cliente){
        return clienteService.createCliente(cliente);
    }

    @GetMapping()
    public List<ClienteModel> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public ClienteModel findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @PutMapping("/edit")
    public String editarCliente(){
        return "Editado";
    }

    @DeleteMapping("/delete")
    public String deletarCliente(){
        return "Cliente deletado";
    }
}
