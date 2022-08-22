package com.company.controller;

import com.company.model.danger.Danger;
import com.company.model.figure.direction.Offset;
import com.company.model.player.Player;
import com.company.model.unit.*;
import com.company.model.board.Board;
import com.company.model.figure.FigureColor;

public class Main {

    private static void print(Offset[] os) {
        for (Offset o : os) {
            System.out.print(o.column + " : " + o.row + ", ");
        }
        System.out.println();
        System.out.println("---------------");
        System.out.println();
    }

    private static void print(boolean[][] array) {
        for (boolean[] ar : array) {
            for (boolean b : ar) {
                String s = b? " 1 " : " 0 ";
                System.out.print(s);
            }
            System.out.println();
        }
        System.out.println("---------------");
        System.out.println();
    }

    public static void main(String[] args) {
//        Board board = createBoard();
//        Danger danger = new Danger(board);
//        boolean[][] array = danger.toArray(FigureColor.WHITE);
//        print(array);


        Board board = createBoard();
        Player player1 = new Player(FigureColor.WHITE);
        Player player2 = new Player(FigureColor.BLACK);

        Game game = new Game(board, player1, player2);
        game.go();

    }

    private static Board createBoard() {
        Board board = new Board();

        for (int i = 0; i < 2; i++) {
            FigureColor color = (i == 0) ? FigureColor.WHITE : FigureColor.BLACK;
            int firstLine = (i == 0) ? 1 : 8;
            int secondLine = (i == 0) ? 2 : 7;

//            board.insert(Pawn.of(color), "a" + secondLine);
//            board.insert(Pawn.of(color), "b" + secondLine);
//            board.insert(Pawn.of(color), "c" + secondLine);
//            board.insert(Pawn.of(color), "d" + secondLine);
//            board.insert(Pawn.of(color), "e" + secondLine);
//            board.insert(Pawn.of(color), "f" + secondLine);
//            board.insert(Pawn.of(color), "g" + secondLine);
//            board.insert(Pawn.of(color), "h" + secondLine);

            board.insert(Rock.of(color), "a" + firstLine);
//            board.insert(Knight.of(color), "b" + firstLine);
//            board.insert(Bishop.of(color), "c" + firstLine);
            board.insert(Queen.of(color), "d" + firstLine);
            board.insert(King.of(color), "e" + firstLine);
//            board.insert(Bishop.of(color), "f" + firstLine);
//            board.insert(Knight.of(color), "g" + firstLine);
            board.insert(Rock.of(color), "h" + firstLine);
        }
        return board;
    }
}
