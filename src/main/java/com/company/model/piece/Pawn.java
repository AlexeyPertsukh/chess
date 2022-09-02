package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Pawn extends Piece {

    private static final Figure FIGURE_WHITE = Figure.PAWN_WHITE;
    private static final Figure FIGURE_BLACK = Figure.PAWN_BLACK;

    private Pawn(Team team) {
        super(getFigure(team));
    }

    private Pawn(Team team, int moveCount) {
        super(getFigure(team), moveCount);
    }

    public static Pawn of(Team team) {
        return new Pawn(team);
    }

    private static Figure getFigure(Team team) {
        return team == Team.WHITE ? FIGURE_WHITE : FIGURE_BLACK;
    }

    @Override
    protected Pawn clone() throws CloneNotSupportedException {
        return new Pawn(getTeam(), getMoveCount());
    }
}
