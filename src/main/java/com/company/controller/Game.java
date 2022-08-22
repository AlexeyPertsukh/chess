package com.company.controller;

import com.company.service.danger.Danger;
import com.company.service.move.Move;
import com.company.model.board.Board;
import com.company.model.command.Command;
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
            String string = reader.next();
            Command command = getCommand(string);
            boolean result = executeCommand(command);
            if (!result) {
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

    private boolean executeCommand(Command command) {
        Danger danger = new Danger(board);
        boolean[][] dangerArray = danger.toArray(current.getColor());
        try {
            executeCommandOrException(command, dangerArray);
            return true;
        } catch (IllegalArgumentException e) {
            printer.println(e.getMessage());
            return false;
        }
    }

    private void executeCommandOrException(Command command, boolean[][] dangerArray) {
        if(command.isMove()) {
            move(command, dangerArray);
            return;
        }

        throw new IllegalArgumentException("неизвестная команда");
    }

    private void move(Command command, boolean[][] dangerArray) {
        Move move = new Move(board);
        move.move(command, current, dangerArray);
    }


    private static Command getCommand(String string) {
        return new Command(string);
    }

}
