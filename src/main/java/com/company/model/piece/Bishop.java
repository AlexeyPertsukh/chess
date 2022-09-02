package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Bishop extends Piece {

    private static final Figure FIGURE_WHITE = Figure.BISHOP_WHITE;
    private static final Figure FIGURE_BLACK = Figure.BISHOP_BLACK;

    private Bishop(Team team) {
        super(getFigure(team));
    }

    private Bishop(Team team, int moveCount) {
        super(getFigure(team), moveCount);
    }


    public static Bishop of(Team team) {
        return new Bishop(team);
    }

    private static Figure getFigure(Team team) {
        return team == Team.WHITE ? FIGURE_WHITE : FIGURE_BLACK;
    }

    @Override
    public Bishop clone() throws CloneNotSupportedException {
        return new Bishop(getTeam(), getMoveCount());
    }
}
