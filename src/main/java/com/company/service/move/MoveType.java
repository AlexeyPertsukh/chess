package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;

public abstract class MoveType {
    protected Board board;
    public MoveType(Board board) {
    }

    public abstract void verify(Cell from, Cell to, boolean[][] dangerArray);
    public abstract void execute(Cell from, Cell to);
}
