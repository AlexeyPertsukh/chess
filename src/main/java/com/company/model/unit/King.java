package com.company.model.unit;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;

public class King extends Unit {

    private static final Figure FIGURE_WHITE = Figure.KING_WHITE;
    private static final Figure FIGURE_BLACK = Figure.KING_BLACK;

    private King(FigureColor color) {
        super(getFigure(color));
    }

    public static King of(FigureColor color) {
        return new King(color);
    }

    private static Figure getFigure(FigureColor color) {
        return color == FigureColor.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
