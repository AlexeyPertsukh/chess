package com.company.model.board;

import com.company.model.piece.figure.direction.Offset;

public class BoardHelper {
    private BoardHelper() {
    }

    public static boolean isObstacleOnLine(Board board, Cell first, Cell second) {
        if (!isOneLine(first, second)) {
            String message = "pieces are not on the same line";
            throw new IllegalArgumentException(message);
        }
        int offsetColumn = second.column - first.column;
        offsetColumn = sign(offsetColumn);

        int offsetRow = second.row - first.row;
        offsetRow = sign(offsetRow);

        Offset offset = new Offset(offsetColumn, offsetRow);

        Cell check = first.sum(offset);

        while(!check.equals(second)) {

            if(!board.get(check).isNull()) {
                return true;
            }

            check = check.sum(offset);
        }

        return false;
    }

    public static boolean isObstacleOnLine(Board board, String first, String second) {
        return isObstacleOnLine(board, Board.toCell(first), Board.toCell(second));
    }

    public static boolean isOneLine(Cell first, Cell second) {
        return first.row == second.row || first.column == second.column;
    }

    private static int sign(int num) {
        if (num == 0) {
            return 0;
        }
        return num > 0 ? 1 : -1;
    }
}
