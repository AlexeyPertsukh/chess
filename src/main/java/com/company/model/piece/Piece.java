package com.company.model.piece;

import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureRank;
import com.company.model.figure.direction.Distance;
import com.company.model.figure.direction.Offset;

public class Piece implements INull {
    private final Figure figure;
    private int moveCount = 0;

    public Piece(Figure figure) {
        this.figure = figure;
    }

    public char getIcon() {
        return figure.getIcon();
    }

    public int getMoveCount() {
        return moveCount;
    }

    public Offset[] getOffsetsMove() {
        return figure.getDirection().getOffsetsMove();
    }

    public Offset[] getOffsetsAttack() {
        return figure.getDirection().getOffsetsAttack();
    }

    public Distance getDistance() {
        return figure.getDirection().getDistance();
    }

    public FigureRank getRank() {
        return figure.getRank();
    }

    public char getLetter() {
        return figure.getLetter();
    }

    public boolean isPawn() {
        return figure.getRank() == FigureRank.PAWN;
    }

    public  boolean isRock() {
        return figure.getRank() == FigureRank.ROCK;
    }

    public  boolean isKnight() {
        return figure.getRank() == FigureRank.KNIGHT;
    }

    public  boolean isBishop() {
        return figure.getRank() == FigureRank.BISHOP;
    }

    public  boolean isQueen() {
        return figure.getRank() == FigureRank.QUEEN;
    }

    public  boolean isKing() {
        return figure.getRank() == FigureRank.KING;
    }

    public FigureColor getColor() {
        return figure.getColor();
    }

    public void incMoveCount() {
        moveCount++;
    }

    public boolean isMoved() {
        return moveCount > 0;
    }

    @Override
    public boolean isNull() {
        return false;
    }

}
