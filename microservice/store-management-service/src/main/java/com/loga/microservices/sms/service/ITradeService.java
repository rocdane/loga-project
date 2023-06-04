package com.loga.microservices.sms.service;


import com.loga.microservices.sms.entity.Sale;

import java.util.Date;
import java.util.List;

public interface ITradeService {
    Sale create(Sale sale);
    List<Sale> list();
    List<Sale> list(Date date);
    List<Sale> list(Date debut, Date fin);
    Sale read(Long id);
}
