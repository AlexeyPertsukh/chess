package com.company.model.artificial_intelligence;

import com.company.model.danger.DangerMatrix;
import com.company.model.figure.FigureColor;

public interface IAi {
    PossibleMove getBestMove(FigureColor myColor, DangerMatrix dangerMatrix);
}
