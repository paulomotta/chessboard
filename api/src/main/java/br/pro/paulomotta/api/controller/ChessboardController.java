package br.pro.paulomotta.api.controller;

import br.pro.paulomotta.api.model.PositionDTO;
import br.pro.paulomotta.poc.Chessboard;
import br.pro.paulomotta.poc.Position;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author paulo
 */
@Controller
public class ChessboardController {
    
    @GetMapping("/moves/{piece}/{start}/{turns}")
    @ResponseBody
    public List<PositionDTO> possibleMoves(
            @PathVariable String piece, 
            @PathVariable String start, 
            @PathVariable int turns) {
        
        List<PositionDTO> moves = new ArrayList<>();
        
        Chessboard board = new Chessboard();
        List<Position> calculated = board.possibleMoves(start, piece, turns);
        for (Position position : calculated) {
            moves.add(new PositionDTO(Position.positionToAlgebraic(position)));
        }
        
        return moves;
    }
}
