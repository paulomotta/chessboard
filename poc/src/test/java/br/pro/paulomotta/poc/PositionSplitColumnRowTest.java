package br.pro.paulomotta.poc;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runners.Parameterized;
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
public class PositionSplitColumnRowTest {

    String position;
    String[] expResult;

    public PositionSplitColumnRowTest(String position, String[] result) {
        this.position = position;
        this.expResult = result;
    }

    @Parameterized.Parameters
    public static Collection positions() {
        return Arrays.asList(new Object[][]{
            {"a1", new String[]{"a", "1"}},
            {"aa1", null},
            {"aa11", null},
            {"a11", new String[]{"a", "11"}},
            {"z11", new String[]{"z", "11"}},
            {"a", null},
            {"1", null},
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

        if (expResult == null) {
            try {
                String[] result = Position.splitColumnRow(position);
                fail("Where is the exception?");
            } catch (IllegalArgumentException ex) {
                //ok
            }
        } else {
            assertArrayEquals(expResult, Position.splitColumnRow(position));
        }
    }

}
