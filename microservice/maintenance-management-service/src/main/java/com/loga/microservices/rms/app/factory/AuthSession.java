package com.loga.microservices.rms.app.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthSession implements Serializable
{
    private String host, token;
    private Boolean closed;
    private Date createdAt;
    private Date closedAt;
}
