package br.pro.paulomotta.poc;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the concrete implementation of the Knight mover that calculates
 * all the 8 moves that are valid for a give starting Position
 * 
 * @author paulo
 */
public class KtMover implements ChessPieceMover {
    
    /**
     * Given the position calculates the moves
     * 
     * @param startPos the starting Position of the Knight
     * @return the List<Position> of the calculated moves
     */
    @Override
    public List<Position> moves(Position startPos){
        List<Position> positions = new ArrayList<>();
        if (startPos == null) {
            return positions;
        }
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
