package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    public Connection connection = null;
    private String nome = "postgres";
    private String password = "giovk";
    private String url = "jdbc:postgresql://localhost:5432/Biblioteca";
    private String driver = "org.postgresql.Driver";

    private ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(this.driver);
            this.connection = DriverManager.getConnection(this.url, this.nome, this.password);
        } catch (ClassNotFoundException var2) {
            System.out.println("Database Connection Creation Failed : " + var2.getMessage());
            var2.printStackTrace();
        }
    }

    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnessioneDatabase();
        } else if (instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }

        return instance;
    }
}
