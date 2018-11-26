/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class KtMoverTest {
    
    Position start;
    int expResult;

    public KtMoverTest(Position start, int expResult) {
        this.start = start;
        this.expResult = expResult;
    }
    
    @Parameterized.Parameters
    public static Collection positions() {
        return Arrays.asList(new Object[][]{
            {new Position("h1"), 8 },
            {new Position("g1"), 8 },
            {new Position("f1"), 8 },
            {null, 0 },
        });
    }
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of moves method, of class KtMover.
     */
    @Test
    public void testMoves() {
        System.out.println("moves");
        System.out.println("start is : " + start + " expResult=" + expResult);
        KtMover instance = new KtMover();
        List<Position> result = instance.moves(start);
        assertEquals(expResult, result.size());
    }
    
}
