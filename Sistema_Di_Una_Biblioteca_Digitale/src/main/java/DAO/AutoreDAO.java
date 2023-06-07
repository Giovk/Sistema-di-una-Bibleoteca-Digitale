package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface AutoreDAO {
    public ResultSet getAutoriLibroDB();
    public void chiudiConnessione();
}
