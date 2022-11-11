package app.service.repairer;

import app.dao.*;
import app.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
@AllArgsConstructor
public class RepairReparation implements IRepairReparation {
    private final RepairRepository repairRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Reparation
     * @param repair
     * @return Repair
     */
    @Override
    public Repair createRepair(RepairReparationRequest repair) {
        return repairRepository.save(new Repair());
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
    public void editRepair(RepairReparationRequest request, Long repair) {
        //TODO:update repair
    }

    @Override
    public void editRepair(Repair repair, Spare fourniture) {

    }

    @Override
    public void editRepair(Repair repair, Reparation reparation) {

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
    public void deleteRepair(Repair repair, Spare fourniture) {

    }

    @Override
    public void deleteRepair(Repair repair, Reparation reparation) {

    }

    @Override
    public List<Repair> listRepair(Date debut, Date fin) {
        return null;
    }
}
