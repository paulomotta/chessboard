package br.pro.paulomotta.poc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo
 */
public class KtMover implements ChessPieceMover {
    
    @Override
    public List<Position> moves(Position startPos){
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(startPos.col + 2, startPos.row + 1));
        positions.add(new Position(startPos.col + 2, startPos.row - 1));
        positions.add(new Position(startPos.col - 2, startPos.row + 1));
        positions.add(new Position(startPos.col - 2, startPos.row - 1));
        positions.add(new Position(startPos.col + 1, startPos.row + 2));
        positions.add(new Position(startPos.col + 1, startPos.row - 2));
        positions.add(new Position(startPos.col - 1, startPos.row + 2));
        positions.add(new Position(startPos.col - 1, startPos.row - 2));
        return positions;
    }
    
}
