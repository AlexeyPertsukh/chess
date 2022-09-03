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
        int num = (this == KNIGHT) ? 1 : 0;
        return name().charAt(num);
    }

    public static Rank getByLetter(char ch) {
        Rank[] ranks = values();
        for (Rank rank : ranks) {
            if(rank.getLetter() == ch) {
                return rank;
            }
        }

        String message = String.format("unknown rank char letter: %c", ch);
        throw new IllegalArgumentException(message);
    }
}
