package org.example;

import GUI.GuiTry;
import GUI.LSForm;
import ImplementazionePostgresDAO.LibroImplementazionePostgresDAO;

public class Main {
    public static void main(String[] args){
        LibroImplementazionePostgresDAO l = new LibroImplementazionePostgresDAO();
        l.printLibri();
        new GuiTry();
    }
}