package com.company.model.player;

import com.company.model.artificial_intelligence.BasicAi;
import com.company.model.artificial_intelligence.IAi;
import com.company.model.board.Board;

import com.company.model.board.Way;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;


public class Bot extends Player {

    public Bot(String name, FigureColor color) {
        super(name, color);
    }

    public Bot(FigureColor color) {
        this("Bot", color);
    }

    public String getStringCommand(Board board, DangerMatrix dangerMatrix) {
        IAi ai = new BasicAi(board);
        Way way = ai.getBestMove(dangerMatrix, color);
        String from = Board.toPosition(way.from);
        String to = Board.toPosition(way.to);
        return String.format("%s-%s", from, to);
    }
}
