package com.company.model.piece;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;

public class Pawn extends Piece {

    private static final Figure FIGURE_WHITE = Figure.PAWN_WHITE;
    private static final Figure FIGURE_BLACK = Figure.PAWN_BLACK;

    private Pawn(FigureColor color) {
        super(getFigure(color));
    }

    public static Pawn of(FigureColor color) {
        return new Pawn(color);
    }

    private static Figure getFigure(FigureColor color) {
        return color == FigureColor.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
