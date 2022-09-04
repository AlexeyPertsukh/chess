package com.company.model.piece;

import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.Team;

public class PieceFactory {
    private static final String MARKER = "failed create piece";

    private PieceFactory() {
    }

    public static Piece createOf(char letter) {
        Team team = Character.isUpperCase(letter) ? Team.WHITE : Team.BLACK;
        Rank rank = Rank.getByLetter(letter);
        return createOf(rank, team);
    }

    public static Piece createOf(Rank rank, Team team) {
        switch (rank) {
            case PAWN:
                return Pawn.of(team);
            case KNIGHT:
                return Knight.of(team);
            case BISHOP:
                return Bishop.of(team);
            case ROOK:
                return Rook.of(team);
            case KING:
                return King.of(team);
            case QUEEN:
                return Queen.of(team);
            default:
                String message = String.format("%s by %s %s", MARKER, team, rank);
                throw new IllegalArgumentException(message);
        }
    }
}
