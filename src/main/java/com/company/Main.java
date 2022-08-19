package com.company;

import com.company.board.Board;
import com.company.figure.Color;
import com.company.figure.Figure;
import com.company.player.Player;

public class Main {

    public static void main(String[] args) {

        Board board = createBoard();
        Player player1 = new Player("Белые", Color.WHITE);
        Player player2 = new Player("Черные", Color.BLACK);

        Game game = new Game(board, player1, player2);
        game.go();


    }

    private static Board createBoard() {
        Board board = new Board();
        board.insert(Figure.PAWN_WHITE, "a2");
        board.insert(Figure.PAWN_WHITE, "b2");
        board.insert(Figure.PAWN_WHITE, "c2");
        board.insert(Figure.PAWN_WHITE, "d2");
        board.insert(Figure.PAWN_WHITE, "e2");
        board.insert(Figure.PAWN_WHITE, "f2");
        board.insert(Figure.PAWN_WHITE, "g2");
        board.insert(Figure.PAWN_WHITE, "h2");

        board.insert(Figure.ROCK_WHITE, "a1");
        board.insert(Figure.KNIGHT_WHITE, "b1");
        board.insert(Figure.BISHOP_WHITE, "c1");
        board.insert(Figure.QUEEN_WHITE, "d1");
        board.insert(Figure.KING_WHITE, "e1");
        board.insert(Figure.BISHOP_WHITE, "f1");
        board.insert(Figure.KNIGHT_WHITE, "g1");
        board.insert(Figure.ROCK_WHITE, "h1");

        board.insert(Figure.PAWN_BLACK, "a7");
        board.insert(Figure.PAWN_BLACK, "b7");
        board.insert(Figure.PAWN_BLACK, "c7");
        board.insert(Figure.PAWN_BLACK, "d7");
        board.insert(Figure.PAWN_BLACK, "e7");
        board.insert(Figure.PAWN_BLACK, "f7");
        board.insert(Figure.PAWN_BLACK, "g7");
        board.insert(Figure.PAWN_BLACK, "h7");

        board.insert(Figure.ROCK_BLACK, "a8");
        board.insert(Figure.KNIGHT_BLACK, "b8");
        board.insert(Figure.BISHOP_BLACK, "c8");
        board.insert(Figure.QUEEN_BLACK, "d8");
        board.insert(Figure.KING_BLACK, "e8");
        board.insert(Figure.BISHOP_BLACK, "f8");
        board.insert(Figure.KNIGHT_BLACK, "g8");
        board.insert(Figure.ROCK_BLACK, "h8");

        return board;
    }
}
