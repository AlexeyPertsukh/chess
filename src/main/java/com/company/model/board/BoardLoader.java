package com.company.model.board;

import com.company.model.piece.figure.Rank;
import com.company.model.piece.figure.Team;
import com.company.model.piece.*;

public class BoardLoader {
    public static final String SPLIT = ",";
    public static final char CHAR_WHITE = 'w';
    public static final char CHAR_BLACK = 'b';

    private BoardLoader() {
    }

    public static Board boardOf(String string) {

        String[] array = string.toLowerCase().replace(" ", "").split(SPLIT);

        Board board = new Board();
        for (String s : array) {
            Piece piece = toUnit(s.substring(0, 2));
            board.insert(piece, s.substring(2));
        }

        return board;
    }

    protected static Team getTeam(char ch) {
        ch = Character.toLowerCase(ch);

        if (ch == CHAR_WHITE) {
            return Team.WHITE;
        } else if (ch == CHAR_BLACK) {
            return Team.BLACK;
        }
        throw new IllegalArgumentException("unknown color char name");
    }

    protected static Piece toUnit(String s) {
        Team color = getTeam(s.charAt(1));
        return toUnit(s.charAt(0), color);
    }

    protected static Piece toUnit(char ch, Team team) {
        ch = Character.toUpperCase(ch);
        Rank rank = Rank.getByLetter(ch);
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
