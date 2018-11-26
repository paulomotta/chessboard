/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pro.paulomotta.poc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo
 */
public class PositionToAlgebraicTest {
    
    public PositionToAlgebraicTest() {
    }
    
    /**
     * Test of positionToAlgebraic method, of class Position.
     */
    @Test
    public void testPositionToAlgebraic() {
        System.out.println("positionToAlgebraic");
        Position p = new Position(0,0);
        String expResult = "a1";
        String result = Position.positionToAlgebraic(p);
        assertEquals(expResult, result);
        
        p = new Position(7,7);
        expResult = "h8";
        result = Position.positionToAlgebraic(p);
        assertEquals(expResult, result);
    }
    
}
