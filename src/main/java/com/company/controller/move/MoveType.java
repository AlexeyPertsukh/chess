package com.company.controller.move;

import com.company.model.board.Board;
import com.company.model.board.Cell;

public abstract class MoveType {
    public abstract void verify(Board board, Cell from, Cell to);
    public abstract void execute(Board board, Cell from, Cell to);
}
