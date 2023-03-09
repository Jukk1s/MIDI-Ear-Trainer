package model;

/**
 * Class that represents the logged-in user.
 */
public class User {
    private static int gameCount;
    private static int correctCount;
    private static double accuracy;
    private static Interval biggestFlaw;

    public static void setUserData(int timesClicked, int timesCorrect, Interval biggestFlaw) {
        User.gameCount = timesClicked;
        User.correctCount = timesCorrect;
        User.accuracy = (double) correctCount/ gameCount * 100;
        User.biggestFlaw = biggestFlaw;
    }

    public static void increaseClickCount() {
        User.gameCount++;
    }

    public static void increaseCorrectCount() {
        User.correctCount++;
    }

    public static void setBiggestFlaw(Interval biggestFlaw) {
        User.biggestFlaw = biggestFlaw;
    }

    public static void setAccuracy() {
        User.accuracy = (double)correctCount/ gameCount *100;
    }

    public static int getGameCount() {
        return gameCount;
    }

    public static Interval getBiggestFlaw() {
        return biggestFlaw;
    }

    public static double getAccuracy() {
        return accuracy;
    }

}
