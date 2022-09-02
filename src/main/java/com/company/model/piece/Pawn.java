package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Pawn extends Piece {

    private static final Figure FIGURE_WHITE = Figure.PAWN_WHITE;
    private static final Figure FIGURE_BLACK = Figure.PAWN_BLACK;

    private Pawn(Team color) {
        super(getFigure(color));
    }

    public static Pawn of(Team color) {
        return new Pawn(color);
    }

    private static Figure getFigure(Team color) {
        return color == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

    @Override
    protected Pawn clone() throws CloneNotSupportedException {
        Pawn piece = new Pawn(getTeam());
        piece.moveCount = super.moveCount;
        return piece;
    }
}
