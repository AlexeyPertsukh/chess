package com.company.model.player;

import com.company.model.artificial_intelligence.BasicAi;
import com.company.model.artificial_intelligence.Iai;
import com.company.model.board.Board;

import com.company.model.board.Way;
import com.company.model.danger.Danger;
import com.company.model.figure.FigureColor;


public class Bot extends Player {

    public Bot(String name, FigureColor color) {
        super(name, color);
    }

    public Bot(FigureColor color) {
        this("Bot", color);
    }

    public String getStringCommand(Board board, Danger danger) {
        Iai ai = new BasicAi(board);
        Way way = ai.getBestMove(danger, color);
        String from = Board.toPosition(way.from);
        String to = Board.toPosition(way.to);
        return String.format("%s-%s", from, to);
    }
}
