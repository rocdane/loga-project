package com.loga.microservices.rms.service;


import com.loga.microservices.rms.entity.Reception;

public interface IReceptionService {

    Reception create(Reception reception);

    Reception read(Long id);
}
