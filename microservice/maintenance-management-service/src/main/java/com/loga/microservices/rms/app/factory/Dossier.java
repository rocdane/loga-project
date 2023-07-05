package com.loga.microservices.rms.app.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dossier {
    private Long id;
    private String reference;
    private Date openAt;
    private Date updatedAt;
}
