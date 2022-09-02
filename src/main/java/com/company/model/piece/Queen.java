package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Queen extends Piece {

    private static final Figure FIGURE_WHITE = Figure.QUEEN_WHITE;
    private static final Figure FIGURE_BLACK = Figure.QUEEN_BLACK;

    private Queen(Team team) {
        super(getFigure(team));
    }

    private Queen(Team team, int moveCount) {
        super(getFigure(team), moveCount);
    }

    public static Queen of(Team team) {
        return new Queen(team);
    }

    private static Figure getFigure(Team team) {
        return team == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

    @Override
    public Queen clone() throws CloneNotSupportedException {
        return new Queen(getTeam(), getMoveCount());
    }
}
