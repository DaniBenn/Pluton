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

public class Bishop extends Piece{
   private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-7, 7, 9};

    Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

       final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinates = this.piecePosition;

            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)) {

                if(isFirstColumnExclusion(candidateDestinationCoordinates, candidateCoordinateOffset)||
                isEightColumnExclusion(candidateDestinationCoordinates, candidateCoordinateOffset)){
                    break;
                }
                candidateDestinationCoordinates += candidateCoordinateOffset;

                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinates);

                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinates));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAllicance();
                        if (this.pieceAllicance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinates, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }


        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPostion, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPostion] &&  (candidateOffset == -9 || candidateOffset == 7);
    }
    private static boolean isEightColumnExclusion(final int currentPostion, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPostion] &&  (candidateOffset == -7 || candidateOffset == 9);
    }


}
