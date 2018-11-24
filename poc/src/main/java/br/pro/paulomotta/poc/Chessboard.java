package br.pro.paulomotta.poc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo
 */
public class Chessboard {
    
    public String[] splitColumnRow(String position){
        String tmp[] = new String[2];
        int size = position.length();
        String col = "";
        int i = 0;
        for(; i < size; i++){
            if(Character.isAlphabetic(position.charAt(i))){
                col += position.charAt(i);
            } else {
                break;
            }
        }
        String row = position.substring(i);
        tmp[0] = col;
        tmp[1] = row;
        if (col.length() < 1 || row.length() < 1) {
            throw new IllegalArgumentException("Invalid position ["+position+"]");
        }
        return tmp;
    }
    
    public int nameToColumn(String column){
        column = column.toLowerCase();
        int size = column.length();
        if (size <= 0) throw new IllegalArgumentException("Invalid column name ["+column+"]");
        int index = 0;
        for (int i = 0; i < size; i++) {
            index += (column.charAt(i) - 'a') + (26 * i);
        }
        return index;
    }
    
    public List<String> possibleMoves(String start, String piece){
        List<String> result = new ArrayList<>();
        result.add("f2");
        result.add("g3");
        return result;
    }
    
}
