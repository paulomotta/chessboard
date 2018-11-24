package br.pro.paulomotta.poc;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo
 */
@RunWith(Parameterized.class)
public class ChessboardTest {

    String position;
    String[] expResult;

    public ChessboardTest(String position, String[] result) {
        this.position = position;
        this.expResult = result;
    }

    @Parameterized.Parameters
    public static Collection positions() {
        return Arrays.asList(new Object[][]{
            {"a1", new String[]{"a","1"}}
        });
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of splitColumnRow method, of class Chessboard.
     */
    @Test
    public void testSplitColumnRow() {
        System.out.println("splitColumnRow");
        String position = "";
        Chessboard instance = new Chessboard();
        String[] expResult = null;
        String[] result = instance.splitColumnRow(position);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPositions() {
        System.out.println("Position is : " + position);
        Chessboard instance = new Chessboard();
        assertArrayEquals(expResult, instance.splitColumnRow(position));
    }

}
