package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Knight extends Piece {

    private static final Figure FIGURE_WHITE = Figure.KNIGHT_WHITE;
    private static final Figure FIGURE_BLACK = Figure.KNIGHT_BLACK;

    private Knight(Team color) {
        super(getFigure(color));
    }

    public static Knight of(Team color) {
        return new Knight(color);
    }

    private static Figure getFigure(Team color) {
        return color == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

    @Override
    protected Knight clone() throws CloneNotSupportedException {
        Knight piece = new Knight(getTeam());
        piece.moveCount = super.moveCount;
        return piece;
    }
}
