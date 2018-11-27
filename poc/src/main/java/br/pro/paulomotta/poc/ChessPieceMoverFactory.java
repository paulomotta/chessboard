package br.pro.paulomotta.poc;

/**
 * Factory class to create ChessPieceMovers
 * 
 * @author paulo
 */
public class ChessPieceMoverFactory {
    
    /**
     * Given the chess piece, returns the concrete mover
     * 
     * Currently only the Knight Mover is implemented
     * 
     * @param piece the chess piece code
     * @return the ChessPieceMover for the given piece
     */
    public static ChessPieceMover createChessPieceMover(String piece){
        ChessPieceMover mover = null;
        
        if (ChessPieceEnum.Kt.name().equals(piece)){
            mover = new KtMover();
        } else {
            throw new IllegalArgumentException("Mover not implemented yet");
        }

        return mover;
    }
    
}
