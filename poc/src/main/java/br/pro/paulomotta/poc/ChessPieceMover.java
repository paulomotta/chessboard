package br.pro.paulomotta.poc;

import java.util.List;

/**
 *
 * @author paulo
 */
public interface ChessPieceMover {

    List<Position> moves(Position startPos);
    
}
