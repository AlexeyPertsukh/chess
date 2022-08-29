package com.company.controller;

import com.company.model.piece.figure.direction.Offset;
import com.company.model.player.Bot;
import com.company.model.player.Player;
import com.company.model.piece.*;
import com.company.model.board.Board;
import com.company.model.piece.figure.Team;
import com.company.view.ConsolePrinter;
import com.company.view.ConsoleReader;
import com.company.view.Printer;
import com.company.view.Reader;

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

        Board board = createBoard();
        Player player1 = new Player(Team.WHITE);
        Player player2 = getPlayer2();

        Game game = new Game(board, player1, player2);
        game.go();

    }

    private static Board createBoard() {
        Board board = new Board();

        for (int i = 0; i < 2; i++) {
            Team color = (i == 0) ? Team.WHITE : Team.BLACK;
            int firstLine = (i == 0) ? 1 : 8;
            int secondLine = (i == 0) ? 2 : 7;

            board.insert(Pawn.of(color), "a" + secondLine);
            board.insert(Pawn.of(color), "b" + secondLine);
            board.insert(Pawn.of(color), "c" + secondLine);
            board.insert(Pawn.of(color), "d" + secondLine);
            board.insert(Pawn.of(color), "e" + secondLine);
            board.insert(Pawn.of(color), "f" + secondLine);
            board.insert(Pawn.of(color), "g" + secondLine);
            board.insert(Pawn.of(color), "h" + secondLine);

            board.insert(Rock.of(color), "a" + firstLine);
            board.insert(Knight.of(color), "b" + firstLine);
            board.insert(Bishop.of(color), "c" + firstLine);
            board.insert(Queen.of(color), "d" + firstLine);
            board.insert(King.of(color), "e" + firstLine);
//            board.insert(Bishop.of(color), "f" + firstLine);
//            board.insert(Knight.of(color), "g" + firstLine);
            board.insert(Rock.of(color), "h" + firstLine);
        }
        return board;
    }

    private static Board createBoard1() {
        Board board = new Board();

        for (int i = 0; i < 2; i++) {
            Team color = (i == 0) ? Team.WHITE : Team.BLACK;
            int firstLine = (i == 0) ? 1 : 8;
            int secondLine = (i == 0) ? 2 : 7;

            board.insert(Rock.of(color), "a" + firstLine);
            board.insert(Knight.of(color), "b" + firstLine);
            board.insert(Bishop.of(color), "c" + firstLine);
            board.insert(Queen.of(color), "d" + firstLine);
            board.insert(King.of(color), "e" + firstLine);
            board.insert(Rock.of(color), "h" + firstLine);
        }
        return board;
    }

    private static Player getPlayer2() {
        Printer printer = new ConsolePrinter();
        Reader reader = new ConsoleReader();
        final String man = "1";
        final String comp = "2";

        String mode = "";
        while (!mode.equalsIgnoreCase("1") && !mode.equalsIgnoreCase("2")) {
            printer.printf("Input game mode (%s - man, %s - computer): ", man, comp);
            mode = reader.next();
        }
        return mode.equalsIgnoreCase(man) ? new Player(Team.BLACK) : new Bot(Team.BLACK);
    }
}
