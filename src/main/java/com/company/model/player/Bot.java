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
}
