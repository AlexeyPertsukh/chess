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

        board.insert(new FigureWithStatistic(Figure.PAWN, color), "a" + secondLine);
        board.insert(new FigureWithStatistic(Figure.PAWN, color), "b" + secondLine);
        board.insert(new FigureWithStatistic(Figure.PAWN, color), "c" + secondLine);
        board.insert(new FigureWithStatistic(Figure.PAWN, color), "d" + secondLine);
        board.insert(new FigureWithStatistic(Figure.PAWN, color), "e" + secondLine);
        board.insert(new FigureWithStatistic(Figure.PAWN, color), "f" + secondLine);
        board.insert(new FigureWithStatistic(Figure.PAWN, color), "g" + secondLine);
        board.insert(new FigureWithStatistic(Figure.PAWN, color), "h" + secondLine);

        board.insert(new FigureWithStatistic(Figure.ROCK, color), "a" + firstLine);
        board.insert(new FigureWithStatistic(Figure.KNIGHT, color), "b" + firstLine);
        board.insert(new FigureWithStatistic(Figure.BISHOP, color), "c" + firstLine);
        board.insert(new FigureWithStatistic(Figure.QUEEN, color), "d" + firstLine);
        board.insert(new FigureWithStatistic(Figure.KING, color), "e" + firstLine);
        board.insert(new FigureWithStatistic(Figure.BISHOP, color), "f" + firstLine);
        board.insert(new FigureWithStatistic(Figure.KNIGHT, color), "g" + firstLine);
        }
        return board;
    }
}
