package com.company.model.artificial_intelligence;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Ai {
    private final Board board;

    public Ai(Board board) {
        this.board = board;
    }

    public PossibleMove getBestMove(FigureColor myColor, DangerMatrix dangerMatrix) {
        List<PossibleMove> list = allPossibleMoves(myColor, dangerMatrix);
        Collections.sort(list);
        int maxValue = list.get(0).value;
        List<PossibleMove> filtered = list.stream()
                .filter(v -> v.value == maxValue)
                .collect(Collectors.toList());

        Random random = new Random();
        int index = random.nextInt(filtered.size());
        return filtered.get(index);
    }

    protected List<PossibleMove> allPossibleMoves(FigureColor myColor, DangerMatrix dangerMatrix) {
        List<PossibleMove> out = new ArrayList<>();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Unit unit = board.get(j, i);
                if (!unit.isNull() && unit.getColor() == myColor) {
                    updateList(out, unit, new Cell(i, j), dangerMatrix);
                }
            }

        }
        return out;
    }

    private void updateList(List<PossibleMove> list, Unit unit, Cell from, DangerMatrix dangerMatrix) {
        FigureColor myColor = unit.getColor();
        Distance distance = unit.getDistance();
        Offset[] offsets = unit.getOffsetsMove();
        int unitValue = getUnitValue(unit, myColor);

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

                int value = getUnitValue(other, myColor);
                if (dangerMatrix.isUnderAttack(check)) {
                    value -= unitValue;
                }

                list.add(new PossibleMove(from, check, value));

                if (!other.isNull() || distance == Distance.ONE) {
                    break;
                }
            }
        }
    }

    private static int getUnitValue(Unit unit, FigureColor myColor) {
        if (unit.isNull()) {
            return 0;
        }

//        int mul = unit.getColor() == myColor ? -1 : 1;
        switch (unit.getRank()) {
            case PAWN:
                return 10;
            case KNIGHT:
            case BISHOP:
                return 30;
            case ROCK:
                return 50;
            case QUEEN:
                return 90;
            case KING:
                return 900;
            default:
                throw new IllegalArgumentException("unknown figure rank");
        }

    }

}
