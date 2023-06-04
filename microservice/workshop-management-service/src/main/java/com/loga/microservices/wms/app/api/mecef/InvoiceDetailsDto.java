package com.loga.microservices.wms.app.api.mecef;

import java.util.List;

public class InvoiceDetailsDto {
    String ifu;
    AibGroupTypeEnum aib;
    InvoiceTypeEnum type;
    List<ItemDto> items;
    ClientDto client;
    OperatorDto operator;
    List<PaymentDto> payment;
    String reference;
    String errorCode;
    String errorDesc;
}
