package br.pro.paulomotta.poc;

/**
 *
 * @author paulo
 */
public class Position {

    int col;
    int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public Position(String position) {
        String tmp[] = splitColumnRow(position);
        int c = nameToColumn(tmp[0]);
        int r = Integer.parseInt(tmp[1]) - 1;
        this.col = c;
        this.row = r;
    }

    @Override
    public String toString() {
        return "Position{" + positionToAlgebraic(this)+"(" + col + "," + row + ")}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.col;
        hash = 37 * hash + this.row;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.col != other.col) {
            return false;
        }
        if (this.row != other.row) {
            return false;
        }
        return true;
    }
    
    public static String[] splitColumnRow(String position) {
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
            Integer.parseInt(row);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid position [" + position + "]");
        }
        tmp[0] = col;
        tmp[1] = row;
        return tmp;
    }

    public static int nameToColumn(String column) {
        column = column.toLowerCase();
        int size = column.length();
        if (size <= 0 || size > 1) {
            throw new IllegalArgumentException("Invalid column name [" + column + "]");
        }
        int index = (column.charAt(0) - 'a');
        return index;
    }

    public static String columnToName(int column) {
        if (column < 0 || column > 25) {
            throw new IllegalArgumentException("Invalid column name [" + column + "]");
        }
        char c = (char) (column + 'a');
        return String.valueOf(c);
    }
    
    private static String positionToAlgebraic(Position p) {
        String c = columnToName(p.col);
        return c + (p.row + 1);
    }

}
