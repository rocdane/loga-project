package com.loga.microservices.wms.service;

import com.loga.microservices.wms.entity.Workshop;
import com.loga.microservices.wms.entity.Department;
import com.loga.microservices.wms.entity.Position;
import com.loga.microservices.wms.repository.WorkshopRepository;
import com.loga.microservices.wms.repository.DepartmentRepository;
import com.loga.microservices.wms.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO:application de l'injection de dépendance
 * Implémentation de l'interface IManage représentant les opérations du service Direction
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
@Service
public class WorkshopService implements IWorkshopService {

    private final WorkshopRepository workshopRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Autowired
    public WorkshopService(WorkshopRepository workshopRepository, DepartmentRepository departmentRepository, PositionRepository positionRepository) {
        this.workshopRepository = workshopRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un workshop dans la base de données.
     * @param workshop
     * @return Atelier
     */
    @Override
    @Transactional
    public Workshop createAtelier(Workshop workshop) {
        return workshopRepository.save(new Workshop());
    }

    /**
     * Cette méthode permet de sélectionner un Atelier de la base de données.
     * @param workshop
     * @return
     */
    @Override
    public Workshop readAtelier(Long workshop) {
        return workshopRepository.findById(workshop).get();
    }

    @Override
    public List<Workshop> listAtelier() {
        return workshopRepository.findAll();
    }

    @Override
    @Transactional
    public void editAtelier(Workshop workshop, Long id) {
        workshopRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setAddress(workshop.getAddress());
                    up.setContact(workshop.getContact());
                    up.setName(workshop.getName());
                    up.setIfu(workshop.getIfu());
                    workshopRepository.saveAndFlush(up);
                });

    }

    @Override
    public void deleteAtelier(Long workshop) {
        workshopRepository.deleteById(workshop);
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
    public List<Department> listDepartment(Long workshop) {
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
    public List<Position> listPosition(Long workshop) {
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
     * @param workshop
     * @return Position
     */
    @Override
    public Position readPosition(String workshop) {
        return positionRepository.findByDesignation(workshop);
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
