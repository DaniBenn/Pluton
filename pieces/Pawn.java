package Pluton.pieces;

import Pluton.Alliance;
import Pluton.Board.Board;
import Pluton.Board.BoardUtils;
import Pluton.Board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static Pluton.Board.Move.*;

public class Pawn extends Piece{

    private final static int[]  CANDIDATE_MOVE_COORDINATE = {8, 16};

    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
         for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

             final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAllicance().getDirection() *currentCandidateOffset);

             if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                 continue;
             }

             if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                 //todo more work here!!!
                 legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
             } else if( currentCandidateOffset == 16 && this.isFirstMove() &&
                     (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAllicance().isBlack()) ||
                     (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAllicance().isWhite())){

                 final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAllicance.getDirection() * 8);
                 if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                    !board.getTile(candidateDestinationCoordinate).isTileOccupied()){

                     legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                 }

             }
         }

        return legalMoves;
    }
}
