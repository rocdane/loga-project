package com.loga.microservices.wms.app.api.mecef;

public class ItemDto {
    String code;
    String name;
    Integer price;
    Float quantity;
    TaxGroupEnum taxGroup;
    Integer taxSpecific;
    Integer originalPrice;
    String priceModification;
}
