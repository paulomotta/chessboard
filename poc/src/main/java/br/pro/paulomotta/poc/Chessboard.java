package br.pro.paulomotta.poc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents the board and is the core for the business rules
 * It keeps track of the board size, allows board grownth and shrinking, and
 * resetting.
 * 
 * The core method is possibleMoves
 * 
 * This class also serves ther purpose of translating a list to algebraic notation
 * 
 * @author paulo
 */
public class Chessboard {

    public static final int MIN_COLUMNS = 8;
    public static final int MAX_COLUMNS = 26;
    public static final int MIN_ROWS = 8;
    public static final int MAX_ROWS = 26;
    int columns = MIN_COLUMNS;
    int rows = MIN_ROWS;
    
    public boolean addColumn(){
        int c = this.columns + 1;
        if (c <= MAX_COLUMNS){
            this.columns = c;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean removeColumn(){
        int c = this.columns - 1;
        if (c >= MIN_COLUMNS){
            this.columns = c;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean addRow(){
        int c = this.rows + 1;
        if (c <= MAX_ROWS){
            this.rows = c;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean removeRow(){
        int c = this.rows - 1;
        if (c >= MIN_ROWS){
            this.rows = c;
            return true;
        } else {
            return false;
        }
    }

    public boolean reset(){
        this.rows = MIN_ROWS;
        this.columns = MIN_COLUMNS;
        return true;
    }
    
    /**
     * This method calculates the possible moves for the given piece from the given position
     * in the given depth. It returns a List of Positions that are valid within the current
     * board.
     * 
     * @param start the starting position in algebraic notation
     * @param piece the chess piece (currently only the Knight is supported)
     * @param depth the amount of turns
     * @return a List<Position> 
     */
    public List<Position> possibleMoves(String start, String piece, int depth) {
        if (start == null || piece == null || start.length() < 1 || piece.length() < 1) {
            throw new IllegalArgumentException("Invalid start [" + start + "] or piece ["+piece+"]");
        }
        
        Position startPos = new Position(start);

        ChessPieceMover mover = ChessPieceMoverFactory.createChessPieceMover(piece);
        
        List<Position> result = new ArrayList<>();
        HashMap<Integer,List<Position>> level = new HashMap<>();
        
        for(int i=0; i < depth; i++){
            List<Position> positions = new ArrayList<>();
            
            List<Position> candidateList = null;
            if (i == 0) {
                candidateList = new ArrayList<>();
                candidateList.add(startPos);
            } else {
                candidateList = level.get(i-1);
            }
            
            for (Position candidate : candidateList){
                List<Position> tmpPos = mover.moves(candidate);
                positions.addAll(tmpPos);
            }
            
            List<Position> levelResult = new ArrayList<>();
            for (Position p : positions){
                if (isValid(p) && !result.contains(p)) {
                    levelResult.add(p);
                }
            }
            level.put(i,levelResult);
        }

        //TODO check better implemntation, List is slow for this verification
        for(List<Position> moves : level.values()){
            for(Position p : moves) {
                if (!result.contains(p)){
                    result.add(p);
                }
            }
        }
        return result;
    }

    /**
     * This method calls the more generic version and converts the result back to 
     * algebraic notation.
     * 
     * @param start the starting position in algebraic notation
     * @param piece the chess piece (currently only the Knight is supported)
     * @param depth the amount of turns
     * @return a List<String> of positions in algebraic notation
     */
    public List<String> possibleMovesInAlgebraicNotation(String start, String piece, int depth){
        List<String> result = new ArrayList<>();
        
        List<Position> moves = possibleMoves(start, piece, depth);
        for (Position p : moves){
            result.add(Position.positionToAlgebraic(p));
        }
        
        return result;
    }

    /**
     * This method check if the give Position is within the current board limits
     * 
     * @param p the Position to test
     * @return true if it is within limits false otherwise
     */
    protected boolean isValid(Position p) {
        return (p.col >= 0 && p.col < columns
                && p.row >= 0 && p.row < rows);
    }
}
