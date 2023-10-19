package model;

import java.util.Random;

import static utility.EnumConverter.intervalToInteger;

public class NoteGenerator {
    private static Random rand;
    Note note1;
    Note note2;

    public NoteGenerator() {
        this.rand = new Random();
    }
    public Note[] generateTwoNotesWithRandomInterval() {
        int interval = rand.nextInt(12) + 1;
        int rand1 = rand.nextInt(12);
        int rand2 = rand1 + interval;
        note1 = Note.getNoteByPitch(rand1);
        note2 = Note.getNoteByPitch(rand2);
        Note[] notes = {note1, note2};
        return notes;
    }

    public Note[] generateTwoRandomNotesWithSpecificInterval(Interval interval) {
        int intervalInt = intervalToInteger(interval);
        int rand1 = rand.nextInt(12) + 1;
        int rand2 = rand1 + intervalInt;
        note1 = Note.getNoteByPitch(rand1);
        note2 = Note.getNoteByPitch(rand2);
        Note[] notes = {note1, note2};
        return notes;
    }

}