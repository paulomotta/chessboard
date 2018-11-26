package br.pro.paulomotta.poc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo
 */
public class Chessboard {

    public static final int MIN_COLUMNS = 8;
    public static final int MAX_COLUMNS = 25;
    public static final int MIN_ROWS = 8;
    public static final int MAX_ROWS = 25;

    int columns = MIN_COLUMNS;
    int rows = MIN_ROWS;

    public List<Position> possibleMoves(String start, String piece) {
        if (start == null || piece == null || start.length() < 1 || piece.length() < 1) {
            throw new IllegalArgumentException("Invalid start [" + start + "] or piece ["+piece+"]");
        }
        
        Position startPos = new Position(start);

        List<Position> positions = calculateValidMovesForPiece(startPos, piece);

        return positions;
    }

    public List<Position> calculateValidMovesForPiece(Position startPos, String piece) {
        Position positions[] = new Position[8];
        //TODO hardcoding knight moves to better understand calculations
        positions[0] = new Position(startPos.col + 2, startPos.row + 1);
        positions[1] = new Position(startPos.col + 2, startPos.row - 1);
        positions[2] = new Position(startPos.col - 2, startPos.row + 1);
        positions[3] = new Position(startPos.col - 2, startPos.row - 1);
        positions[4] = new Position(startPos.col + 1, startPos.row + 2);
        positions[5] = new Position(startPos.col + 1, startPos.row - 2);
        positions[6] = new Position(startPos.col - 1, startPos.row + 2);
        positions[7] = new Position(startPos.col - 1, startPos.row - 2);
        
        List<Position> result = new ArrayList<>();

        for (int i = 0; i < positions.length; i++) {
            Position position = positions[i];
            if (isValid(position)) {
                result.add(position);
            }
            
        }
        
        return result;
    }

    private boolean isValid(Position p) {
        return (p.col >= 0 && p.col < columns
                && p.row >= 0 && p.row < columns);
    }
}
