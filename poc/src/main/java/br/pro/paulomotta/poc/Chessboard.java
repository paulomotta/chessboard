package br.pro.paulomotta.poc;

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
    
}
