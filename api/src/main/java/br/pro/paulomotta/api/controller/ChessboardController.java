package br.pro.paulomotta.api.controller;

import br.pro.paulomotta.api.model.PositionDTO;
import br.pro.paulomotta.api.model.Request;
import br.pro.paulomotta.api.repository.RequestRepository;
import br.pro.paulomotta.poc.Chessboard;
import br.pro.paulomotta.poc.Position;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author paulo
 */
@Controller
public class ChessboardController {
    Chessboard board = new Chessboard();
    
    @Autowired
    RequestRepository repo;
    
    @GetMapping("/moves/{piece}/{start}/{turns}")
    @ResponseBody
    public List<PositionDTO> possibleMoves(
            @PathVariable String piece, 
            @PathVariable String start, 
            @PathVariable int turns) {
        
        List<PositionDTO> moves = new ArrayList<>();
        
        List<Position> calculated = board.possibleMoves(start, piece, turns);
        for (Position position : calculated) {
            moves.add(new PositionDTO(Position.positionToAlgebraic(position)));
        }
        Request req = new Request(start+"/"+piece+"/"+turns,moves.toString());
        repo.save(req);
        
        return moves;
    }
    
    @PostMapping("/add/column")
    @ResponseBody
    public Boolean addColumn(){
        Boolean b = board.addColumn();
        Request req = new Request("/add/column",b.toString());
        repo.save(req);
        return b;
    }
    
    @PostMapping("/remove/column")
    @ResponseBody
    public Boolean removeColumn(){
        Boolean b = board.removeColumn();
        Request req = new Request("/remove/column",b.toString());
        repo.save(req);
        return b;
    }
    
    @PostMapping("/add/row")
    @ResponseBody
    public Boolean addRow(){
        Boolean b = board.addRow();
        Request req = new Request("/add/row",b.toString());
        repo.save(req);
        return b;
    }
    
    @PostMapping("/remove/row")
    @ResponseBody
    public Boolean removeRow(){
        Boolean b = board.removeRow();
        Request req = new Request("/remove/row",b.toString());
        repo.save(req);
        return b;
    }
    
    @PostMapping("/reset")
    @ResponseBody
    public Boolean reset(){
        Boolean b = board.reset();
        Request req = new Request("/reset",b.toString());
        repo.save(req);
        return b;
    }
}
