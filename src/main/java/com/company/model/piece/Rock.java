package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Rock extends Piece {

    private static final Figure FIGURE_WHITE = Figure.ROCK_WHITE;
    private static final Figure FIGURE_BLACK = Figure.ROCK_BLACK;

    private Rock(Team color) {
        super(getFigure(color));
    }

    public static Rock of(Team color) {
        return new Rock(color);
    }

    private static Figure getFigure(Team color) {
        return color == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

    @Override
    protected Rock clone() throws CloneNotSupportedException {
        Rock piece = new Rock(getTeam());
        piece.moveCount = super.moveCount;
        return piece;
    }
}
