package com.company.model.piece;

import com.company.model.piece.figure.Figure;
import com.company.model.piece.figure.Team;

public class King extends Piece {

    private static final Figure FIGURE_WHITE = Figure.KING_WHITE;
    private static final Figure FIGURE_BLACK = Figure.KING_BLACK;

    private King(Team color) {
        super(getFigure(color));
    }

    public static King of(Team color) {
        return new King(color);
    }

    private static Figure getFigure(Team color) {
        return color == Team.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
