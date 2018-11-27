package br.pro.paulomotta.poc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
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

    public List<String> possibleMovesInAlgebraicNotation(String start, String piece, int depth){
        List<String> result = new ArrayList<>();
        
        List<Position> moves = possibleMoves(start, piece, depth);
        for (Position p : moves){
            result.add(Position.positionToAlgebraic(p));
        }
        
        return result;
    }
    protected boolean isValid(Position p) {
        return (p.col >= 0 && p.col < columns
                && p.row >= 0 && p.row < rows);
    }
}
