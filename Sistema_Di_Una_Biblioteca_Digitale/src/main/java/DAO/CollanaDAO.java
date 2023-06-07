package DAO;

import java.sql.ResultSet;

public interface CollanaDAO {
    public ResultSet getCollanaDB();
    public void chiudiConnessione();
}
