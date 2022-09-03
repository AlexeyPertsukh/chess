package com.company.model.piece;

import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.Team;

public class PieceFactory {
    private static final String MARKER = "failed create piece";

    private PieceFactory() {
    }

    public static Piece createOf(String s) {
        if (s.length() != 2) {
            String message = String.format("%s by string '%s'", MARKER, s);
            throw new IllegalArgumentException(message);
        }

        String from = s.toUpperCase();

        char charRank = from.charAt(0);
        Rank rank = Rank.getByLetter(charRank);

        char charTeam = from.charAt(1);
        Team team = Team.getByLetter(charTeam);

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
            case ROCK:
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
