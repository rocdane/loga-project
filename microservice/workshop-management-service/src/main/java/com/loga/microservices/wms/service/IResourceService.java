package com.loga.microservices.wms.service;

import com.loga.microservices.wms.entity.Schedule;
import com.loga.microservices.wms.entity.Evaluation;
import com.loga.microservices.wms.entity.Employee;
import com.loga.microservices.wms.entity.Space;

import java.util.List;

public interface IResourceService {

    Space createSpace(Space space);
    List<Space> listSpace();
    Space findSpace(Long space);
    Space findSpace(String space);
    void deleteSpace(Long space);

    Employee createProfile(Employee employee);
    List<Employee> listProfile();
    List<Employee> listProfile(String name);
    Employee findProfile(Long id);
    void editProfile(Employee employee, Long id);
    void deleteProfile(Long id);

    Evaluation createEvaluation(Evaluation evaluation);

    Schedule createCalendar(Schedule schedule);
    List<Schedule> listCalendar(Long atelier);
    Schedule findCalendar(Long id);
}
