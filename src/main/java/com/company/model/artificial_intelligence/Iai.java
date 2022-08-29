package com.company.model.artificial_intelligence;

import com.company.model.board.Way;
import com.company.model.danger.Danger;
import com.company.model.piece.figure.Team;

public interface Iai {
    Way getBestMove(Danger danger, Team myColor);
}
