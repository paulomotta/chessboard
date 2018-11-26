package br.pro.paulomotta.poc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author paulo
 */
@RunWith(Parameterized.class)
public class ChessboardPossibleMovesTest {
    
    String start;
    String piece;
    List<String> expResult;

    public ChessboardPossibleMovesTest(String start, String piece, List<String> expResult) {
        this.start = start;
        this.piece = piece;
        this.expResult = expResult;
    }
        
    @Parameterized.Parameters
    public static Collection positions() {
        return Arrays.asList(new Object[][]{
            {"h1", "Kt", Arrays.asList(new Position[]{new Position("f2"),new Position("g3")}) },
            {"g1", "Kt", Arrays.asList(new Position[]{new Position("e2"),new Position("f3"),new Position("h3")}) },
            {"f1", "Kt", Arrays.asList(new Position[]{new Position("d2"),new Position("e3"),new Position("g3"),new Position("h2")}) },
            {"", "", null},
        });
    }
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of possibleMoves method, of class Chessboard.
     */
    @Test
    public void testPossibleMoves() {
        System.out.println("possibleMoves");
        
        System.out.println("start is : " + start + " piece="+ piece +" expResult=" + expResult);
        Chessboard instance = new Chessboard();

        if (expResult == null) {
            try {
                List<Position> result = instance.possibleMoves(start, piece);
                fail("Where is the exception?");
            } catch (IllegalArgumentException ex) {
                //ok
            }
        } else {
            //Collections.sort(expResult);
            List<Position> result = instance.possibleMoves(start, piece);
            //Collections.sort(result);
            assertEquals(expResult.size(), result.size());
        }
    }
    
}
