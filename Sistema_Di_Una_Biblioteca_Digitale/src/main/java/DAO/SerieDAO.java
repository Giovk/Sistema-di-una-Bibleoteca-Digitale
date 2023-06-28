package DAO;

import java.sql.ResultSet;

public interface SerieDAO {
    public ResultSet getSerieDB();
    public void chiudiConnessione();
}
