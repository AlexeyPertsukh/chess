package com.company.controller.danger;

import com.company.model.direction.Direction;
import com.company.model.direction.Offset;
import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureWithStatistic;

import java.util.HashMap;

public class Danger {
    private static final HashMap<Figure, Direction> map = getMap();

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
                    Direction.PAWN_WHITE.getOffsetsMove() : Direction.PAWN_BLACK.getOffsetsMove();
        }

        Direction direction = map.get(figure.getFigure());
        return direction.getOffsetsMove();
    }

    private static HashMap<Figure, Direction> getMap() {
        HashMap<Figure, Direction> map = new HashMap<>();
        map.put(Figure.ROCK, Direction.ROCK);
        map.put(Figure.KNIGHT, Direction.KNIGHT);
        map.put(Figure.BISHOP, Direction.BISHOP);
        map.put(Figure.QUEEN, Direction.QUEEN);
        map.put(Figure.KING, Direction.KING);
        return map;
    }




}
