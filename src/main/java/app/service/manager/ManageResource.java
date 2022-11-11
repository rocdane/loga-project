package app.service.manager;

import app.dao.*;
import app.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManageResource implements IManageResource{
    private final CalendarRepository calendarRepository;
    private final SpaceRepository spaceRepository;
    private final ProfileRepository profileRepository;
    private final EvaluationRepository evaluationRepository;

    /**
     * TODO : Cette méthode permet d'enregistrer un nouveau calendrier dans la base de données. Elle retourne l'objet ainsi créé.
     * @param calendar
     * @return CalendrierFourniture
     * */
    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    /**
     *
     * TODO : Cette méthode permet de sélectionner et de retourner tous les calendriers de la base de données dans une collection.
     * @param atelier
     *  @return List
     * */
    @Override
    public List<Calendar> listCalendar(Long atelier) {
        return (List<Calendar>) calendarRepository.findAll();
    }

    /**
     * TODO:cette méthode permet de sélectionner un calendrier de la base de données à partir de son identifiant
     * @param id
     * @return Calendrier
     * */
    @Override
    public Calendar findCalendar(Long id) {
        return calendarRepository.findById(id).get();
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un titre de la base de données.
     * @param space
     * @return Espace
     */
    @Override
    public Space createSpace(Space space) {
        return spaceRepository.save(space);
    }

    /**
     * Cette méthode permet de sélectionner tous les Espace de la bases de données dans une collection.
     * @return List
     */
    @Override
    public List<Space> listSpace() {
        return (List<Space>) spaceRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner un Espace de la base de données.
     * @param id
     * @return Espace
     */
    @Override
    public Space findSpace(Long id) {
        return spaceRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de sélectionner un Espace de la base de données.
     * @param name
     * @return Space
     */
    @Override
    public Space findSpace(String name) {
        return spaceRepository.findByName();
    }

    /**
     * Cette méthode permet de supprimer un Espace de la base de données
     * @param space
     */
    @Override
    public void deleteSpace(Long space) {
        spaceRepository.deleteById(space);
    }

    /**
     * Cette méthode permet d'enregistrer un objet Profile dans la base de données.
     * @param profile
     * @return Profile
     */
    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Profile de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Profile> listProfile() {
        return (List<Profile>) profileRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Profile de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Profile> listProfile(String name) {
        return profileRepository.findByNameOrSurname(name);
    }

    /**
     * Cette méthode permet de sélectionner un objet Profile de la base de données.
     * @param id
     * @return Profile
     */
    @Override
    public Profile findProfile(Long id) {
        return profileRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de sélectionner un objet Profile de la base de données.
     * @param user
     * @return Profile
     */
    @Override
    public Profile findProfile(String user) {
        return profileRepository.findByUsername(user);
    }

    /**
     * Cette méthode permet de mettre à jour un objet Profile.
     * @param profile
     */
    @Override
    public void editProfile(Long profile) {
        //TODO:profileRepository.saveAndFlush(profile);
    }

    /**
     * Cette méthode permet de supprimer un objet Profile de la base de données.
     * @param profile
     */
    @Override
    public void deleteProfile(Long profile) {
        profileRepository.deleteById(profile);
    }

    /**
     * Cette méthode permet d'enregistrer un objet Evaluation dans la base de données.
     * @param evaluation
     * @return Evaluation
     */
    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

}
