package DAO;

import java.sql.ResultSet;

public interface PresentazioneDAO {
    public ResultSet getPresentazioneDB(String isbn);
    public void chiudiConnessione();
}
