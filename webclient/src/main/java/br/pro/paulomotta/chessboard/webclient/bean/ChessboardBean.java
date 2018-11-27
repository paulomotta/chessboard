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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

   

    Set<String> moves = new TreeSet<>();
    List<Row> rows = new ArrayList<>();
    int numRows = 8;
    int numColumns = 8;
    int lastColumn = numColumns + 1;
    int lastRow = numRows + 1;

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

        List lista = restTemplate().getForObject(createURLWithPort("/moves/Kt/"+position+"/2"), List.class);
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//        ResponseEntity<String> response = testRestTemplate.exchange(
//                createURLWithPort("/moves/Kt/h1/1"),
//                HttpMethod.GET, entity, String.class);

        for(Object o:lista){
            System.out.println("o="+o);
            Map<String,String> m = (Map<String,String>)o;
            
            moves.add(m.get("coordinates"));
        }
        //moves.add("f2");
        //moves.add("g3");
        clearBoard();
        moves.clear();
        moves = new TreeSet<>();
    }

    public void addColumn() {
        numColumns++;
        lastColumn++;
        clearBoard();
    }

    public void removeColumn() {
        numColumns--;
        lastColumn--;
        clearBoard();
    }

    public void addRow() {
        numRows++;
        lastRow++;
        clearBoard();
    }

    public void removeRow() {
        numRows--;
        lastRow--;
        clearBoard();
    }

    public void reset() {
        numRows = 8;
        lastRow = numRows + 1;
        numColumns = 8;
        lastColumn = numColumns + 1;
        clearBoard();
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

}
