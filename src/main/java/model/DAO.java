package model;

import java.sql.*;
import java.util.ResourceBundle;

public class DAO {
    private final ResourceBundle reader;

    private static Connection connection;

    private static DAO daoInstance = null;

    private DAO() {
        reader = ResourceBundle.getBundle("dbconfig");
        String url = reader.getString("db.url");
        String username = reader.getString("db.username");
        String password = reader.getString("db.password");

        try {
            connection = DriverManager.getConnection(url + "?user=" + username + "&password=" + password);
        } catch (SQLException e) {
            do {
                System.err.println(e.getMessage());
                System.err.println(e.getErrorCode());
                System.err.println(e.getSQLState());
            } while (e.getNextException() != null);
        }
    }

    public static DAO getInstance() {
        if (daoInstance == null) {
            daoInstance = new DAO();
        }
        return daoInstance;
    }
    /*
    public boolean saveGame() {
        try {
            String query = "INSERT INTO  FROM User WHERE UserID = 1";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String username = rs.getString(1);
                System.out.println(username);
            }


        } catch (SQLException e) {
            do {
                System.err.println("Viesti: "+e.getMessage());
                System.err.println("Virhekoodi: "+e.getErrorCode());
                System.err.println("SQL-tilakoodi: "+e.getSQLState());
            } while (e.getNextException() != null);
        }
        return true;
    } */

}
