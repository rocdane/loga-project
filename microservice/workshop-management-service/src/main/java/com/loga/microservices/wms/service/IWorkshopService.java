package com.loga.microservices.wms.service;

import com.loga.microservices.wms.entity.Workshop;
import com.loga.microservices.wms.entity.Department;
import com.loga.microservices.wms.entity.Position;

import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Direction
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IWorkshopService {

    /**
     * Regroupement des opérations de planification et ressources humaines
     * */
    Workshop createAtelier(Workshop workshop);
    Workshop readAtelier(Long atelier);
    List<Workshop> listAtelier();
    void editAtelier(Workshop workshop, Long id);
    void deleteAtelier(Long atelier);
    Department createDepartment(Department department);
    List<Department> listDepartment(Long id);
    Department readDepartment(Long department);
    Department readDepartment(String designation);
    void deleteDepartment(Long department);
    Position createPosition(Position position);
    List<Position> listPosition(Long department);
    Position readPosition(Long position);
    Position readPosition(String designation);
    void deletePosition(Long id);
}
