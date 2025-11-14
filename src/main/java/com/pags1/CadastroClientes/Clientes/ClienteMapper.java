package com.pags1.CadastroClientes.Clientes;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteModel map(ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();

        clienteModel.setId(clienteDTO.id());
        clienteModel.setNome(clienteDTO.nome());
        clienteModel.setEmail(clienteDTO.email());

        return clienteModel;
    }

    public ClienteDTO map(ClienteModel clienteModel) {
        return new ClienteDTO(
                clienteModel.getId(),
                clienteModel.getNome(),
                clienteModel.getEmail()
        );
    }
}
