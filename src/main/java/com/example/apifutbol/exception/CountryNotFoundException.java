package com.example.apifutbol.exception;

public class CountryNotFoundException extends Exception {
    public CountryNotFoundException() {
        super("El pais no fue encontrado en la base de datos.");
    }
}
