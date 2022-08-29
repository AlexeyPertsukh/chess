package com.company.model.piece;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;

public class Rock extends Piece {

    private static final Figure FIGURE_WHITE = Figure.ROCK_WHITE;
    private static final Figure FIGURE_BLACK = Figure.ROCK_BLACK;

    private Rock(FigureColor color) {
        super(getFigure(color));
    }

    public static Rock of(FigureColor color) {
        return new Rock(color);
    }

    private static Figure getFigure(FigureColor color) {
        return color == FigureColor.WHITE ? FIGURE_WHITE :FIGURE_BLACK;
    }

}
