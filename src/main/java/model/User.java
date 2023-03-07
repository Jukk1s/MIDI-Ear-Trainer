package model;

/**
 * Class that represents the logged-in user.
 */
public class User {
    private static int clickCount;
    private static int correctCount;
    private static double accuracy;
    private static int biggestFlaw;

    public static void setUserData(int timesClicked, int timesCorrect, int biggestFlaw) {
        User.clickCount = timesClicked;
        User.correctCount = timesCorrect;
        User.accuracy = (double)correctCount/clickCount*100;
        User.biggestFlaw = biggestFlaw;
    }

    public static void increaseClickCount() {
        User.clickCount++;
    }

    public static void increaseCorrectCount() {
        User.correctCount++;
    }

    public static void setBiggestFlaw(int biggestFlaw) {
        User.biggestFlaw = biggestFlaw;
    }

    public static void setAccuracy() {
        User.accuracy = (double)correctCount/clickCount*100;
    }

    public static int getClickCount() {
        return clickCount;
    }

    public static int getBiggestFlaw() {
        return biggestFlaw;
    }

    public static double getAccuracy() {
        return accuracy;
    }

}
