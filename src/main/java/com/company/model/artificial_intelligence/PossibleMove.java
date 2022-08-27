package com.company.model.artificial_intelligence;

import com.company.model.board.Cell;

public class PossibleMove implements Comparable<PossibleMove> {
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

    @Override
    public int compareTo(PossibleMove other) {
        return value - other.value;
    }
}
