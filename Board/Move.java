package Pluton.Board;

import Pluton.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece movedPiece;
    final int destinationCoodrinate;


    private Move(final Board board,
         final Piece movedPiece,
         final int destinationCoodrinate){
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoodrinate = destinationCoodrinate;
    }

    public static final class MajorMove extends Move {

        public MajorMove(final Board board,
                  final Piece movedPiece,
                  final int destinationCoodrinate) {
            super(board, movedPiece, destinationCoodrinate);
        }
    }

    public static final class AttackMove extends Move{

        final Piece attackedPiece;
        public AttackMove(final Board board,
                   final Piece movedPiece,
                   final int destinationCoodrinate,
                   final Piece attackedPiece) {
            super(board, movedPiece, destinationCoodrinate);
            this.attackedPiece = attackedPiece;
        }
    }


}
