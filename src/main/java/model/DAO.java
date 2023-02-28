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
            System.err.println(e.getMessage());
        }
    }

    public static DAO getInstance() {
        if (daoInstance == null) {
            daoInstance = new DAO();
        }
        return daoInstance;
    }

    public boolean saveGame(int selectedInterval, int correctInterval) {
        try {
            long now = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(now);
            String query = "INSERT INTO Game (PlayedAt, SelectedInterval, CorrectInterval, UserID) " +
                    "VALUES ('"+timestamp+"', "+selectedInterval+", "+correctInterval+", 1)";
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            return true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

}
