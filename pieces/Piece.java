package Pluton.pieces;

import Pluton.Alliance;
import Pluton.Board.Board;
import Pluton.Board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAllicance;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAllicance = pieceAlliance;
        this.piecePosition = piecePosition;
        //todo more work here!!!
        this.isFirstMove = false;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    public Alliance getPieceAllicance(){
        return  this.pieceAllicance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

}
