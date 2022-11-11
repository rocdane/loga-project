package app.service.repairer;

import app.dao.QualityRepository;
import app.model.Quality;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RepairQuality implements IRepairQuality{

    private final QualityRepository qualityRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Qualite dans la base de données.
     * @param quality
     * @return Quality
     */
    @Override
    public Quality createQuality(Quality quality) {
        return qualityRepository.save(quality);
    }
}
