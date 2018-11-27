package br.pro.paulomotta.poc;

import java.util.List;

/**
 * Interface with the method to calculate the moves of a piece. Given a Position
 * the concrete Mover will calculate all the possible moves regardless of being
 * valid values or not.
 * 
 * @author paulo
 */
public interface ChessPieceMover {

    List<Position> moves(Position startPos);
    
}
