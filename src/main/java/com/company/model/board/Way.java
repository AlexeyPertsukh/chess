package com.company.model.board;

import com.company.model.board.Cell;

public class Way {
    public final Cell from;
    public final Cell to;

    public Way(Cell from, Cell to) {
        this.from = from;
        this.to = to;
    }

}
