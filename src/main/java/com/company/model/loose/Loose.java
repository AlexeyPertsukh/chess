package com.company.model.loose;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.board.Way;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureRank;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Loose {
    private final Board board;

    public Loose(Board board) {
        this.board = board;
    }

    public boolean isCheck(DangerMatrix dangerMatrix, FigureColor myColor) {
        Cell kingCell = findCellKing(myColor);
        return dangerMatrix.isUnderAttack(kingCell);
    }

    public boolean isCheckmate(DangerMatrix dangerMatrix, FigureColor myColor) {
        if (!isCheck(dangerMatrix, myColor)) {
            return false;
        }
        Cell kingCell = findCellKing(myColor);
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


    private Cell findCellKing(FigureColor myColor) {
        return board.find(FigureRank.KING, myColor);
    }


}
