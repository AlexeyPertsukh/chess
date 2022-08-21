package com.company.controller;

import com.company.model.board.Board;
import com.company.model.figure.Figure;
import com.company.model.figure.FigureColor;
import com.company.model.figure.FigureWithStatistic;
import com.company.model.player.Player;

public class Main {

    public static void main(String[] args) {

        Board board = createBoard();
        Player player1 = new Player("Белые", FigureColor.WHITE);
        Player player2 = new Player("Черные", FigureColor.BLACK);

        Game game = new Game(board, player1, player2);
        game.go();

    }

    private static Board createBoard() {
        Board board = new Board();

        for (int i = 0; i < 2; i++) {
            FigureColor color = (i == 0) ? FigureColor.WHITE : FigureColor.BLACK;
            int firstLine = (i == 0) ? 1 : 8;
            int secondLine = (i == 0) ? 2 : 7;



        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "a" + secondLine);
        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "b" + secondLine);
        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "c" + secondLine);
        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "d" + secondLine);
        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "e" + secondLine);
        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "f" + secondLine);
        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "g" + secondLine);
        board.insert(FigureWithStatistic.of(Figure.PAWN, color), "h" + secondLine);

        board.insert(FigureWithStatistic.of(Figure.ROCK, color), "a" + firstLine);
        board.insert(FigureWithStatistic.of(Figure.KNIGHT, color), "b" + firstLine);
        board.insert(FigureWithStatistic.of(Figure.BISHOP, color), "c" + firstLine);
        board.insert(FigureWithStatistic.of(Figure.QUEEN, color), "d" + firstLine);
        board.insert(FigureWithStatistic.of(Figure.KING, color), "e" + firstLine);
        board.insert(FigureWithStatistic.of(Figure.BISHOP, color), "f" + firstLine);
        board.insert(FigureWithStatistic.of(Figure.KNIGHT, color), "g" + firstLine);
        }
        return board;
    }
}
