package com.company.model.piece.figure;

public enum Rank {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING,
    ;

    public char getLetter() {
        int num = (this == KNIGHT) ? 1 : 0;
        return name().charAt(num);
    }

    public static Rank getByLetter(char letter) {
        letter = Character.toUpperCase(letter);
        Rank[] ranks = values();
        for (Rank rank : ranks) {
            if(rank.getLetter() == letter) {
                return rank;
            }
        }

        String message = String.format("unknown rank char letter: %c", letter);
        throw new IllegalArgumentException(message);
    }
}
