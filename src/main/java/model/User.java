package model;

public class User {
    private static int clickCount;
    private static int correctCount;
    private static int biggestFlaw;
    private static double accuracy;

    public static void setUserData(int timesClicked, int timesCorrect) {
        User.clickCount = timesClicked;
        User.correctCount = timesCorrect;
        User.accuracy = (double)correctCount/clickCount*100;
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
