package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    private static ResultSet loadUserGameData() {
        try {
            String query = "SELECT * FROM Game WHERE UserID = 1";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static void analyzeAndSetUserGameData() {
        ResultSet rs = loadUserGameData();
        List<Game> playedGames = null;
        try {
            playedGames = new ArrayList<>(rs.getMetaData().getColumnCount());
            while (rs.next()) {
                Game game = new Game();
                game.setUserID(rs.getInt("UserID"));
                game.setSelectedInterval(rs.getInt("SelectedInterval"));
                game.setCorrectInterval(rs.getInt("CorrectInterval"));
                game.setPlayedAt(rs.getTimestamp("playedAt"));
                playedGames.add(game);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        int totalCount = DataAnalyzer.findClickCount(playedGames);
        int correctCount = DataAnalyzer.findCorrectCount(playedGames);
        int biggestFlaw = DataAnalyzer.findBiggestFlaw(playedGames);

        User.setUserData(totalCount, correctCount, biggestFlaw);
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
