package Pluton.pieces;

import Pluton.Alliance;
import Pluton.Board.Board;
import Pluton.Board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAllicance;

    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAllicance = pieceAlliance;
        this.piecePosition = piecePosition;
    }

    public Alliance getPieceAllicance(){
        return  this.pieceAllicance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

}
