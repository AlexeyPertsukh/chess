package com.company.controller;

import com.company.model.chess_exception.ChessException;
import com.company.model.command.CommandEnum;
import com.company.model.danger.Danger;
import com.company.model.help.Help;
import com.company.model.board.Board;
import com.company.model.command.Command;
import com.company.model.piece.figure.Team;
import com.company.model.player.Bot;
import com.company.model.player.Player;
import com.company.service.move.Castling;
import com.company.service.move.Move;
import com.company.service.move.Step;
import com.company.view.ConsolePrinter;
import com.company.view.ConsoleReader;
import com.company.view.Printer;
import com.company.view.Reader;

import java.util.List;

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
    }

    public void go() {
        go(Team.WHITE);
    }

    public void go(Team currentTeam) {
        current = getPLayerByTeam(currentTeam);
        printHelp();
        printBoard();

        while (true) {

            Danger danger = new Danger(board, current.getTeam());

            if (isCheck(danger)) {
                printCheck();
            }

            printer.printf("%s, your move: ", current.getName());
            String string = getStringCommand();
            Command command = getCommand(string);

            if(command.isSurrender()) {
                printSurrender();
                break;
            }

            boolean needCheckPlayer = executeCommand(command, danger);
            if (!needCheckPlayer) {
                continue;
            }
            changePlayer();
            printBoard();

        }
    }

    private Player other() {
        return current == player1 ? player2 : player1;
    }

    private void changePlayer() {
        current = other();
    }

    private Player getPLayerByTeam(Team team) {
        if(team == player1.getTeam()) {
            return player1;
        }
        if(team == player2.getTeam()) {
            return player2;
        }
        String message = String.format("unknown team %s", team.name().toLowerCase());
        throw new ChessException(message);
    }

//    private boolean isMate() {
//        Loose loose = new Loose(board);
//        try {
//            Danger danger = new Danger(board, other().getColor());
//            return loose.isCheckmate(danger, current.getColor());
//
//        } catch (ChessException e) {
//            printer.println(e.getMessage());
//            return false;
//        }
//    }

    private boolean isCheck(Danger danger) {
        try {
            return danger.isCheck();
        } catch (ChessException e) {
            printer.println(e.getMessage());
            return false;
        }
    }

    private boolean executeCommand(Command command, Danger danger) {
        try {
            return executeCommandOrException(command, danger);
        } catch (ChessException e) {
            printer.println(e.getMessage());
            return false;
        }
    }

    private boolean executeCommandOrException(Command command, Danger danger) {

        if (command.isHelp()) {
            printHelp();
            return false;
        }

        if (command.isStep()) {
            step(command, danger);
            return true;
        }

        if (command.isCastling()) {
            castling(command, danger);
            return true;
        }

        throw new ChessException("unknown command");
    }

    private void step(Command command, Danger danger) {
        Move move = new Step(board);
        move.execute(command, current, danger);
    }

    private void castling(Command command, Danger danger) {
        Move move = new Castling(board);
        move.execute(command, current, danger);
    }


    private static Command getCommand(String string) {
        return new Command(string);
    }

    private String getStringCommand() {
        if(current instanceof Bot) {
            Danger danger = new Danger(board, other().getTeam());

            String string = ((Bot) current).getStringCommand(board, danger);
            printer.println(string);
            return string;
        } else {
            return reader.next();
        }
    }

    private void printMate(Player looser) {
        String message = String.format("Checkmate, %s lost", looser.getName());
        printer.println(message);
    }

    private void printCheck() {
        String message = "You check, save the king!";
        printer.println(message);
    }

    private void printSurrender() {
        String message = String.format("%s surrendered.", current.getName());
        printer.println(message);
    }

    private void printHelp() {
        printer.println("\n" + GAME_NAME);
        printer.println("----");
        List<String> list = Help.info();
        for (String s : list) {
            printer.println(s);
        }
        printer.println("----");
    }

    private void printBoard() {
        printer.println();
        printer.print(board);
        printer.println("...");
        printer.printf("%s - %s %n", CommandEnum.HELP.getKey(), CommandEnum.HELP.getDescription());
        printer.println();
    }

}
