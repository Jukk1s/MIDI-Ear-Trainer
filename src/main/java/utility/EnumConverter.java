package utility;

import model.GameType;
import model.Interval;

/**
 * Class that converts Interval to String and Integer, and Integer to Interval.
 */
public class EnumConverter {

    /**
     * Converts Integer value to Interval
     * @param value Integer to be converted
     * @return corresponding enum Interval
     */
    public static Interval integerToInterval(int value) {
        Interval interval = null;
        switch (value) {
            case 1 -> interval = Interval.MINOR_2ND;
            case 2 -> interval = Interval.MAJOR_2ND;
            case 3 -> interval = Interval.MINOR_3RD;
            case 4 -> interval = Interval.MAJOR_3RD;
            case 5 -> interval = Interval.PERFECT_4TH;
            case 6 -> interval = Interval.TRITONE;
            case 7 -> interval = Interval.PERFECT_5TH;
            case 8 -> interval = Interval.MINOR_6TH;
            case 9 -> interval = Interval.MAJOR_6TH;
            case 10 -> interval = Interval.MINOR_7TH;
            case 11 -> interval = Interval.MAJOR_7TH;
            case 12 -> interval = Interval.OCTAVE;
        }
        return interval;
    }

    /**
     * Converts Interval to Integer value.
     * @param interval Interval to be converted
     * @return corresponding Integer value
     */
    public static Integer intervalToInteger(Interval interval) {
        int value = 0;
        switch (interval) {
            case MINOR_2ND -> value = 1;
            case MAJOR_2ND -> value = 2;
            case MINOR_3RD -> value = 3;
            case MAJOR_3RD -> value = 4;
            case PERFECT_4TH -> value = 5;
            case TRITONE -> value = 6;
            case PERFECT_5TH -> value = 7;
            case MINOR_6TH -> value = 8;
            case MAJOR_6TH -> value = 9;
            case MINOR_7TH -> value = 10;
            case MAJOR_7TH -> value = 11;
            case OCTAVE -> value = 12;
        }
        return value;
    }

    /**
     * Converts String value to Interval
     * @param string String to be converted
     * @return corresponding Interval value
     */
    public static Interval stringToInterval(String string) {
        //TODO
        return null;
    }

    /**
     * Converts Interval to String value.
     * @param interval Interval to be converted
     * @return corresponding String value
     */
    public static String intervalToString(Interval interval) {
        String value = null;
        switch (interval) {
            case MINOR_2ND -> value = "Minor 2nd";
            case MAJOR_2ND -> value = "Major 2nd";
            case MINOR_3RD -> value = "Minor 3rd";
            case MAJOR_3RD -> value = "Major 3rd";
            case PERFECT_4TH -> value = "Perfect 4th";
            case TRITONE -> value = "Tritone";
            case PERFECT_5TH -> value = "Perfect 5th";
            case MINOR_6TH -> value = "Minor 6th";
            case MAJOR_6TH -> value = "Major 6th";
            case MINOR_7TH -> value = "Minor 7th";
            case MAJOR_7TH -> value = "Major 7th";
            case OCTAVE -> value = "Octave";
        }
        return value;
    }

    /**
     * Converts GameType to String value.
     * @param gameType gameType to be converted
     * @return corresponding String value
     */
    public static String gameTypeToString(GameType gameType) {
        String value = null;
        switch (gameType) {
            case GAME -> value = "game";
            case TRAINING -> value = "training";
        }
        return value;
    }
}
