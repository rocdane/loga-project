package app.service.manager;

import app.model.Calendar;
import app.model.Space;
import app.model.Evaluation;
import app.model.Profile;

import java.util.List;

public interface IManageResource {
    Space createSpace(Space space);
    List<Space> listSpace();
    Space findSpace(Long space);
    Space findSpace(String space);
    void deleteSpace(Long space);

    Profile createProfile(Profile profile);
    List<Profile> listProfile();
    List<Profile> listProfile(String profile);
    Profile findProfile(Long profile);
    Profile findProfile(String name);
    void editProfile(Long profile);
    void deleteProfile(Long profile);

    Evaluation createEvaluation(Evaluation evaluation);

    Calendar createCalendar(Calendar calendar);
    List<Calendar> listCalendar(Long atelier);
    Calendar findCalendar(Long id);
}
