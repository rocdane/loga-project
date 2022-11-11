package app.service.manager;

import app.dao.AtelierRepository;
import app.dao.DepartmentRepository;
import app.dao.PositionRepository;
import app.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:application de l'injection de dépendance
 * Implémentation de l'interface IManage représentant les opérations du service Direction
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
@Service
@AllArgsConstructor
public class ManageGarage implements IManageGarage {

    private final AtelierRepository atelierRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un atelier dans la base de données.
     * @param request
     * @return Atelier
     */
    @Override
    public Atelier createAtelier(ManageGarageRequest request) {
        return atelierRepository.save(new Atelier());
    }

    /**
     * Cette méthode permet de sélectionner un Atelier de la base de données.
     * @param atelier
     * @return
     */
    @Override
    public Atelier readAtelier(Long atelier) {
        return atelierRepository.findById(atelier).get();
    }

    @Override
    public List<Atelier> listAtelier() {
        return atelierRepository.findAll();
    }

    @Override
    public void editAtelier(ManageGarageRequest request, Long atelier) {
        //todo : update atelier data
    }

    @Override
    public void deleteAtelier(Long atelier) {
        atelierRepository.deleteById(atelier);
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un service dans la base de données.
     * @param department
     * @return Department
     */
    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Cette méthode permet de sélectionner tous les services de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Department> listDepartment(Long atelier) {
        return departmentRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner un service de la base de données.
     * @param id
     * @return Service;
     */
    @Override
    public Department readDepartment(Long id) {
        return departmentRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de sélectionner un service de la base de données.
     * @param designation
     * @return Service;
     */
    @Override
    public Department readDepartment(String designation) {
        return departmentRepository.findByDesignation(designation);
    }

    /**
     * Cette méthode permet de supprimer un service de la base de données.
     * @param department
     */
    @Override
    public void deleteDepartment(Long department) {
        departmentRepository.deleteById(department);
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un titre dans la base de données.
     * @param position
     * @return Position
     */
    @Override
    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    /**
     * Cette méthode permet de sélectionner tous les titres de la base de données dans une collection.
     * @return Titre
     */
    @Override
    public List<Position> listPosition(Long atelier) {
        return positionRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner un titre de la base de données
     * @param position
     * @return Position
     */
    @Override
    public Position readPosition(Long position) {
        return positionRepository.findById(position).get();
    }

    /**
     * Cette méthode permet de sélectionner un titre de la base de données
     * @param atelier
     * @return Position
     */
    @Override
    public Position readPosition(String atelier) {
        return positionRepository.findByDesignation();
    }

    /**
     * Cette méthode permet de supprimer un titre de la base de données
     * @param position
     */
    @Override
    public void deletePosition(Long position) {
        positionRepository.deleteById(position);
    }
}
