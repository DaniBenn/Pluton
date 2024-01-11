package Pluton.pieces;

import Pluton.Alliance;
import Pluton.Board.Board;
import Pluton.Board.BoardUtils;
import Pluton.Board.Move;
import Pluton.Board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static Pluton.Board.Move.*;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6 ,10, 15, 17};

    Knight(final int piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {


        final List<Move> legalMoves = new ArrayList<>();

         for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){

             final int candidateDestinationCoordinates = this.piecePosition + currentCandidateOffset;

             if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)){

                 if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                         isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                         isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                         isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                     continue;
                 }

                 final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinates);

                 if(!candidateDestinationTile.isTileOccupied()){
                     legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinates));
                 } else {
                     final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                     final Alliance pieceAlliance = pieceAtDestination.getPieceAllicance();

                     if(this.pieceAllicance != pieceAlliance) {
                         legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinates, pieceAtDestination));
                     }
                 }
             }
         }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10
                || candidateOffset == 6 ||candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffest){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffest == -10 || candidateOffest == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return  BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -15 || candidateOffset == -6
                || candidateOffset == 10 || candidateOffset == 17);
    }

}
