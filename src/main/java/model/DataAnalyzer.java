package model;

import java.util.List;

import static utility.EnumConverter.integerToInterval;

/**
 * Class that works as a utility for DAO to analyze data retrieved from database.
 */
public class DataAnalyzer {

    /**
     * Counts the number of games played.
     * @param list list of Game objects
     * @return size of the given list
     */
    public static int findTotalCountByInterval(List<Game> list) {
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
     * Calculates which interval has relatively most incorrect answers.
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

        double biggestFlawValue = 0;
        Interval biggestFlaw = null;

        for (int i = 0; i < flawCount.length; i++) {
            int totalCount = findTotalCountByInterval(list, integerToInterval(i+1));
            System.out.println("totalcouht:" +totalCount);
            System.out.println("flawcount"+flawCount[i]);

            if (totalCount != 0) {
                if ((double) flawCount[i] / totalCount > biggestFlawValue) {
                    biggestFlawValue = flawCount[i];
                    biggestFlaw = integerToInterval(i+1);
                }
            }
        }

        return biggestFlaw;
    }

    /**
     * Finds the most common wrong answer in a given list of games.
     * @param list list of Game objects
     * @return interval that is the most common wrong answer
     */
    public static Interval findMostCommonMistake(List<Game> list) {
        int[] mistakeCount = {0,0,0,0,0,0,0,0,0,0,0,0};

        for (Game game : list) {
            if (game.getChosenInterval() != game.getCorrectInterval()) {
                Interval mistake = game.getChosenInterval();
                switch (mistake) {
                    case MINOR_2ND -> mistakeCount[0]++;
                    case MAJOR_2ND -> mistakeCount[1]++;
                    case MINOR_3RD -> mistakeCount[2]++;
                    case MAJOR_3RD -> mistakeCount[3]++;
                    case PERFECT_4TH -> mistakeCount[4]++;
                    case TRITONE -> mistakeCount[5]++;
                    case PERFECT_5TH-> mistakeCount[6]++;
                    case MINOR_6TH -> mistakeCount[7]++;
                    case MAJOR_6TH -> mistakeCount[8]++;
                    case MINOR_7TH -> mistakeCount[9]++;
                    case MAJOR_7TH -> mistakeCount[10]++;
                    case OCTAVE -> mistakeCount[11]++;
                }
            }
        }

        double mostMistakes = 0;
        Interval mostCommonMistake = null;

        for (int i = 0; i < mistakeCount.length; i++) {
            if (mistakeCount[i] > mostMistakes) {
                mostMistakes = mistakeCount[i];
                mostCommonMistake = integerToInterval(i+1);
            }
        }

        return mostCommonMistake;
    }


    /**
     * Counts the number of games played where the provided interval was the correct answer.
     * @param list list of Game objects
     * @param interval interval that was the correct answer
     * @return size of the given list, filtered with interval parameter
     */
    public static int findTotalCountByInterval(List<Game> list, Interval interval) {
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
