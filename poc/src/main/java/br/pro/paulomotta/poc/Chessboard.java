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

    public String[] splitColumnRow(String position) {
        String tmp[] = new String[2];
        String col = "";
        if (Character.isAlphabetic(position.charAt(0))) {
            col += position.charAt(0);
        } else {
            throw new IllegalArgumentException("Invalid position [" + position + "]");
        }
        String row = position.substring(1);
        if (col.length() < 1 || row.length() < 1) {
            throw new IllegalArgumentException("Invalid position [" + position + "]");
        }
        
        try {
            int r = Integer.parseInt(row);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid position [" + position + "]");
        }
        tmp[0] = col;
        tmp[1] = row;
        return tmp;
    }

    public int nameToColumn(String column) {
        column = column.toLowerCase();
        int size = column.length();
        if (size <= 0 || size > 1) {
            throw new IllegalArgumentException("Invalid column name [" + column + "]");
        }
        int index = (column.charAt(0) - 'a');
        return index;
    }

    public String columnToName(int column) {
        if (column < 0 || column > 25) {
            throw new IllegalArgumentException("Invalid column name [" + column + "]");
        }
        char c = (char) (column + 'a');
        return String.valueOf(c);
    }

    public List<String> possibleMoves(String start, String piece) {
        if (start == null || piece == null || start.length() < 1 || piece.length() < 1) {
            throw new IllegalArgumentException("Invalid start [" + start + "] or piece ["+piece+"]");
        }
        
        String tmp[] = splitColumnRow(start);
        int col = nameToColumn(tmp[0]);
        int row = Integer.parseInt(tmp[1]) - 1;

        //TODO hardcoding knight moves to better understand calculations
        Position startPos = new Position(col, row);

        Position positions[] = new Position[8];
        
        positions[0] = new Position(startPos.col + 2, startPos.row + 1);
        positions[1] = new Position(startPos.col + 2, startPos.row - 1);
        positions[2] = new Position(startPos.col - 2, startPos.row + 1);
        positions[3] = new Position(startPos.col - 2, startPos.row - 1);

        positions[4] = new Position(startPos.col + 1, startPos.row + 2);
        positions[5] = new Position(startPos.col + 1, startPos.row - 2);
        positions[6] = new Position(startPos.col - 1, startPos.row + 2);
        positions[7] = new Position(startPos.col - 1, startPos.row - 2);

        List<String> result = new ArrayList<>();

        for (int i = 0; i < positions.length; i++) {
            Position position = positions[i];
            if (isValid(position)) {
                System.out.println(position);
                String str = positionToAlgebraic(position);
                result.add(str);
            }
            
        }

        return result;
    }

    private boolean isValid(Position p) {
        return (p.col >= 0 && p.col < columns
                && p.row >= 0 && p.row < columns);
    }

    private String positionToAlgebraic(Position p) {
        String col = columnToName(p.col);
        return col+(p.row + 1);
    }

    class Position {

        int col;
        int row;

        public Position(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public String toString() {
            return "Position{" + "col=" + col + ", row=" + row + '}';
        }
        
    }

}
