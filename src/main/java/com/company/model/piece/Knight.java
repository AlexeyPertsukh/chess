package com.company.model.piece;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;

public class Knight extends Piece {

    private static final Figure FIGURE_WHITE = Figure.KNIGHT_WHITE;
    private static final Figure FIGURE_BLACK = Figure.KNIGHT_BLACK;

    private Knight(FigureColor color) {
        super(getFigure(color));
    }

    public static Knight of(FigureColor color) {
        return new Knight(color);
    }

    private static Figure getFigure(FigureColor color) {
        return color == FigureColor.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
