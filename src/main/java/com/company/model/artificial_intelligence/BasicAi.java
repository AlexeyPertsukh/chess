package com.company.model.artificial_intelligence;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.board.Way;
import com.company.model.danger.Danger;
import com.company.model.figure.FigureColor;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.loose.Loose;
import com.company.model.unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicAi implements IAi {

    private final Board board;

    public BasicAi(Board board) {
        this.board = board;
    }

    public Way getBestMove(Danger danger, FigureColor myColor) {
        List<Way> list;

        Loose loose = new Loose(board);
        if (loose.isCheck(danger, myColor)) {
            list = movesToAvoidCheck(danger, myColor);
        } else {
            list = allMoves(danger, myColor);
        }

        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private List<Way> movesToAvoidCheck(Danger danger, FigureColor myColor) {
        List<Way> out = new ArrayList<>();

        return out;
    }


    private List<Way> allMoves(Danger danger, FigureColor myColor) {
        List<Way> out = new ArrayList<>();

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
