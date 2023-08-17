package com.example.apifutbol.exception;

public class CompetitionNotFoundException extends Exception {
    public CompetitionNotFoundException() {
        super("La competicion no fue encontrado en la base de datos.");
    }
}
