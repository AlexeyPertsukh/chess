package com.company.model.loose;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.danger.Danger;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureRank;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

public class Loose {
    private final Board board;

    public Loose(Board board) {
        this.board = board;
    }

    public boolean isCheck(Danger danger, FigureColor myColor) {
        Cell kingCell = findCellKing(myColor);
        return danger.isUnderAttack(kingCell);
    }

    public boolean isCheckmate(Danger danger, FigureColor myColor) {
        if (!isCheck(danger, myColor)) {
            return false;
        }
        Cell kingCell = findCellKing(myColor);
        return !isKingHasMoves(danger, myColor, kingCell);
    }

    private boolean isKingHasMoves(Danger danger, FigureColor myColor, Cell kingCell) {
        Unit king = board.get(kingCell);
        Offset[] offsets = king.getOffsetsMove();
        for (Offset o : offsets) {
            Cell to = kingCell.sum(o);
            if (!board.isCorrect(to)) {
                continue;
            }
            Unit other = board.get(to);
            if ((other.isNull() || other.getColor() != myColor) && !danger.isUnderAttack(to)) {
                return true;
            }
        }
        return false;
    }


    private Cell findCellKing(FigureColor myColor) {
        return board.find(FigureRank.KING, myColor);
    }


}
