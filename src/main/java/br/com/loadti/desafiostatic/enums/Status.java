package br.com.loadti.desafiostatic.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum Status {
    INTERNAL_SERVER_ERROR(500),
    OK(200),
    NOT_FOUND(404);


    public int valorStatus;

}