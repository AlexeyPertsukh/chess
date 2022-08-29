package com.company.model.piece.figure;

public enum Rank {
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
