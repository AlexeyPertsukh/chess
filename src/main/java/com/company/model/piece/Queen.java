package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class Queen extends Piece {

    private static final Figure FIGURE_WHITE = Figure.QUEEN_WHITE;
    private static final Figure FIGURE_BLACK = Figure.QUEEN_BLACK;

    private Queen(Team color) {
        super(getFigure(color));
    }

    public static Queen of(Team color) {
        return new Queen(color);
    }

    private static Figure getFigure(Team color) {
        return color == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
