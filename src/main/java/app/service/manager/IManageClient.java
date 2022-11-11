package app.service.manager;

import app.model.Client;

import java.util.List;

public interface IManageClient {
    Client createClient(ManageClientRequest request);
    List<Client> listClient();
    List<Client> listClient(String name);
    Client findClient(long id);
    Client findClient(String name);
    void editClient(ManageClientRequest request, Long id);
    void deleteClient(Long id);
}
