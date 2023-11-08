package DAO;

import Model.ArticoloScientifico;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ArticoloScientificoDAO {
    public ResultSet getArticoliScientificiDB(String issn, int n);
    public boolean creaArticoloDB(String titolo, int anno, int numero, String issn);
    public String getDoiDB(String titolo);
    public int getAPDB(String doi);
    public void eliminaArticoloDB(String doi);
    public void chiudiConnessione(); //chiude la connessione al DB
}
