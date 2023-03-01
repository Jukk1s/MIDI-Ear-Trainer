package model;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.util.List;

public class DataAnalyzer {

    public static int findClickCount(List<Game> list) {
        int totalCount = list.size();
        return totalCount;
    }

    public static int findCorrectCount(List<Game> list) {
        int correctCount = 0;

        for (Game game : list) {
            if (game.getChosenInterval() == game.getCorrectInterval()) {
                correctCount++;
            }
        }
        return correctCount;
    }

    public static int findBiggestFlaw(List<Game> list) {

        return 0;
    }
}
