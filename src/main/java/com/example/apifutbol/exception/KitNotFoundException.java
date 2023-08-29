package com.example.apifutbol.exception;

public class KitNotFoundException extends Exception {
    public KitNotFoundException() {
        super("El Kit no fue encontrado en la base de datos.");
    }
}
