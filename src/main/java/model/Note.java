package model;

/**
 * Singleton class for all the notes included in the app.
 */
public class Note {
    private String name;
    private int pitch;

    private Note(String name, int pitch) {
        this.name = name;
        this.pitch = pitch;
    }

    private static Note[] notes = new Note[24];

    static {
        notes[0] = new Note("C", 0);
        notes[1] = new Note("C#", 1);
        notes[2] = new Note("D", 2);
        notes[3] = new Note("D#", 3);
        notes[4] = new Note("E", 4);
        notes[5] = new Note("F", 5);
        notes[6] = new Note("F#", 6);
        notes[7] = new Note("G", 7);
        notes[8] = new Note("G#", 8);
        notes[9] = new Note("A", 9);
        notes[10] = new Note("A#", 10);
        notes[11] = new Note("B", 11);
        notes[12] = new Note("C2", 12);
        notes[13] = new Note("C#2", 13);
        notes[14] = new Note("D2", 14);
        notes[15] = new Note("D#2", 15);
        notes[16] = new Note("E2", 16);
        notes[17] = new Note("F2", 17);
        notes[18] = new Note("F#2", 18);
        notes[19] = new Note("G2", 19);
        notes[20] = new Note("G#2", 20);
        notes[21] = new Note("A2", 21);
        notes[22] = new Note("A#2", 22);
        notes[23] = new Note("B2", 23);
    }

    public static Note getNoteByPitch(int pitch) {
        if (pitch >= 0 && pitch < notes.length) {
            return notes[pitch];
        } else {
            throw new IllegalArgumentException("Invalid pitch value");
        }
    }

    public static int getPitchByNote(Note note) {
        return note.pitch;
    }

    @Override
    public String toString() {
        return name;
    }
}