package com.company.model.figure;

public enum FigureRank {
    PAWN,
    ROCK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING,
    ;

    public char getLetter() {
        int num = this == KNIGHT ? 1 : 0;
        return name().charAt(num);
    }
}
