package br.pro.paulomotta.api.controller;

import br.pro.paulomotta.api.model.PositionDTO;
import br.pro.paulomotta.poc.Chessboard;
import br.pro.paulomotta.poc.Position;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
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
        
        return moves;
    }
    
    @PostMapping("/add/column")
    @ResponseBody
    public Boolean addColumn(){
        return board.addColumn();
    }
    
    @PostMapping("/remove/column")
    @ResponseBody
    public Boolean removeColumn(){
        return board.removeColumn();
    }
    
    @PostMapping("/add/row")
    @ResponseBody
    public Boolean addRow(){
        return board.addRow();
    }
    
    @PostMapping("/remove/row")
    @ResponseBody
    public Boolean removeRow(){
        return board.removeRow();
    }
    
    @PostMapping("/reset")
    @ResponseBody
    public Boolean reset(){
        return board.reset();
    }
}
