package com.company.model.artificial_intelligence;

import com.company.model.board.Way;
import com.company.model.danger.Danger;
import com.company.model.figure.FigureColor;

public interface IAi {
    Way getBestMove(Danger danger, FigureColor myColor);
}
