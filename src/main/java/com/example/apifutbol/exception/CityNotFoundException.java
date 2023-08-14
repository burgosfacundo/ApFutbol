package com.example.apifutbol.exception;

public class CityNotFoundException extends Exception{
    public CityNotFoundException() {
        super("La ciudad no fue encontrada en la base de datos.");
    }
}
