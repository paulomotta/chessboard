package br.pro.paulomotta.poc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo
 */
public class ChessPieceMoverFactoryTest {
    
    public ChessPieceMoverFactoryTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createChessPieceMover method, of class ChessPieceMoverFactory.
     */
    @Test
    public void testCreateChessPieceMover() {
        System.out.println("createChessPieceMover");
        String piece = "Kt";
        ChessPieceMover expResult = new KtMover();
        ChessPieceMover result = ChessPieceMoverFactory.createChessPieceMover(piece);
        assertEquals(expResult.getClass(), result.getClass());
    }
    
    /**
     * Test of createChessPieceMover method, of class ChessPieceMoverFactory.
     */
    @Test
    public void testCreateChessPieceMoverNonImplementedMover() {
        System.out.println("createChessPieceMover");
        String piece = "K";
        ChessPieceMover result = null;
        try {
            result = ChessPieceMoverFactory.createChessPieceMover(piece);
            fail("Where is the exception?");
        } catch (IllegalArgumentException e) {
            //ok
        }
    }
}
