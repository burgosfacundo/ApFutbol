package com.example.apifutbol.exception;

public class StadiumNotFoundException extends Exception {
    public StadiumNotFoundException() {
        super("El Estadio no fue encontrado en la base de datos.");
    }
}
