package com.company.model.player;

import com.company.model.artificial_intelligence.Ai;
import com.company.model.artificial_intelligence.PossibleMove;
import com.company.model.board.Board;

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
        Ai ai = new Ai(board);
        PossibleMove possibleMove = ai.getBestMove(color, dangerMatrix);
        String from = Board.toPosition(possibleMove.from);
        String to = Board.toPosition(possibleMove.to);
        return String.format("%s-%s", from, to);
    }
}
