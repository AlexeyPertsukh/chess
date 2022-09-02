package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Knight extends Piece {

    private static final Figure FIGURE_WHITE = Figure.KNIGHT_WHITE;
    private static final Figure FIGURE_BLACK = Figure.KNIGHT_BLACK;

    private Knight(Team team) {
        super(getFigure(team));
    }

    private Knight(Team team, int moveCount) {
        super(getFigure(team), moveCount);
    }

    public static Knight of(Team team) {
        return new Knight(team);
    }

    private static Figure getFigure(Team team) {
        return team == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

    @Override
    public Knight clone() throws CloneNotSupportedException {
        return new Knight(getTeam(), getMoveCount());
    }
}
