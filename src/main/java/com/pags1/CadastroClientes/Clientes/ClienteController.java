package com.pags1.CadastroClientes.Clientes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ClienteController {

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Primeira msg";
    }
}
