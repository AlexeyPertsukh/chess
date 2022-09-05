package com.company.model.piece.figure;

public enum Team {
    WHITE,
    BLACK;

    public char getLetter() {
        return name().charAt(0);
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
