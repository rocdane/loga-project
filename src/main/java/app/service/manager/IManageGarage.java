package app.service.manager;

import app.model.*;

import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Direction
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IManageGarage {

    /**
     * Regroupement des opérations de planification et ressources humaines
     * */
    Atelier createAtelier(ManageGarageRequest request);
    Atelier readAtelier(Long atelier);
    List<Atelier> listAtelier();
    void editAtelier(ManageGarageRequest request, Long atelier);
    void deleteAtelier(Long atelier);
    Department createDepartment(Department department);
    List<Department> listDepartment(Long atelier);
    Department readDepartment(Long department);
    Department readDepartment(String designation);
    void deleteDepartment(Long department);
    Position createPosition(Position position);
    List<Position> listPosition(Long department);
    Position readPosition(Long position);
    Position readPosition(String designation);
    void deletePosition(Long id);
}
