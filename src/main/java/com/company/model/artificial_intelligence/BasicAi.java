package com.company.model.artificial_intelligence;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.board.Way;
import com.company.model.danger.CheckList;
import com.company.model.danger.Danger;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureRank;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.loose.Loose;
import com.company.model.unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicAi implements Iai {

    private final Board board;

    public BasicAi(Board board) {
        this.board = board;
    }

    public Way getBestMove(Danger danger, FigureColor myColor) {
        ArrayList<Way> list;

        Loose loose = new Loose(board);
        if (loose.isCheck(danger, myColor)) {
            list = saveKingMoves(danger, myColor);

        } else {
            list = allMoves(danger, myColor);
        }

        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }


    private WayList saveKingMoves(Danger danger, FigureColor myColor) {
        WayList list = new WayList();
        List<CheckList> checkLists = danger.getCheckLists();

        Cell cellKing = board.find(FigureRank.KING, myColor);
        Unit king = board.get(cellKing);
        Offset[] offsets = king.getOffsetsMove();

        for (Offset o : offsets) {
            Cell cellTo = cellKing.sum(o);
            if (!board.isCorrect(cellTo)) {
                continue;
            }

            Unit other = board.get(cellTo);
            if (!danger.isUnderAttack(cellTo) && (other.isNull() || king.getColor() != other.getColor())) {
                if(!other.isNull()) {
                    WayList attackList = new WayList();
                    attackList.add(new Way(cellKing, cellTo));
                    return attackList;
                }

                list.add(new Way(cellKing, cellTo));
            }
        }

        return list;
    }

    private WayList allMoves(Danger danger, FigureColor myColor) {
        WayList out = new WayList();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Unit unit = board.get(j, i);
                if (!unit.isNull() && unit.getColor() == myColor) {
                    updateList(out, unit, new Cell(i, j), danger);
                }
            }

        }
        return out;
    }

    private void updateList(List<Way> list, Unit unit, Cell from, Danger danger) {
        FigureColor myColor = unit.getColor();
        Distance distance = unit.getDistance();
        Offset[] offsets = unit.getOffsetsMove();

        for (Offset o : offsets) {

            Cell check = from;
            while (true) {

                check = check.sum(o);
                if (!board.isCorrect(check)) {
                    break;
                }

                Unit other = board.get(check);
                if (!other.isNull() && other.getColor() == myColor) {
                    break;
                }

                list.add(new Way(from, check));

                if (!other.isNull() || distance == Distance.ONE) {
                    break;
                }
            }
        }
    }


}
