package br.pro.paulomotta.poc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo
 */
public class ChessboardBoardSizeTest {
    
    public ChessboardBoardSizeTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addColumn method, of class Chessboard.
     */
    @Test
    public void testAddColumn() {
        System.out.println("addColumn");
        Chessboard instance = new Chessboard();
        boolean expResult = true;
        for(int i=Chessboard.MIN_COLUMNS; i < Chessboard.MAX_COLUMNS;i++){
            boolean result = instance.addColumn();
            assertEquals(expResult, result);
        }
        boolean result = instance.addColumn();
        assertEquals(false, result);
    }

    /**
     * Test of removeColumn method, of class Chessboard.
     */
    @Test
    public void testRemoveColumn() {
        System.out.println("removeColumn");
        Chessboard instance = new Chessboard();
        boolean expResult = true;
        for(int i=Chessboard.MIN_COLUMNS; i < Chessboard.MAX_COLUMNS;i++){
            boolean result = instance.addColumn();
        }
        
        for(int i=Chessboard.MAX_COLUMNS; i>Chessboard.MIN_COLUMNS; i--){
            boolean result = instance.removeColumn();
            assertEquals(expResult, result);
        }
        
        boolean result = instance.removeColumn();
        assertEquals(false, result);
    }
    
    /**
     * Test of addRow method, of class Chessboard.
     */
    @Test
    public void testAddRow() {
        System.out.println("addRow");
        Chessboard instance = new Chessboard();
        boolean expResult = true;
        for(int i=Chessboard.MIN_ROWS; i < Chessboard.MAX_ROWS;i++){
            boolean result = instance.addRow();
            assertEquals(expResult, result);
        }
        boolean result = instance.addRow();
        assertEquals(false, result);
    }

    /**
     * Test of removeRow method, of class Chessboard.
     */
    @Test
    public void testRemoveRow() {
        System.out.println("removeRow");
        Chessboard instance = new Chessboard();
        boolean expResult = true;
        for(int i=Chessboard.MIN_ROWS; i < Chessboard.MAX_ROWS;i++){
            boolean result = instance.addRow();
        }
        
        for(int i=Chessboard.MAX_ROWS; i>Chessboard.MIN_ROWS; i--){
            boolean result = instance.removeRow();
            assertEquals(expResult, result);
        }
        
        boolean result = instance.removeRow();
        assertEquals(false, result);
    }

    /**
     * Test of isValid method, of class Chessboard.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        Chessboard instance = new Chessboard();
        boolean expResult = true;
        Position p = new Position("h1");
        boolean result = instance.isValid(p);
        assertEquals(expResult, result);
        
        p = new Position("h8");
        result = instance.isValid(p);
        assertEquals(expResult, result);
        
        p = new Position("a1");
        result = instance.isValid(p);
        assertEquals(expResult, result);
        
        p = new Position("a8");
        result = instance.isValid(p);
        assertEquals(expResult, result);

        expResult = false;

        p = new Position("a9");
        result = instance.isValid(p);
        assertEquals(expResult, result);
        
        p = new Position("i8");
        result = instance.isValid(p);
        assertEquals(expResult, result);
        
        p = new Position("i9");
        result = instance.isValid(p);
        assertEquals(expResult, result);
    }
    
    
}
