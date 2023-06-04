package com.loga.microservices.rms.service;

import com.loga.microservices.rms.entity.Notice;
import com.loga.microservices.rms.entity.Reception;
import com.loga.microservices.rms.repository.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ReceptionService implements IReceptionService {

    @Autowired
    private ReceptionRepository receptionRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Reception dans la base de données.
     * @param reception
     * @return Reception
     * @see Reception , Notice
     * */
    @Override
    @Transactional
    public Reception create(Reception reception) {
        reception.setCreatedAt(new Date());
        if(receptionRepository.findByReference(reception.getReference())!=null)
            return null;
        else
            return receptionRepository.save(reception);
    }

    @Override
    public Reception read(Long id) {
        return receptionRepository.findById(id).get();
    }
}
