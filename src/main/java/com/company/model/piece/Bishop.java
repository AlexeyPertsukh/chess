package com.company.model.piece;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;

public class Bishop extends Piece {

    private static final Figure FIGURE_WHITE = Figure.BISHOP_WHITE;
    private static final Figure FIGURE_BLACK = Figure.BISHOP_BLACK;

    private Bishop(FigureColor color) {
        super(getFigure(color));
    }

    public static Bishop of(FigureColor color) {
        return new Bishop(color);
    }

    private static Figure getFigure(FigureColor color) {
        return color == FigureColor.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
