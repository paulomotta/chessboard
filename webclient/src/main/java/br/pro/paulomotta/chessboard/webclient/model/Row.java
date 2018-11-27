package br.pro.paulomotta.chessboard.webclient.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo
 */
public class Row {
    public List<Cell> row = new ArrayList<>();
    
    public List<Cell> getRow(){
        return row;
    }
}
