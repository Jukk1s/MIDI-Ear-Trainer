package model;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import static model.Note.getPitchByNote;

/**
 * Class that plays notes.
 */
public class NotePlayer {
    private static Synthesizer synth;
    private static MidiChannel midiChannel;
    private static int pitch = 60; //the lowest pitch used here is 60 steps above the lowest that the MIDI synth can play

    /**
     * Constructor. Initializes the player.
     */
    public NotePlayer() {
        try {
            this.synth = MidiSystem.getSynthesizer();
            this.midiChannel = synth.getChannels()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the desired note.
     * @param note pitch of the note translated into Integer value
     */
    public static void playNote (Note note) {
        int midiNote = getPitchByNote(note) + pitch;
        try {
            synth.open();
            midiChannel.noteOn(midiNote, 100);
            Thread.sleep(2000);
            synth.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
