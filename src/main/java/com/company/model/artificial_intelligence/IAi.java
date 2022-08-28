package com.company.model.artificial_intelligence;

import com.company.model.board.Way;
import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;

public interface IAi {
    Way getBestMove(DangerMatrix dangerMatrix, FigureColor myColor);
}
