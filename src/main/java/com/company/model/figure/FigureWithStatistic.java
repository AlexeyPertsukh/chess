package com.company.model.figure;

public class FigureWithStatistic{
    private final Figure figure;
    private final FigureColor color;
    private int moveCount;

    public FigureWithStatistic(Figure figure, FigureColor color) {
        this.figure = figure;
        this.color = color;
    }

    public Figure getFigure() {
        return figure;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void incMoveCount() {
        moveCount++;
    }

    public boolean isMoved() {
        return moveCount > 0;
    }

    public boolean isNull() {
        return false;
    }

    public FigureColor getColor() {
        return color;
    }

    public boolean isPawn() {
        return figure == Figure.PAWN;
    }

    public  boolean isRock() {
        return figure == Figure.ROCK;
    }

    public  boolean isKnight() {
        return figure == Figure.KNIGHT;
    }

    public  boolean isBishop() {
        return figure == Figure.BISHOP;
    }

    public  boolean isQueen() {
        return figure == Figure.QUEEN;
    }

    public  boolean isKing() {
        return figure == Figure.KING;
    }

    public char getIcon() {
        FigureColor[] colors = FigureColor.values();
        for (int i = 0; i < colors.length; i++) {
            if(colors[i] == color) {
                return figure.getIconByNum(i);
            }
        }

        throw new IllegalArgumentException("unknown color by get icon from figure");
    }


}
