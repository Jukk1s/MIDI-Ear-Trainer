package model;

import java.util.List;

/**
 * Class that works as a utility for DAO to analyze data retrieved from database.
 */
public class DataAnalyzer {

    /**
     * Counts the number of games played.
     * @param list list of Game objects
     * @return the size of the given list
     */
    public static int findClickCount(List<Game> list) {
        int totalCount = list.size();
        return totalCount;
    }

    /**
     * Counts the number of correct answers.
     * @param list list of Game objects
     * @return the number of correct answers in the given list
     */
    public static int findCorrectCount(List<Game> list) {
        int correctCount = 0;

        for (Game game : list) {
            if (game.getChosenInterval() == game.getCorrectInterval()) {
                correctCount++;
            }
        }
        return correctCount;
    }

    /**
     * Calculates which interval has the most incorrect answers.
     * TODO calculate the relative amount of incorrect answers
     * @param list list of Game objects
     * @return interval that has most incorrect answers
     */
    public static int findBiggestFlaw(List<Game> list) {
        int[] flawCount = {0,0,0,0,0,0,0,0,0,0,0,0};

        for (Game game : list) {
            if (game.getChosenInterval() != game.getCorrectInterval()) {
                int flaw = game.getCorrectInterval();
                switch (flaw) {
                    case 1:
                        flawCount[0]++;
                        break;
                    case 2:
                        flawCount[1]++;
                        break;
                    case 3:
                        flawCount[2]++;
                        break;
                    case 4:
                        flawCount[3]++;
                        break;
                    case 5:
                        flawCount[4]++;
                        break;
                    case 6:
                        flawCount[5]++;
                        break;
                    case 7:
                        flawCount[6]++;
                        break;
                    case 8:
                        flawCount[7]++;
                        break;
                    case 9:
                        flawCount[8]++;
                        break;
                    case 10:
                        flawCount[9]++;
                        break;
                    case 11:
                        flawCount[10]++;
                        break;
                    case 12:
                        flawCount[11]++;
                        break;
                }
            }
        }

        int biggestValue = 0;

        for (int i = 0; i < flawCount.length; i++) {
            if (flawCount[i] > biggestValue) {
                biggestValue = flawCount[i];
            }
        }

        return biggestValue;
    }
}
