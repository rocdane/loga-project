package com.loga.microservices.wms.app.api.mecef;

import java.util.List;

public class InvoiceRequestDataDto {
    String ifu;
    AibGroupTypeEnum aib;
    List<ItemDto> items;
    ClientDto client;
    OperatorDto operator;
    List<PaymentDto> payment;
    String reference;
}
