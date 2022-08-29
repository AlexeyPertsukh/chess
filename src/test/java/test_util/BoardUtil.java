package test_util;

import com.company.model.board.Board;
import com.company.model.piece.figure.Team;
import com.company.model.piece.*;

public class BoardUtil {
    public static final String SPLIT = ",";
    public static final char CHAR_WHITE = 'w';
    public static final char CHAR_BLACK = 'b';

    public BoardUtil() {
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

    protected static Team getColor(char ch) {
        ch = Character.toLowerCase(ch);

        if (ch == CHAR_WHITE) {
            return Team.WHITE;
        } else if (ch == CHAR_BLACK) {
            return Team.BLACK;
        }
        throw new IllegalArgumentException("unknown color char name");
    }

    public static Piece toUnit(String s) {
        Team color = getColor(s.charAt(1));
        return toUnit(s.charAt(0), color);
    }

    public static Piece toUnit(char ch, Team color) {
        ch = Character.toUpperCase(ch);
        switch (ch) {
            case 'P':
                return Pawn.of(color);
            case 'N':
                return Knight.of(color);
            case 'B':
                return Bishop.of(color);
            case 'R':
                return Rock.of(color);
            case 'K':
                return King.of(color);
            case 'Q':
                return Queen.of(color);
            default:
                throw new IllegalArgumentException("unknown figure char name");
        }
    }
}
