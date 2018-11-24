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
            {"a1", new String[]{"a", "1"}},
            {"a", null},
        });
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPositions() {
        System.out.println("Position is : " + position + " expResult=" + expResult);
        Chessboard instance = new Chessboard();

        if (expResult == null) {
            try {
                String[] result = instance.splitColumnRow(position);
                fail("Where is the exception?");
            } catch (IllegalArgumentException ex) {
                //ok
            }
        } else {
            assertArrayEquals(expResult, instance.splitColumnRow(position));
        }
    }

}
