package test_util;

import com.company.model.board.Board;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureRank;
import com.company.model.unit.*;

public class BoardUtil {

    private static final String ERR = "BoardUtil ERROR";

    private BoardUtil() {
    }

    public static Board getBoard(String string) {
        String[] strings = string.toLowerCase().replace(" ", "").split(",");
        Board board = new Board();
        for (String s : strings) {
            FigureColor color = getColor(s.charAt(1));
            Unit unit = getUnit(s.charAt(0), color);
            board.insert(unit, s.substring(2));
        }
        return board;
    }

    private static FigureColor getColor(char ch) {
        if (ch == 'w') {
            return FigureColor.WHITE;
        } else if (ch == 'b') {
            return FigureColor.BLACK;
        }
        throw new IllegalArgumentException(ERR + " getColor");
    }

    private static Unit getUnit(char ch, FigureColor color) {
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
                throw new IllegalArgumentException(ERR + " getUnit");
        }
    }
}
