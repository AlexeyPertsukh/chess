package com.company.controller;

import com.company.model.board.Board;
import com.company.model.figure.Figure;
import com.company.model.player.Player;
import com.company.view.ConsolePrinter;
import com.company.view.ConsoleReader;
import com.company.view.Printer;
import com.company.view.Reader;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player current;
    private final Printer printer = new ConsolePrinter();
    private final Reader reader = new ConsoleReader();

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        current = player1;
    }

    public void go() {
        printer.print(board);
        while (true) {
            printer.printf("%s, ваш ход: ", current.getName());
            String command = reader.next();
            printer.println();
            boolean result = executeCommand(command);
            if(!result) {
                continue;
            }
            changePlayer();
            printer.print(board);

        }
    }

    private Player other() {
        return current == player1 ? player2 : player1;
    }

    private void changePlayer() {
        current = other();
    }


    private boolean executeCommand(String command) {
        String[] array = command.replace(" ", "").split("-");
        if(array.length != 2) {
            printer.println("Некорректная команда");
            return false;
        }
        String from = array[0];
        String to = array[1];
        Figure figure = board.get(from);
        if(figure.isNull()) {
            printer.printf("Ход невозможен: на клетке %s нет фигуры %n", from);
            return false;
        }

        if(figure.getColor() != current.getColor()) {
            printer.println("Ход невозможен: фигура другого игрока");
            return false;
        }

        figure = board.remove(from);
        board.insert(figure, to);
        return true;
    }

}
