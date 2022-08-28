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

    public static Board boardOf(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);
            if(i < strings.length - 1) {
                builder.append(",");
            }
        }

        String[] array = builder.toString().toLowerCase().replace(" ", "").split(SPLIT);
        Board board = new Board();
        for (String s : strings) {
            Unit unit = toUnit(s);
            board.insert(unit, s.substring(2));
        }

        return board;
    }

    protected static FigureColor getColor(char ch) {
        ch = Character.toLowerCase(ch);

        if (ch == CHAR_BLACK) {
            return FigureColor.WHITE;
        } else if (ch == CHAR_WHITE) {
            return FigureColor.BLACK;
        }
        throw new IllegalArgumentException("unknown color char name");
    }

    protected static Unit toUnit(String s) {
        FigureColor color = getColor(s.charAt(1));
        return toUnit(s.charAt(0), color);
    }

    protected static Unit toUnit(char ch, FigureColor color) {
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