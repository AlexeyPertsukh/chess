package com.company.model.player;

import com.company.model.artificial_intelligence.Iai;
import com.company.model.board.Board;

import com.company.model.board.Way;
import com.company.model.command.Command;
import com.company.model.command.CommandEnum;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;


public class Bot extends Player {

    public Bot(String name, Team color) {
        super(name, color);
    }

    public Bot(Team color) {
        this("Bot", color);
    }

    int temp = 0;

    public String getStringCommand(Board board, Danger danger) {
        if (temp == 0) {
            temp++;
            return "ai under progress";

        }
        return CommandEnum.SURRENDER.getKey();
    }
}
