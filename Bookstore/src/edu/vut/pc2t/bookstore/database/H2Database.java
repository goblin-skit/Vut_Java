package edu.vut.pc2t.bookstore.database;

import edu.vut.pc2t.bookstore.model.Kniha;
import edu.vut.pc2t.bookstore.model.Roman;
import edu.vut.pc2t.bookstore.model.Ucebnice;

import java.sql.*;



public class H2Database {

    private static final String INSERT_KNIHA_SQL = "INSERT INTO KNIHY" +
            "  (id, druh, nazov, autor, rokVydania, dostupna, zaner, vhodnyRocnik) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String QUERY_ALL = "select id, druh, nazov, autor, rokVydania, dostupna, zaner, vhodnyRocnik from KNIHY";

    String jdbcURL = "jdbc:h2:./H2/Db";
    String username = "user";
    String password = "1234";

    Connection connection;

    public void connectToSQLDB() throws SQLException {

        connection = DriverManager.getConnection(jdbcURL, username, password);
        //System.out.println("Connected to H2 embedded database.");
    }

    public void disconnectFromSQLDB() throws SQLException {
        connection.close();
    }

    public void createTableKnihy() throws SQLException {

        final String createStatement = "CREATE TABLE IF NOT EXISTS KNIHY ( id INTEGER NOT NULL, druh VARCHAR(50) NOT NULL, nazov VARCHAR(50) NOT NULL, autor VARCHAR(50) NOT NULL, rokVydania INTEGER, dostupna BOOLEAN, zaner VARCHAR(50), vhodnyRocnik INTEGER);";
       
        Statement statement = connection.createStatement();
        statement.execute(createStatement);
    }

    public void dropTableKnihy() throws SQLException {

        String createStatement = "DROP TABLE KNIHY;";
        Statement statement = connection.createStatement();
        statement.execute(createStatement);
    }

    public void insert(Kniha kniha, int id) throws SQLException {

        /* INSERT_KNIHA_SQL = "INSERT INTO KNIHA" +
                "  (id, druh, nazov, autor, rokVydania, dostupna, zaner, vhodnyRocnik) VALUES " +
                " (?, ?, ?, ?, ?, ?, ?, ?);
         */

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KNIHA_SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, kniha.printDruhKnihy());
        preparedStatement.setString(3, kniha.getNazev());
        preparedStatement.setString(4, kniha.getAutor());
        preparedStatement.setInt(5, kniha.getRokVydani());
        preparedStatement.setBoolean(6, kniha.isJeDostupny());
        preparedStatement.setString(7, kniha.getZaner());
        preparedStatement.setInt(8, kniha.getVhodnyRocnik());

        // System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }

    public void writeAllToSQLDB(Databaze inMemoryDatabaze) throws SQLException {

        dropTableKnihy();
        createTableKnihy();

        int id = 0;
        for (Kniha kniha : inMemoryDatabaze.getVsetkyKnihy()) {
            insert(kniha, id);
            id++;
        }
        
        System.out.println("Writing to database...");
    }

    public void readAllToMemory(Databaze inMemoryDatabaze) throws SQLException {

        createTableKnihy();

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(QUERY_ALL);

        while (rs.next()) {

            int id = rs.getInt("id");
            String druh = rs.getString("druh");
            String nazev = rs.getString("nazov");
            String autor = rs.getString("autor");
            int rokVydania = rs.getInt("rokVydania");
            boolean dostupna = rs.getBoolean("dostupna");
            String zaner = rs.getString("zaner");
            int vhodnyRocnik = rs.getInt("vhodnyRocnik");

            if (druh.equalsIgnoreCase("Roman")) {
                Roman roman = new Roman();
                roman.setNazev(nazev);
                roman.setAutor(autor);
                roman.setRokVydani(rokVydania);
                roman.setJeDostupny(dostupna);
                roman.setZaner(zaner);

                inMemoryDatabaze.addKniha(roman);
            }

            if (druh.equalsIgnoreCase("Ucebnice")) {
                Ucebnice ucebnice = new Ucebnice();
                ucebnice.setNazev(nazev);
                ucebnice.setAutor(autor);
                ucebnice.setRokVydani(rokVydania);
                ucebnice.setJeDostupny(dostupna);
                ucebnice.setVhodnyRocnik(vhodnyRocnik);

                inMemoryDatabaze.addKniha(ucebnice);
            }
        }
        
        System.out.println("Reading from database...");
    }
}
