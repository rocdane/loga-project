package com.loga.microservices.rms.service;

import com.loga.microservices.rms.entity.Repair;
import com.loga.microservices.rms.entity.Spare;
import com.loga.microservices.rms.entity.Task;
import com.loga.microservices.rms.repository.RepairRepository;
import com.loga.microservices.rms.repository.SpareRepository;
import com.loga.microservices.rms.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * TODO:application de l'injection de dépendance
 * Implémentation de l'interface IRepair représentant les opérations du service Atelier.
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
@Service
public class RepairService implements IRepairService {

    private final RepairRepository repairRepository;
    private final TaskRepository taskRepository;
    private final SpareRepository spareRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository, TaskRepository taskRepository, SpareRepository spareRepository){
        this.repairRepository = repairRepository;
        this.taskRepository = taskRepository;
        this.spareRepository = spareRepository;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Reparation
     * @param repair
     * @return Repair
     */
    @Override
    @Transactional
    public Repair createRepair(Repair repair) {

        Calendar.getInstance().getTime();
        repair.setCreatedAt(new Date());

        return repairRepository.save(repair);
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Reparation dans une collection
     * @return List
     */
    @Override
    public List<Repair> listRepair() {
        return repairRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Reparation dans une collection
     * @return List
     */
    @Override
    public List<Repair> listRepair(String reference) {
        return repairRepository.findByReferenceContaining(reference);
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Reparation dans la base de données.
     * @param id
     * @return Repair
     */
    @Override
    public Repair findRepair(Long id) {
        return repairRepository.findById(id).get();
    }

    /**
     * TODO:Cette méthode permet de mettre à jour un objet Reparation dans la base de données.
     * @param repair
     */
    @Override
    @Transactional
    public void editRepair(Repair repair, Long id) {
        repairRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setReference(repair.getReference());
                    up.setDescription(repair.getDescription());
                    up.setMileage(repair.getMileage());
                    up.setIsApproved(repair.getIsApproved());
                    repairRepository.saveAndFlush(up);
                });
    }

    @Override
    public void orderRepair(Long id) {
        repairRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setIsApproved(true);
                    repairRepository.saveAndFlush(up);
                });
    }

    @Override
    public void editTask(Task task, Long id) {
        taskRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setDescription(task.getDescription());
                    up.setCost(task.getCost());
                    up.setDuration(task.getDuration());
                    taskRepository.saveAndFlush(up);
                });
    }

    @Override
    public void editSpare(Spare spare, Long id) {
        spareRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setDesignation(spare.getDesignation());
                    up.setAmount(spare.getAmount());
                    up.setQuantity(spare.getQuantity());
                    up.setPrice(spare.getPrice());
                    spareRepository.saveAndFlush(up);
                });
    }

    /**
     * TODO:Cette méthode permet de supprimer un objet Reparation de la base de données.
     * @param repair
     */
    @Override
    public void deleteRepair(Long repair) {
        repairRepository.deleteById(repair);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteSpare(Long id) {
        spareRepository.deleteById(id);
    }

    @Override
    public List<Repair> listRepair(Date debut, Date fin) {
        return repairRepository.findAllByCreatedAtBetween(debut,fin);
    }
}
