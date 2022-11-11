package app.service.manager;

import app.model.Automobile;

import java.util.List;

public interface IManageAutomobile {
    Automobile createAutomobile(ManageAutomobileRequest request);
    List<Automobile> listAutomobile();
    List<Automobile> listAutomobile(Long client);
    List<Automobile> listAutomobile(String immatriculation);
    Automobile findAutomobile(Long id);
    Automobile findAutomobile(String immatriculation);
    void editAutomobile(ManageAutomobileRequest request, Long id);
    void deleteAutomobile(Long id);
}
