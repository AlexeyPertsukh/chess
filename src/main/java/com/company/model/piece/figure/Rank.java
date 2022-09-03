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

    public static Rank getByLetter(char ch) {
        Rank[] ranks = values();
        for (Rank rank : ranks) {
            if(rank.getLetter() == ch) {
                return rank;
            }
        }

        throw new IllegalArgumentException("unknown rank char letter");
    }
}
