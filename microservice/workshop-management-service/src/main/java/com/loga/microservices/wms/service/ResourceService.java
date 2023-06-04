package com.loga.microservices.wms.service;

import com.loga.microservices.wms.entity.Schedule;
import com.loga.microservices.wms.entity.Evaluation;
import com.loga.microservices.wms.entity.Employee;
import com.loga.microservices.wms.entity.Space;
import com.loga.microservices.wms.repository.CalendarRepository;
import com.loga.microservices.wms.repository.EvaluationRepository;
import com.loga.microservices.wms.repository.EmployeeRepository;
import com.loga.microservices.wms.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceService implements IResourceService {

    private final CalendarRepository calendarRepository;
    private final SpaceRepository spaceRepository;
    private final EmployeeRepository employeeRepository;
    private final EvaluationRepository evaluationRepository;

    @Autowired
    public ResourceService(CalendarRepository calendarRepository,
                           SpaceRepository spaceRepository,
                           EmployeeRepository employeeRepository,
                           EvaluationRepository evaluationRepository) {
        this.calendarRepository = calendarRepository;
        this.spaceRepository = spaceRepository;
        this.employeeRepository = employeeRepository;
        this.evaluationRepository = evaluationRepository;
    }

    /**
     * TODO : Cette méthode permet d'enregistrer un nouveau calendrier dans la base de données. Elle retourne l'objet ainsi créé.
     * @param schedule
     * @return CalendrierFourniture
     * */
    @Override
    @Transactional
    public Schedule createCalendar(Schedule schedule) {
        return calendarRepository.save(schedule);
    }

    /**
     *
     * TODO : Cette méthode permet de sélectionner et de retourner tous les calendriers de la base de données dans une collection.
     * @param atelier
     *  @return List
     * */
    @Override
    public List<Schedule> listCalendar(Long atelier) {
        return calendarRepository.findAll();
    }

    /**
     * TODO:cette méthode permet de sélectionner un calendrier de la base de données à partir de son identifiant
     * @param id
     * @return Calendrier
     * */
    @Override
    public Schedule findCalendar(Long id) {
        return calendarRepository.findById(id).get();
    }

    /**
     * Cette méthode permet d'enregistrer ou de mettre à jour un titre de la base de données.
     * @param space
     * @return Espace
     */
    @Override
    @Transactional
    public Space createSpace(Space space) {
        return spaceRepository.save(space);
    }

    /**
     * Cette méthode permet de sélectionner tous les Espace de la bases de données dans une collection.
     * @return List
     */
    @Override
    public List<Space> listSpace() {
        return spaceRepository.findAll();
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
        return spaceRepository.findByName(name);
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
     * @param employee
     * @return Profile
     */
    @Override
    @Transactional
    public Employee createProfile(Employee employee) {
        if(employeeRepository.findByContact(employee.getContact())!=null)
            return null;
        else
            return employeeRepository.save(employee);
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Profile de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Employee> listProfile() {
        return employeeRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Profile de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Employee> listProfile(String name) {
        return employeeRepository.findByNameOrSurname(name,name);
    }

    /**
     * Cette méthode permet de sélectionner un objet Profile de la base de données.
     * @param id
     * @return Profile
     */
    @Override
    public Employee findProfile(Long id) {
        return employeeRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de mettre à jour un objet Profile.
     * @param id
     */
    @Override
    @Transactional
    public void editProfile(Employee employee, Long id) {
        employeeRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setName(employee.getName());
                    up.setSurname(employee.getSurname());
                    up.setBirthdate(employee.getBirthdate());
                    up.setAddress(employee.getAddress());
                    up.setContact(employee.getContact());
                    employeeRepository.saveAndFlush(up);
                });
    }

    /**
     * Cette méthode permet de supprimer un objet Profile de la base de données.
     * @param id
     */
    @Override
    public void deleteProfile(Long id) {
        employeeRepository
                .findById(id) // returns Optional<User>
                .ifPresent(up -> {
                    up.setArchived(true);
                    employeeRepository.saveAndFlush(up);
                });
    }

    /**
     * Cette méthode permet d'enregistrer un objet Evaluation dans la base de données.
     * @param evaluation
     * @return Evaluation
     */
    @Override
    @Transactional
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

}
