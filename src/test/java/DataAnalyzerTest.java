import model.DataAnalyzer;
import model.Game;
import model.Interval;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import static model.DataAnalyzer.*;
import static org.junit.Assert.assertEquals;

public class DataAnalyzerTest {

    static List gameList;
    @BeforeClass
    public static void setUp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Game g1 = new Game(1, Interval.MAJOR_2ND, Interval.MAJOR_3RD, timestamp);
        Game g2 = new Game(1, Interval.OCTAVE, Interval.MINOR_2ND, timestamp);
        Game g3 = new Game(1, Interval.MAJOR_6TH, Interval.MAJOR_6TH, timestamp);
        Game g4 = new Game(1, Interval.MINOR_2ND, Interval.MINOR_2ND, timestamp);
        Game g5 = new Game(1, Interval.MAJOR_7TH, Interval.OCTAVE, timestamp);
        Game g6 = new Game(1, Interval.MAJOR_7TH, Interval.MAJOR_7TH, timestamp);
        Game g7 = new Game(1, Interval.MAJOR_2ND, Interval.MAJOR_2ND, timestamp);
        Game g8 = new Game(1, Interval.MINOR_7TH, Interval.MINOR_7TH, timestamp);
        Game g9 = new Game(1, Interval.MINOR_3RD, Interval.MINOR_3RD, timestamp);
        Game g10 = new Game(1, Interval.TRITONE, Interval.TRITONE, timestamp);
        Game g11 = new Game(1, Interval.PERFECT_5TH, Interval.TRITONE, timestamp);
        Game g12 = new Game(1, Interval.PERFECT_5TH, Interval.PERFECT_5TH, timestamp);
        Game g13 = new Game(1, Interval.MAJOR_2ND, Interval.MAJOR_3RD, timestamp);
        Game g14 = new Game(1, Interval.OCTAVE, Interval.OCTAVE, timestamp);
        Game g15 = new Game(1, Interval.MINOR_7TH, Interval.MINOR_7TH, timestamp);
        Game g16 = new Game(1, Interval.MINOR_6TH, Interval.MINOR_2ND, timestamp);
        Game g17 = new Game(1, Interval.TRITONE, Interval.OCTAVE, timestamp);
        Game g18 = new Game(1, Interval.MAJOR_7TH, Interval.MAJOR_7TH, timestamp);
        Game g19 = new Game(1, Interval.PERFECT_4TH, Interval.MINOR_2ND, timestamp);
        Game g20 = new Game(1, Interval.MINOR_6TH, Interval.MINOR_3RD, timestamp);
        Game g21 = new Game(1, Interval.MINOR_6TH, Interval.MINOR_6TH, timestamp);
        Game g22 = new Game(1, Interval.TRITONE, Interval.TRITONE, timestamp);
        Game g23 = new Game(1, Interval.OCTAVE, Interval.TRITONE, timestamp);
        Game g24 = new Game(1, Interval.MAJOR_3RD, Interval.PERFECT_5TH, timestamp);
        gameList = new ArrayList<Game>();
        List<Game> collection = Arrays.asList(g1,g2,g3,g4,g5,g6,g7,g8,g9,g10,g11,g12,g13,g14,g15,g16,g17,g18,g19,g20,g21,g22,g23,g24);
        gameList.addAll(collection);
    }

    @Test
    public void testFindTotalCount() {
        System.out.println("test case - find total count");
        assertEquals(24, DataAnalyzer.findTotalCount(gameList));
    }
    @Test
    public void testFindCorrectCount() {
        System.out.println("test case - find correct count");
        assertEquals(13, DataAnalyzer.findCorrectCount(gameList));
    }
    @Test
    public void testFindBiggestFlaw() {
        System.out.println("test case - find biggest flaw");
        assertEquals(Interval.MAJOR_3RD, findBiggestFlaw(gameList));
    }
    @Test
    public void testFindTotalCountWithInterval() {
        System.out.println("test case - find total count with interval");
        assertEquals(4, findTotalCount(gameList, Interval.MINOR_2ND));
        assertEquals(1, findTotalCount(gameList, Interval.MAJOR_2ND));
        assertEquals(2, findTotalCount(gameList, Interval.MINOR_3RD));
        assertEquals(2, findTotalCount(gameList, Interval.MAJOR_3RD));
        assertEquals(0, findTotalCount(gameList, Interval.PERFECT_4TH));
        assertEquals(4, findTotalCount(gameList, Interval.TRITONE));
        assertEquals(2, findTotalCount(gameList, Interval.PERFECT_5TH));
        assertEquals(1, findTotalCount(gameList, Interval.MINOR_6TH));
        assertEquals(1, findTotalCount(gameList, Interval.MAJOR_6TH));
        assertEquals(2, findTotalCount(gameList, Interval.MINOR_7TH));
        assertEquals(2, findTotalCount(gameList, Interval.MAJOR_7TH));
        assertEquals(3, findTotalCount(gameList, Interval.OCTAVE));
    }
    @Test
    public void testFindCorrectCountWithInterval() {
        System.out.println("test case - find correct count with interval");
        assertEquals(1, findCorrectCount(gameList, Interval.MINOR_2ND));
        assertEquals(1, findCorrectCount(gameList, Interval.MAJOR_2ND));
        assertEquals(1, findCorrectCount(gameList, Interval.MINOR_3RD));
        assertEquals(0, findCorrectCount(gameList, Interval.MAJOR_3RD));
        assertEquals(0, findCorrectCount(gameList, Interval.PERFECT_4TH));
        assertEquals(2, findCorrectCount(gameList, Interval.TRITONE));
        assertEquals(1, findCorrectCount(gameList, Interval.PERFECT_5TH));
        assertEquals(1, findCorrectCount(gameList, Interval.MINOR_6TH));
        assertEquals(1, findCorrectCount(gameList, Interval.MAJOR_6TH));
        assertEquals(2, findCorrectCount(gameList, Interval.MINOR_7TH));
        assertEquals(2, findCorrectCount(gameList, Interval.MAJOR_7TH));
        assertEquals(1, findCorrectCount(gameList, Interval.OCTAVE));
    }

}