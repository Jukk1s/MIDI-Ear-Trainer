package model;

import static model.Note.getPitchByNote;
import static utility.EnumConverter.integerToInterval;

public class Calculator {
    public static Interval calculateInterval(Note note1, Note note2) {
        int note1pitch = getPitchByNote(note1);
        int note2pitch = getPitchByNote(note2);
        int intervalInt = Math.abs(note1pitch - note2pitch);
        return integerToInterval(intervalInt);
    }
}
