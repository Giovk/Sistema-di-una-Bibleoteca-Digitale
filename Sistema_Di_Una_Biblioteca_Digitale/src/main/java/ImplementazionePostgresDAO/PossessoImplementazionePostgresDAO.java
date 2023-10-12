package ImplementazionePostgresDAO;

import DAO.PossessoDAO;
import Database.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PossessoImplementazionePostgresDAO implements PossessoDAO {
    private Connection connection;

    public PossessoImplementazionePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    @Override
    public ResultSet getPossessoLLibreriaDB(String nome, String sitoweb, String indirizzo, String user){
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Libro AS l NATURAL JOIN possesso_l AS pl NATURAL JOIN Libreria AS lib WHERE lib.nome = '"+nome+"' ";
            if (sitoweb == null) query = query + "AND lib.sitoweb IS NULL ";
            else query = query + "AND lib.sitoweb = '"+sitoweb+"' ";

            if (indirizzo == null) query = query + "AND lib.indirizzo IS NULL ";
            else query = query + "AND lib.indirizzo = '"+indirizzo+"' ";

            query = query + "AND lib.gestore = '"+user+"'";
            PreparedStatement getPossessoLLibreriaPS = connection.prepareStatement(query);
            rs = getPossessoLLibreriaPS.executeQuery();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getPossessoSLibreriaDB(String nome, String sitoweb, String indirizzo, String user){
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Serie AS s NATURAL JOIN possesso_s AS ps NATURAL JOIN Libreria AS lib WHERE lib.nome = '"+nome+"' ";
            if (sitoweb == null) query = query + "AND lib.sitoweb IS NULL ";
            else query = query + "AND lib.sitoweb = '"+sitoweb+"' ";

            if (indirizzo == null) query = query + "AND lib.indirizzo IS NULL ";
            else query = query + "AND lib.indirizzo = '"+indirizzo+"' ";

            query = query + "AND lib.gestore = '"+user+"'";
            PreparedStatement getPossessoSLibreriaPS = connection.prepareStatement(query);
            rs = getPossessoSLibreriaPS.executeQuery();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public ResultSet getPossessoFLibreriaDB(String nome, String sitoweb, String indirizzo, String user){
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Rivista AS riv NATURAL JOIN Fascicolo AS f NATURAL JOIN possesso_f AS pf NATURAL JOIN Libreria AS lib WHERE lib.nome = '"+nome+"' ";
            if (sitoweb == null) query = query + "AND lib.sitoweb IS NULL ";
            else query = query + "AND lib.sitoweb = '"+sitoweb+"' ";

            if (indirizzo == null) query = query + "AND lib.indirizzo IS NULL ";
            else query = query + "AND lib.indirizzo = '"+indirizzo+"' ";

            query = query + "AND lib.gestore = '"+user+"'";
            PreparedStatement getPossessoFLibreriaPS = connection.prepareStatement(query);
            rs = getPossessoFLibreriaPS.executeQuery();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return rs;
    }

    @Override
    public void modQuantitaLibroDB(String isbn, String nt, String fruizione, int value){
        int codl = 0;

        ResultSet rs = null;

        try {
            PreparedStatement modQuantitaLibroPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = modQuantitaLibroPS.executeQuery();

            try {
                while(rs.next()) codl = rs.getInt("codl");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        try {
            PreparedStatement modQuantitaLibroPS = connection.prepareStatement(
                    "UPDATE possesso_l SET quantita = '"+value+"' WHERE codl = '"+codl+"' AND isbn = '"+isbn+"' AND fruizione = '"+fruizione+"'"
            );

            modQuantitaLibroPS.executeUpdate();
        } catch (SQLException var){
            var.printStackTrace();
        }

        chiudiConnessione();
    }

    @Override
    public void modQuantitaFascicoloDB(String issn, int numero ,String nt, String fruizione, int value){
        int codl = 0;
        int codf = 0;

        ResultSet rs = null;

        try {
            PreparedStatement modQuantitaFascicoloPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = modQuantitaFascicoloPS.executeQuery();

            try {
                while(rs.next()) codl = rs.getInt("codl");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        try {
            PreparedStatement modQuantitaFascicoloPS = connection.prepareStatement(
                    "SELECT codf FROM fascicolo WHERE numero = '"+numero+"' AND issn = '"+issn+"'"
            );

            rs = modQuantitaFascicoloPS.executeQuery();

            try {
                while(rs.next()) codf = rs.getInt("codf");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException var){
            var.printStackTrace();
        }

        try {
            PreparedStatement modQuantitaLibroPS = connection.prepareStatement(
                    "UPDATE possesso_f SET quantita = '"+value+"' WHERE codl = '"+codl+"' AND codf = '"+codf+"' AND fruizione = '"+fruizione+"'"
            );

            modQuantitaLibroPS.executeUpdate();
        } catch (SQLException var){
            var.printStackTrace();
        }

        chiudiConnessione();
    }

    @Override
    public boolean insertPossessoLDB(String isbn, String nt, int quantita, String fruizione){
        ResultSet rs = null;
        int codl = 0;
        try {
            PreparedStatement insertPossessoLPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = insertPossessoLPS.executeQuery();
            try {
                while (rs.next()){
                    try {
                        codl = rs.getInt("codl");
                        insertPossessoLPS = connection.prepareStatement(
                                "SELECT COUNT(*) AS contatore FROM possesso_l WHERE isbn = '"+isbn+"' AND codl = '"+codl+"' AND fruizione = '"+fruizione+"'"
                        );

                        rs = insertPossessoLPS.executeQuery();
                        try {
                            while (rs.next()){
                                if (rs.getInt("contatore") == 0){
                                    if (!fruizione.equals("Cartaceo")){
                                        try {
                                            insertPossessoLPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_l(codl, isbn, fruizione) " +
                                                            "VALUES ('"+codl+"', '"+isbn+"', '"+fruizione+"')"
                                            );

                                            insertPossessoLPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            insertPossessoLPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_l(codl, isbn, fruizione, quantita) " +
                                                            "VALUES ('"+codl+"', '"+isbn+"', '"+fruizione+"', '"+quantita+"')"
                                            );

                                            insertPossessoLPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    rs.close();
                                    chiudiConnessione();
                                    return false;
                                }
                            }
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return false;
    }

    @Override
    public boolean insertPossessoFDB(int numero, String issn, String nt, int quantita, String fruizione){
        ResultSet rs = null;
        int codl = 0;
        int codf = 0;
        try {
            PreparedStatement insertPossessoFPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = insertPossessoFPS.executeQuery();
            try {
                while (rs.next()){
                    try {
                        codl = rs.getInt("codl");
                        try{

                            insertPossessoFPS = connection.prepareStatement(
                                    "SELECT codf FROM fascicolo WHERE issn = '"+issn+"' AND numero = '"+numero+"'"
                            );

                            System.out.println(numero);

                            ResultSet rs2 = insertPossessoFPS.executeQuery();
                            System.out.println(issn);

                            try {
                                while(rs2.next()) {
                                    codf = rs2.getInt("codf");
                                    System.out.println(codf);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            rs2.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        insertPossessoFPS = connection.prepareStatement(
                                "SELECT COUNT(*) AS contatore FROM possesso_f WHERE codf = '"+codf+"' AND codl = '"+codl+"' AND fruizione = '"+fruizione+"'"
                        );

                        rs = insertPossessoFPS.executeQuery();
                        try {
                            while (rs.next()){
                                if (rs.getInt("contatore") == 0){
                                    if (!fruizione.equals("Cartaceo")){
                                        try {
                                            insertPossessoFPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_f(codl, codf, fruizione) " +
                                                            "VALUES ('"+codl+"', '"+codf+"', '"+fruizione+"')"
                                            );

                                            insertPossessoFPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            insertPossessoFPS = connection.prepareStatement(
                                                    "INSERT INTO possesso_f(codl, codf, fruizione, quantita) " +
                                                            "VALUES ('"+codl+"', '"+codf+"', '"+fruizione+"', '"+quantita+"')"
                                            );

                                            insertPossessoFPS.executeUpdate();
                                            rs.close();
                                            chiudiConnessione();
                                            return true;
                                        } catch (SQLException e){
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    rs.close();
                                    chiudiConnessione();
                                    return false;
                                }
                            }
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
        return false;
    }

    @Override
    public void eliminaPossessoLDB(String isbn, String nt, String fruizione){
        ResultSet rs = null;
        try {
            int codl = 0;
            PreparedStatement eliminaPossessoLPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = eliminaPossessoLPS.executeQuery();
            try {
                while (rs.next()) {
                    codl = rs.getInt("codl");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            eliminaPossessoLPS = connection.prepareStatement(
                    "DELETE FROM possesso_l WHERE codl = '"+codl+"' AND isbn = '"+isbn+"' AND fruizione = '"+fruizione+"'"
            );

            eliminaPossessoLPS.executeUpdate();
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
    }

    @Override
    public void eliminaPossessoFDB(String issn, int numero, String nt, String fruizione){
        ResultSet rs = null;
        try {
            int codl = 0;
            int codf = 0;
            PreparedStatement eliminaPossessoFPS = connection.prepareStatement(
                    "SELECT codl FROM libreria WHERE numerotelefonico = '"+nt+"'"
            );

            rs = eliminaPossessoFPS.executeQuery();
            try {
                while (rs.next()) {
                    codl = rs.getInt("codl");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            eliminaPossessoFPS = connection.prepareStatement(
                    "SELECT codf FROM fascicolo WHERE issn = '"+issn+"' AND numero = '"+numero+"'"
            );

            rs = eliminaPossessoFPS.executeQuery();
            try {
                while (rs.next()) {
                    codf = rs.getInt("codf");
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            eliminaPossessoFPS = connection.prepareStatement(
                    "DELETE FROM possesso_f WHERE codl = '"+codl+"' AND codf = '"+codf+"' AND fruizione = '"+fruizione+"'"
            );

            eliminaPossessoFPS.executeUpdate();
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        chiudiConnessione();
    }
    @Override
    public void chiudiConnessione(){    //chiude la connessione al DB
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
