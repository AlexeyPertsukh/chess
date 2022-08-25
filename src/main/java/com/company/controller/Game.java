package com.company.controller;

import com.company.model.danger.Danger;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;
import com.company.model.loose.Loose;
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

            if(isCheckmate()) {
                String message = String.format("Мат королю, %s проиграл", current.getName());
                printer.println(message);
                break;
            }

            if(isShah()) {
                String message = "Вам шах, спасайте короля!";
                printer.println(message);
            }

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

    private boolean isCheckmate() {
        Loose loose = new Loose(board);
        try {
            DangerMatrix dangerMatrix = (new Danger(board)).toMatrix(other().getColor());
            return loose.isCheckmate(dangerMatrix, current.getColor());

        } catch (IllegalArgumentException e) {
            printer.println(e.getMessage());
            return false;
        }
    }

    private boolean isShah() {
        Loose loose = new Loose(board);
        try {
            DangerMatrix dangerMatrix = (new Danger(board)).toMatrix(other().getColor());
            return loose.isShah(dangerMatrix, current.getColor());

        } catch (IllegalArgumentException e) {
            printer.println(e.getMessage());
            return false;
        }
    }

    private boolean executeCommand(Command command) {
        Danger danger = new Danger(board);
        DangerMatrix dangerMatrix = danger.toMatrix(other().getColor());
        try {
            executeCommandOrException(command, dangerMatrix);
            return true;
        } catch (IllegalArgumentException e) {
            printer.println(e.getMessage());
            return false;
        }
    }

    private void executeCommandOrException(Command command, DangerMatrix dangerMatrix) {
        if (command.isMove()) {
            move(command, dangerMatrix);
            return;
        }

        throw new IllegalArgumentException("неизвестная команда");
    }

    private void move(Command command, DangerMatrix dangerMatrix) {
        Move action = new Move(board);
        action.execute(command, current, dangerMatrix);
    }


    private static Command getCommand(String string) {
        return new Command(string);
    }

}
