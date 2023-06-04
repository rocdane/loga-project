package com.loga.intelligentservice.app.ontology.concept;

import jade.content.Concept;

public class Automobile implements Concept {
    private String vin;
    private String make;
    private String model;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
