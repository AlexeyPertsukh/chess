package com.company.service.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;
import com.company.model.danger.DangerMatrix;

public abstract class MoveType {
    protected Board board;
    public MoveType(Board board) {
        this.board = board;
    }

    public abstract void verify(Cell from, Cell to, DangerMatrix dangerMatrix);
    public abstract void execute(Cell from, Cell to);
}
