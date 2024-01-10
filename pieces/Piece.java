package Pluton.pieces;

import Pluton.Alliance;
import Pluton.Board.Board;
import Pluton.Board.Move;

import java.util.List;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAllicance;

    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAllicance = pieceAlliance;
        this.piecePosition = piecePosition;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);

}
