package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Rock extends Piece {

    private static final Figure FIGURE_WHITE = Figure.ROCK_WHITE;
    private static final Figure FIGURE_BLACK = Figure.ROCK_BLACK;

    private Rock(Team team) {
        super(getFigure(team));
    }

    private Rock(Team color, int moveCount) {
        super(getFigure(color), moveCount);
    }

    public static Rock of(Team team) {
        return new Rock(team);
    }

    private static Figure getFigure(Team team) {
        return team == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

    @Override
    public Rock clone() throws CloneNotSupportedException {
        return new Rock(getTeam(), getMoveCount());
    }
}
