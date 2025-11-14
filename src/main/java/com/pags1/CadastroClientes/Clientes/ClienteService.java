package com.pags1.CadastroClientes.Clientes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> findAll() {
        List<ClienteModel> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(clienteMapper::map)
                .collect(Collectors.toList());
    }

    public ClienteDTO findById(Long id) {
        Optional<ClienteModel> clienteById = clienteRepository.findById(id);
        return clienteById.map(clienteMapper::map).orElse(null);
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        ClienteModel cliente = clienteMapper.map(clienteDTO);
        cliente = clienteRepository.save(cliente);

        return clienteMapper.map(cliente);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Optional<ClienteModel> clienteExists = clienteRepository.findById(id);

        if (clienteExists.isPresent()){
            ClienteModel cliente = clienteMapper.map(clienteDTO);
            cliente.setId(id);
            ClienteModel clienteUpdate = clienteRepository.save(cliente);

            return clienteMapper.map(clienteUpdate);
        }
        return null;
    }

    public void deleteCliente (Long id) {
        clienteRepository.deleteById(id);
    }
}
