package com.company.model.board;

import com.company.model.piece.*;

public class BoardFactory {
    private static final String MARKER = "failed create board";

    public static final String SPLIT = ",";

    private BoardFactory() {
    }

    public static Board createdOf(String string) {
        String[] array = string.replace(" ", "").split(SPLIT);
        Board board = new Board();
        for (String s : array) {
            verify(s);
            Piece piece = PieceFactory.createOf(s.charAt(0));
            board.insert(piece, s.substring(1));
        }

        return board;
    }

    private static void verify(String s) {
        if (s.length() != 3) {
            String message = String.format("%s: illegal string '%s' for create piece", MARKER, s);
            throw new IllegalArgumentException(message);
        }
    }

}
