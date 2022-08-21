package com.company.model.figure;

public class FigureNull extends FigureWithStatistic{
    public FigureNull() {
        super(null, null);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
