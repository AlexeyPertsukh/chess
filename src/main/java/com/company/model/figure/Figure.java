package com.company.model.figure;

public enum Figure {
    PAWN('♟', '♙'),
    ROCK('♜','♖'),
    KNIGHT('♞', '♘'),
    BISHOP('♝', '♗'),
    QUEEN('♛', '♕'),
    KING('♚', '♔'),
    ;

    private final char[] icons;

    Figure(char... icons) {
        this.icons = icons;
    }

    public char getIconByNum(int num) {
        return icons[num];
    }


}
