package DAO;

import Model.ArticoloScientifico;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ArticoloScientificoDAO {
    public ResultSet getArticoliScientificiDB(String issn, int n);
    public void chiudiConnessione(); //chiude la connessione al DB
}
