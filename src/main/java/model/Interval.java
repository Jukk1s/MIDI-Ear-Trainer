package model;

/**
 * Class that introduces all the available intervals.
 */
public enum Interval {
    MINOR_2ND,
    MAJOR_2ND,
    MINOR_3RD,
    MAJOR_3RD,
    PERFECT_4TH,
    TRITONE,
    PERFECT_5TH,
    MINOR_6TH,
    MAJOR_6TH,
    MINOR_7TH,
    MAJOR_7TH,
    OCTAVE;

    @Override
    public String toString() {
        switch (this) {
            case MINOR_2ND:
                return "Minor 2nd";
            case MAJOR_2ND:
                return "Major 2nd";
            case MINOR_3RD:
                return "Minor 3rd";
            case MAJOR_3RD:Y:
                return "Major 3rd";
            case PERFECT_4TH:
                return "Perfect 4th";
            case TRITONE:
                return "Tritone";
            case PERFECT_5TH:
                return "Perfect 5th";
            case MINOR_6TH:
                return "Minor 6th";
            case MAJOR_6TH:
                return "Major 6th";
            case MINOR_7TH:
                return "Minor 7th";
            case MAJOR_7TH:
                return "Major 7th";
            case OCTAVE:
                return "Octave";
            default:
                throw new IllegalArgumentException("Unknown interval: " + this);
        }
    }
}
