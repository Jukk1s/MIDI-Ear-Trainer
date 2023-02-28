package model;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class NotePlayer {
    private Synthesizer synth;
    private MidiChannel midiChannel;

    public NotePlayer() {
        try {
            this.synth = MidiSystem.getSynthesizer();
            this.midiChannel = synth.getChannels()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playNote (int note) {

        try {
            synth.open();
            midiChannel.noteOn(note, 100);
            Thread.sleep(2000);
            synth.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
