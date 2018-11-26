package br.pro.paulomotta.poc;

/**
 *
 * @author paulo
 */
public class ChessPieceMoverFactory {
    
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
