package br.pro.paulomotta.chessboard.webclient.bean;

import br.pro.paulomotta.chessboard.webclient.model.Cell;
import br.pro.paulomotta.chessboard.webclient.model.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author paulo
 */
@Scope(value = "session")
@Component(value = "chessboardBean")
@ELBeanName(value = "chessboardBean")
@Join(path = "/chessboard", to = "/chessboard-form.xhtml")
public class ChessboardBean {

   

    private Set<String> moves = new TreeSet<>();
    private List<Row> rows = new ArrayList<>();
    private int numRows = 8;
    private int numColumns = 8;
    private int lastColumn = numColumns + 1;
    private int lastRow = numRows + 1;
    private int turns = 2;

    /**
     * Creates a new instance of ChessboardBean
     */
    public ChessboardBean() {
        createBoard();
    }

    public List<Row> getRows() {
        return rows;
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + 8090 + uri;
    }

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public void moves(String position) {
        System.out.println("position=" + position);

        List lista = restTemplate().getForObject(createURLWithPort("/moves/Kt/"+position+"/"+turns), List.class);

        for(Object o:lista){
            System.out.println("o="+o);
            Map<String,String> m = (Map<String,String>)o;
            
            moves.add(m.get("coordinates"));
        }
        clearBoard();
        moves.clear();
        moves = new TreeSet<>();
    }

    public void addColumn() {
        Boolean b = restTemplate().postForObject(createURLWithPort("/add/column"), null, Boolean.class);
        if (b) {
            numColumns++;
            lastColumn++;
            clearBoard();
        }
    }

    public void removeColumn() {
        Boolean b = restTemplate().postForObject(createURLWithPort("/remove/column"), null, Boolean.class);
        if (b) {
            numColumns--;
            lastColumn--;
            clearBoard();
        }
    }

    public void addRow() {
        Boolean b = restTemplate().postForObject(createURLWithPort("/add/row"), null, Boolean.class);
        if (b) {
            numRows++;
            lastRow++;
            clearBoard();
        }
    }

    public void removeRow() {
        Boolean b = restTemplate().postForObject(createURLWithPort("/remove/row"), null, Boolean.class);
        if (b) {
            numRows--;
            lastRow--;
            clearBoard();
        }
    }

    public void reset() {
        Boolean b = restTemplate().postForObject(createURLWithPort("/reset"), null, Boolean.class);
        if (b) {
            numRows = 8;
            lastRow = numRows + 1;
            numColumns = 8;
            lastColumn = numColumns + 1;
            setTurns(2);
            clearBoard();
        }
    }
    
    public void addTurn(){
        turns++;
    }
    
    public void removeTurn(){
        if (turns>0){
            turns--;
        }
    }

    private void clearBoard() {
        rows.clear();
        rows = new ArrayList<>();
        createBoard();
    }

    private void createBoard() {
        int color = 1;
        for (int i = 0; i < numRows + 2; i++) {
            Row r = new Row();
            color = i % 2;
            for (int j = 0; j < numColumns + 2; j++) {
                Cell p = new Cell();

                if ((j == 0 || j == lastColumn) || i == 0 || i == lastRow) {
                    p.setLabel(true);
                } else {
                    p.setColor(color);
                    color = (color == 1 ? 0 : 1);
                }

                if (i == 0 && j == 0
                        || i == 0 && j == lastColumn
                        || i == lastRow && j == 0
                        || i == lastRow && j == lastColumn) {
                    p.setName(" ");
                } else if (i == 0 || i == lastRow) {
                    p.setName(String.valueOf((char) ('a' + j - 1)));
                } else if (j == 0 || j == lastColumn) {
                    p.setName(" " + (numRows - i + 1) + " ");
                } else {
                    String pos = toAlgebraic(j, numRows - i + 1);
                    if (moves.contains(pos)) {
                        p.setName("Kt");
                        p.setBgColor("cyan");
                    } else {
                        p.setName(pos);
                    }

                }

                r.row.add(p);
            }
            rows.add(r);
        }
    }

    private String toAlgebraic(int j, int i) {
        char col = (char) ('a' + j - 1);
        int row = i;
        return String.valueOf(col + "" + row);
    }

    /**
     * @return the turns
     */
    public int getTurns() {
        return turns;
    }

    /**
     * @param turns the turns to set
     */
    public void setTurns(int turns) {
        this.turns = turns;
    }

}
