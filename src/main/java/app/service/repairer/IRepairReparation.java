package app.service.repairer;

import app.model.Repair;
import app.model.Reparation;
import app.model.Spare;

import java.util.Date;
import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Atelier.
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IRepairReparation {
    Repair createRepair(RepairReparationRequest request);
    List<Repair> listRepair();
    List<Repair> listRepair(String reference);
    List<Repair> listRepair(Date debut, Date fin);
    Repair findRepair(Long repair);
    void editRepair(RepairReparationRequest request, Long repair);
    void editRepair(Repair repair, Spare fourniture);
    void editRepair(Repair repair, Reparation reparation);
    void deleteRepair(Long repair);
    void deleteRepair(Repair repair, Spare fourniture);
    void deleteRepair(Repair repair, Reparation reparation);
}
