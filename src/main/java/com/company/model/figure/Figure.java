package com.company.model.figure;

public enum Figure {

    NULL(' ', null),

    PAWN_WHITE('♟', FigureColor.WHITE),
    KNIGHT_WHITE('♞', FigureColor.WHITE),
    BISHOP_WHITE('♝', FigureColor.WHITE),
    ROCK_WHITE('♜', FigureColor.WHITE),
    QUEEN_WHITE('♛', FigureColor.WHITE),
    KING_WHITE('♚', FigureColor.WHITE),

    PAWN_BLACK('♙', FigureColor.BLACK),
    KNIGHT_BLACK('♘', FigureColor.BLACK),
    BISHOP_BLACK('♗', FigureColor.BLACK),
    ROCK_BLACK('♖', FigureColor.BLACK),
    QUEEN_BLACK('♕', FigureColor.BLACK),
    KING_BLACK('♔', FigureColor.BLACK),
    ;


    private final char coat;
    private final FigureColor color;


    Figure(char coat, FigureColor color) {
        this.coat = coat;
        this.color = color;

    }

    public char getCoat() {
        return coat;
    }

    public boolean isNull() {
        return this == NULL;
    }

    public FigureColor getColor() {
        return color;
    }

}
