package com.company.model.piece.figure;

public enum Team {
    WHITE,
    BLACK;

    public char getLetter() {
        return name().charAt(0);
    }

    public static Team getByLetter(char ch) {
        switch (ch) {
            case 'W': return WHITE;
            case 'B': return BLACK;
            default:
                String message = String.format("unknown team char letter: %c", ch);
                throw new IllegalArgumentException(message);
        }
    }
}
