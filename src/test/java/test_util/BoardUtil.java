package test_util;

import com.company.model.board.Board;
import com.company.model.figure.FigureColor;
import com.company.model.unit.*;

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
            Unit unit = toUnit(s.substring(0, 2));
            board.insert(unit, s.substring(2));
        }

        return board;
    }

    protected static FigureColor getColor(char ch) {
        ch = Character.toLowerCase(ch);

        if (ch == CHAR_WHITE) {
            return FigureColor.WHITE;
        } else if (ch == CHAR_BLACK) {
            return FigureColor.BLACK;
        }
        throw new IllegalArgumentException("unknown color char name");
    }

    public static Unit toUnit(String s) {
        FigureColor color = getColor(s.charAt(1));
        return toUnit(s.charAt(0), color);
    }

    public static Unit toUnit(char ch, FigureColor color) {
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
