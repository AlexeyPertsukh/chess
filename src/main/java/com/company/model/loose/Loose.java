package com.company.model.loose;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

public class Loose {
    private final Board board;

    public Loose(Board board) {
        this.board = board;
    }

    public boolean isShah(DangerMatrix dangerMatrix, FigureColor myColor) {
        Cell kingCell = findCellKingByColor(myColor);
        return dangerMatrix.isUnderAttack(kingCell);
    }

    public boolean isCheckmate(DangerMatrix dangerMatrix, FigureColor myColor) {
        if (!isShah(dangerMatrix, myColor)) {
            return false;
        }
        Cell kingCell = findCellKingByColor(myColor);
        return !isKingHasMoves(dangerMatrix, myColor, kingCell);
    }

    private boolean isKingHasMoves(DangerMatrix dangerMatrix, FigureColor myColor, Cell kingCell) {
        Unit king = board.get(kingCell);
        Offset[] offsets = king.getOffsetsMove();
        for (Offset o : offsets) {
            Cell to = kingCell.sum(o);
            if (!board.isCorrect(to)) {
                continue;
            }
            Unit other = board.get(to);
            if ((other.isNull() || other.getColor() != myColor) && !dangerMatrix.isUnderAttack(to)) {
                return true;
            }
        }
        return false;
    }


    private Cell findCellKingByColor(FigureColor myColor) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Unit unit = board.get(j, i);
                if (!unit.isNull() && unit.isKing() && unit.getColor() == myColor) {
                    return new Cell(i, j);
                }
            }
        }

        String message = String.format("the %s king is missing from the board", myColor.toString().toLowerCase());
        throw new IllegalArgumentException(message);
    }


}
