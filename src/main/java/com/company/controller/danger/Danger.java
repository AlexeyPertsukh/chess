package com.company.controller.danger;

import com.company.model.attack_direction.AttackDirection;
import com.company.model.attack_direction.Offset;
import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureWithStatistic;

import java.util.HashMap;

public class Danger {
    private static final HashMap<Figure, AttackDirection> map = getMap();

    public boolean[][] toArray(Board board, FigureColor color) {
        boolean[][] array = new boolean[Board.SIZE][Board.SIZE];

        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                Cell cell = new Cell(j, i);
                FigureWithStatistic figure = board.get(cell);

                if(!figure.isNull() && figure.getColor() == color) {
                    action(board, cell, array);
                }
            }

        }


        return array;
    }

    private void action(Board board, Cell cell, boolean[][] array) {
        FigureWithStatistic figure = board.get(cell);
        FigureColor color = figure.getColor();

        Offset[] attack = getOffsets(figure);
    }

    private Offset[] getOffsets(FigureWithStatistic figure) {
        if(figure.isPawn()) {
            return figure.getColor() == FigureColor.WHITE ?
                    AttackDirection.PAWN_WHITE.getOffsets() : AttackDirection.PAWN_BLACK.getOffsets();
        }

        AttackDirection direction = map.get(figure.getFigure());
        return direction.getOffsets();
    }

    private static HashMap<Figure, AttackDirection> getMap() {
        HashMap<Figure, AttackDirection> map = new HashMap<>();
        map.put(Figure.ROCK, AttackDirection.ROCK);
        map.put(Figure.KNIGHT, AttackDirection.KNIGHT);
        map.put(Figure.BISHOP, AttackDirection.BISHOP);
        map.put(Figure.QUEEN, AttackDirection.QUEEN);
        map.put(Figure.KING, AttackDirection.KING);
        return map;
    }




}
