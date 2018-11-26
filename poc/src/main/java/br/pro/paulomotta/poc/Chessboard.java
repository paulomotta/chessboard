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

        ChessPieceMover mover = ChessPieceMoverFactory.createChessPieceMover(piece);
        
        List<Position> positions = mover.moves(startPos);
        List<Position> result = new ArrayList<>();
        
        for (Position p : positions){
            if (isValid(p)) {
                result.add(p);
            }
        }
        
        return result;
    }

    private boolean isValid(Position p) {
        return (p.col >= 0 && p.col < columns
                && p.row >= 0 && p.row < rows);
    }
}
