package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class that serves as a link between database and the application.
 *
 * @author Jukka Hallikainen
 */
public class DAO {
    private final ResourceBundle reader;
    private static Connection connection;
    private static DAO daoInstance = null;

    /**
     * Constructor that creates a connection to the database.
     */
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

    /**
     * Singleton model of constructor.
     * @return instance of DAO
     */
    public static DAO getInstance() {
        if (daoInstance == null) {
            daoInstance = new DAO();
        }
        return daoInstance;
    }

    /**
     * Loads all user game data from the database and builds an ArrayList from the data.
     * Calls for methods of DataAnalyzer class to find relevant key figures that are set as User class variables.
     */
    public static void loadUserGameData(TimePeriod timePeriod) {
        List<Game> playedGames = null;

        String query = switch (timePeriod) {
            case All -> "SELECT * FROM Game WHERE UserID = 1";
            case Hour -> "SELECT * FROM Game WHERE UserID = 1 AND DATE_SUB(NOW(), INTERVAL 1 HOUR) < PlayedAt";
            case Day -> "SELECT * FROM Game WHERE UserID = 1 AND DATE_SUB(NOW(), INTERVAL 1 DAY) < PlayedAt";
            case Week -> "SELECT * FROM Game WHERE UserID = 1 AND DATE_SUB(NOW(), INTERVAL 1 WEEK) < PlayedAt";
            case Month -> "SELECT * FROM Game WHERE UserID = 1 AND DATE_SUB(NOW(), INTERVAL 1 MONTH) < PlayedAt";
            case Year -> "SELECT * FROM Game WHERE UserID = 1 AND DATE_SUB(NOW(), INTERVAL 1 YEAR) < PlayedAt";
        };

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
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

    /**
     * Saves a single game information into database.
     * @param selectedInterval interval that user selected
     * @param correctInterval interval that was correct
     */
    public void saveGame(int selectedInterval, int correctInterval) {
        try {
            long now = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(now);
            String query = "INSERT INTO Game (PlayedAt, SelectedInterval, CorrectInterval, UserID) " +
                    "VALUES ('"+timestamp+"', "+selectedInterval+", "+correctInterval+", 1)";
            Statement statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
