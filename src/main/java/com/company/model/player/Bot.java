package com.company.model.player;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;
import com.company.model.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Bot extends Player {
    private final Board board;

    public Bot(String name, FigureColor color, Board board) {
        super(name, color);
        this.board = board;
    }

    public Bot(FigureColor color, Board board) {
        this("Bot", color, board);
    }

    protected List<PossibleMove> allPossibleMoves(FigureColor myColor, DangerMatrix dangerMatrix) {
        List<PossibleMove> out = new ArrayList<>();

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Unit unit = board.get(j, i);
                if (!unit.isNull() && unit.getColor() == myColor) {
                    updateList(out, unit, new Cell(j, i));
                }
            }

        }
        return out;
    }

    private void updateList(List<PossibleMove> list, Unit unit, Cell from) {
        FigureColor myColor = unit.getColor();
        Distance distance = unit.getDistance();
        Offset[] offsets = unit.getOffsetsAttack();

        for (Offset o : offsets) {

            Cell check = from;
            while (true) {

                check = check.sum(o);
                if (!board.isCorrect(check)) {
                    break;
                }

                Unit other = board.get(check);
                if(!other.isNull() && other.getColor() == myColor) {
                    break;
                }

                list.add(new PossibleMove(from, check, getUnitValue(other, myColor)));

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

        int mul = unit.getColor() == myColor ? -1 : 1;
        switch (unit.getRank()) {
            case PAWN:
                return 10 * mul;
            case KNIGHT:
            case BISHOP:
                return 30 * mul;
            case ROCK:
                return 50 * mul;
            case QUEEN:
                return 90 * mul;
            case KING:
                return 900 * mul;
            default:
                throw new IllegalArgumentException("unknown figure rank");
        }

    }

    public static class PossibleMove {
        public final Cell from;
        public final Cell to;
        public final int value;

        public PossibleMove(Cell from, Cell to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public String toString() {
            return "PossibleMove{" +
                    "from=" + from +
                    ", to=" + to +
                    ", value=" + value +
                    '}';
        }
    }


}
