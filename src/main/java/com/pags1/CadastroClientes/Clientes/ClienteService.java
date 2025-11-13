package com.pags1.CadastroClientes.Clientes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> findAll() {
        return clienteRepository.findAll();
    }

    public ClienteModel findById(Long id) {
        Optional<ClienteModel> clienteById = clienteRepository.findById(id);
        return clienteById.orElse(null);
    }

    public ClienteModel createCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }
}
