package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class King extends Piece {

    private static final Figure FIGURE_WHITE = Figure.KING_WHITE;
    private static final Figure FIGURE_BLACK = Figure.KING_BLACK;

    private King(Team team) {
        super(getFigure(team));
    }

    private King(Team team, int moveCount) {
        super(getFigure(team), moveCount);
    }

    public static King of(Team team) {
        return new King(team);
    }

    private static Figure getFigure(Team team) {
        return team == Team.WHITE ? FIGURE_WHITE : FIGURE_BLACK;
    }

    @Override
    public King clone() throws CloneNotSupportedException {
        return new King(getTeam(), getMoveCount());
    }
}
