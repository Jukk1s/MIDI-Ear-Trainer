package utility;

import model.Interval;

/**
 * Class that converts Integers into Interval, and Intervals into Integer.
 */
public class TypeConverter {

    /**
     * Converts Integer value into Interval
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
     * Converts enum Interval into Integer.
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

}
