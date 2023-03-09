package model;

import java.util.List;

import static utility.TypeConverter.integerToInterval;

/**
 * Class that works as a utility for DAO to analyze data retrieved from database.
 */
public class DataAnalyzer {

    /**
     * Counts the number of games played.
     * @param list list of Game objects
     * @return size of the given list
     */
    public static int findTotalCount(List<Game> list) {
        int totalCount = list.size();
        return totalCount;
    }

    /**
     * Counts the number of correct answers.
     * @param list list of Game objects
     * @return number of correct answers in the given list
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
    public static Interval findBiggestFlaw(List<Game> list) {
        int[] flawCount = {0,0,0,0,0,0,0,0,0,0,0,0};

        for (Game game : list) {
            if (game.getChosenInterval() != game.getCorrectInterval()) {
                Interval flaw = game.getCorrectInterval();
                switch (flaw) {
                    case MINOR_2ND -> flawCount[0]++;
                    case MAJOR_2ND -> flawCount[1]++;
                    case MINOR_3RD -> flawCount[2]++;
                    case MAJOR_3RD -> flawCount[3]++;
                    case PERFECT_4TH -> flawCount[4]++;
                    case TRITONE -> flawCount[5]++;
                    case PERFECT_5TH-> flawCount[6]++;
                    case MINOR_6TH -> flawCount[7]++;
                    case MAJOR_6TH -> flawCount[8]++;
                    case MINOR_7TH -> flawCount[9]++;
                    case MAJOR_7TH -> flawCount[10]++;
                    case OCTAVE -> flawCount[11]++;
                }
            }
        }
        int biggestValue = 0;
        int intBiggestFlaw = 0;
        for (int i = 0; i < flawCount.length; i++) {
            if (flawCount[i] > biggestValue) {
                biggestValue = flawCount[i];
                intBiggestFlaw = i;
            }
        }
        Interval biggestFlaw = integerToInterval(intBiggestFlaw);
        return biggestFlaw;
    }

    /**
     * Counts the number of games played, where the provided interval was the correct answer.
     * @param list list of Game objects
     * @param interval interval that was the correct answer
     * @return size of the given list, filtered with interval parameter
     */
    public static int findTotalCount(List<Game> list, Interval interval) {
        int totalCount = 0;

        for (Game game : list) {
            if (game.getCorrectInterval() == interval) {
                totalCount++;
            }
        }
        return totalCount;
    }

    /**
     * Counts the number of correct answers, where the provided interval was the correct answer.
     * @param list list of Game objects
     * @return number of correct answers in the given list, filtered with interval parameter
     */
    public static int findCorrectCount(List<Game> list, Interval interval) {
        int correctCount = 0;

        for (Game game : list) {
            if (game.getChosenInterval() == game.getCorrectInterval() && game.getCorrectInterval() == interval) {
                correctCount++;
            }
        }
        return correctCount;
    }

}
