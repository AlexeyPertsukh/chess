package com.company.model.unit;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;

public class Queen extends Unit {

    private static final Figure FIGURE_WHITE = Figure.QUEEN_WHITE;
    private static final Figure FIGURE_BLACK = Figure.QUEEN_BLACK;

    private Queen(FigureColor color) {
        super(getFigure(color));
    }

    public static Queen of(FigureColor color) {
        return new Queen(color);
    }

    private static Figure getFigure(FigureColor color) {
        return color == FigureColor.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
