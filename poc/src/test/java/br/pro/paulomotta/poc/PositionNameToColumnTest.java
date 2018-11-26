package br.pro.paulomotta.poc;

import java.util.Arrays;
import java.util.Collection;
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
public class PositionNameToColumnTest {
    
    String column;
    int expResult;

    public PositionNameToColumnTest(String column, int expResult) {
        this.column = column;
        this.expResult = expResult;
    }
    
    @Parameterized.Parameters
    public static Collection positions() {
        return Arrays.asList(new Object[][]{
            {"a", 0},
            {"b", 1},
            {"z", 25},
            {"aa", 26},
            {"Aa", 26},
            {"aA", -1},
            {"AA", -1},
            {"Abc", -1},
            {"AbC", -1},
            {"", -1},
        });
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of nameToColumn method, of class Chessboard.
     */
    @Test
    public void testNameToColumn() {
        System.out.println("nameToColumn");
        
        System.out.println("Column is : " + column + " expResult=" + expResult);

        if (expResult < 0 || expResult > 25) {
            try {
                int result = Position.nameToColumn(column);
                fail("Where is the exception?");
            } catch (IllegalArgumentException ex) {
                //ok
            }
        } else {
            assertEquals(expResult, Position.nameToColumn(column));
        }
    }
    
    /**
     * Test of ColumnToName method, of class Chessboard.
     */
    @Test
    public void testColumnToName() {
        System.out.println("ColumnToName");
        
        System.out.println("Column is : " + expResult + " name=" + column);

        if (expResult < 0 || expResult > 25) {
            try {
                String result = Position.columnToName(expResult);
                fail("Where is the exception?");
            } catch (IllegalArgumentException ex) {
                //ok
            }
        } else {
            assertEquals(column, Position.columnToName(expResult));
        }
    }
}
