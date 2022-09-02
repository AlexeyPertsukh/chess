package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Bishop extends Piece {

    private static final Figure FIGURE_WHITE = Figure.BISHOP_WHITE;
    private static final Figure FIGURE_BLACK = Figure.BISHOP_BLACK;

    private Bishop(Team team) {
        super(getFigure(team));
    }

    public static Bishop of(Team color) {
        return new Bishop(color);
    }

    private static Figure getFigure(Team color) {
        return color == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

    @Override
    protected Bishop clone() throws CloneNotSupportedException {
        Bishop piece = new Bishop(getTeam());
        piece.moveCount = super.moveCount;
        return piece;
    }
}
