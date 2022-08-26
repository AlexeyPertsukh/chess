package com.company.controller;

import com.company.model.danger.Danger;
import com.company.model.danger.DangerMatrix;
import com.company.model.help.Help;
import com.company.model.loose.Loose;
import com.company.model.board.Board;
import com.company.model.command.Command;
import com.company.model.player.Player;
import com.company.service.move.Castling;
import com.company.service.move.Move;
import com.company.service.move.Step;
import com.company.view.ConsolePrinter;
import com.company.view.ConsoleReader;
import com.company.view.Printer;
import com.company.view.Reader;

public class Game {
    private static final String GAME_NAME = "JAVA CONSOLE CHESS";

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
        printHelp();
        printer.print(board);
        while (true) {

            if (isMate()) {
                printOnMate(current);
                break;
            }

            if (isShah()) {
                printOnShah();
            }

            printer.printf("%s, ваш ход: ", current.getName());
            String string = reader.next();
            Command command = getCommand(string);
            boolean needCheckPlayer = executeCommand(command);
            if (!needCheckPlayer) {
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

    private boolean isMate() {
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
            return executeCommandOrException(command, dangerMatrix);
        } catch (IllegalArgumentException e) {
            printer.println(e.getMessage());
            return false;
        }
    }

    private boolean executeCommandOrException(Command command, DangerMatrix dangerMatrix) {

        if (command.isHelp()) {
            printHelp();
            return false;
        }

        if (command.isStep()) {
            step(command, dangerMatrix);
            return true;
        }

        if (command.isCastling()) {
            castling(command, dangerMatrix);
            return true;
        }

        throw new IllegalArgumentException("неизвестная команда");
    }

    private void step(Command command, DangerMatrix dangerMatrix) {
        Move move = new Step(board);
        move.execute(command, current, dangerMatrix);
    }

    private void castling(Command command, DangerMatrix dangerMatrix) {
        Move move = new Castling(board);
        move.execute(command, current, dangerMatrix);
    }


    private static Command getCommand(String string) {
        return new Command(string);
    }

    private void printOnMate(Player looser) {
        String message = String.format("Мат королю, %s проиграл", looser.getName());
        printer.println(message);
    }

    private void printOnShah() {
        String message = "Вам шах, спасайте короля!";
        printer.println(message);
    }

    private void printHelp() {
        printer.println("\n" + GAME_NAME);
        printer.println("----");
        String[] strings = Help.info();
        for (String s : strings) {
            printer.println(s);
        }
        printer.println("----");
    }

}
