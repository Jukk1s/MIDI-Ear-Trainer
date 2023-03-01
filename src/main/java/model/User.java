package model;

public class User {
    private static int timesClicked;
    private static int biggestFlaw;
    private static double accuracy;

    public static void setUserData(int timesClicked, double accuracy) {
        User.timesClicked = timesClicked;
        User.accuracy = accuracy;
    }

    public static void increaseTimesClicked() {
        User.timesClicked = User.timesClicked + 1;
    }

    public static void setBiggestFlaw(int biggestFlaw) {
        User.biggestFlaw = biggestFlaw;
    }


    public static int getTimesClicked() {
        return timesClicked;
    }

    public static int getBiggestFlaw() {
        return biggestFlaw;
    }

    public static double getAccuracy() {
        return accuracy;
    }
}
