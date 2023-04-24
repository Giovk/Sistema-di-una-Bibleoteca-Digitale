package org.example;

import ImplementazionePostgresDAO.LibroImplementazionePostgresDAO;

public class Main {
    public static void main(String[] args){
        LibroImplementazionePostgresDAO l = new LibroImplementazionePostgresDAO();
        l.printLibri();
    }
}