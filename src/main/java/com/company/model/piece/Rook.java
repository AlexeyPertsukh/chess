package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Rook extends Piece {

    private static final Figure FIGURE_WHITE = Figure.ROCK_WHITE;
    private static final Figure FIGURE_BLACK = Figure.ROCK_BLACK;

    private Rook(Team team) {
        super(getFigure(team));
    }

    private Rook(Team color, int moveCount) {
        super(getFigure(color), moveCount);
    }

    public static Rook of(Team team) {
        return new Rook(team);
    }

    private static Figure getFigure(Team team) {
        return team == Team.WHITE ? FIGURE_WHITE : FIGURE_BLACK;
    }

    @Override
    public Rook clone() throws CloneNotSupportedException {
        return new Rook(getTeam(), getMoveCount());
    }
}
