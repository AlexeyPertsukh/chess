package com.company.model.board;

import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.Team;
import com.company.model.piece.*;

public class BoardLoader {
    public static final String SPLIT = ",";

    private BoardLoader() {
    }

    public static Board boardOf(String string) {

        String[] array = string.toLowerCase().replace(" ", "").split(SPLIT);

        Board board = new Board();
        for (String s : array) {
            Piece piece = toPiece(s.substring(0, 2));
            board.insert(piece, s.substring(2));
        }

        return board;
    }

    protected static Piece toPiece(String s) {
        char charRank = s.toUpperCase().charAt(0);
        Rank rank = Rank.getByLetter(charRank);

        char charTeam = s.toUpperCase().charAt(1);
        Team team = Team.getByLetter(charTeam);

        return toPiece(rank, team);
    }

    protected static Piece toPiece(Rank rank, Team team) {
        switch (rank) {
            case PAWN:
                return Pawn.of(team);
            case KNIGHT:
                return Knight.of(team);
            case BISHOP:
                return Bishop.of(team);
            case ROCK:
                return Rock.of(team);
            case KING:
                return King.of(team);
            case QUEEN:
                return Queen.of(team);
            default:
                throw new IllegalArgumentException("unknown figure char name");
        }
    }
}
