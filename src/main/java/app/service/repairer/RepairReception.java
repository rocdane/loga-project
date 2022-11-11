package app.service.repairer;

import app.dao.ReceptionRepository;
import app.model.Notice;
import app.model.Reception;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RepairReception implements IRepairReception{
    private final ReceptionRepository receptionRepository;
    /**
     * TODO:Cette méthode permet d'enregistrer un objet Reception dans la base de données.
     * @param request
     * @return Reception
     * @see RepairReceptionRequest,Notice
     * */
    @Override
    public Reception createReception(RepairReceptionRequest request) {
        Reception reception = new Reception();
        reception.setCommentaire(request.getCommentaire());
        reception.setDossier(request.getDossier());
        reception.setObservation(request.getObservation());
        reception.setNotices(request.getNotices());
        reception.setCompteur(reception.getCompteur());
        reception.setProfile(request.getProfile());
        return receptionRepository.save(reception);
    }

    @Override
    public Reception readReception(long receptionID) {
        return null;
    }
}
