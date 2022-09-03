package com.company.model.board;

import com.company.model.piece.*;

public class BoardFactory {
    private static final String MARKER = "failed create board";

    public static final String SPLIT = ",";

    private BoardFactory() {
    }

    public static Board createdOf(String string) {
        String[] array = string.toLowerCase().replace(" ", "").split(SPLIT);
        Board board = new Board();
        for (String s : array) {
            verify(s);
            Piece piece = PieceFactory.createOf(s.substring(0, 2));
            board.insert(piece, s.substring(2));
        }

        return board;
    }

    private static void verify(String s) {
        if(s.length() != 4) {
            String message = String.format("%s by string '%s'", MARKER, s);
            throw new IllegalArgumentException(message);
        }
    }

}
