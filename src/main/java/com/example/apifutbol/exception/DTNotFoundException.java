package com.example.apifutbol.exception;

public class DTNotFoundException extends Exception {
    public DTNotFoundException() {
        super("El DT no fue encontrado en la base de datos.");
    }
}

