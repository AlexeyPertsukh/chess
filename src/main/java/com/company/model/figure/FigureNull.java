package com.company.model.figure;

public class FigureNull extends FigureWithStatistic{

    private static final FigureNull instance = new FigureNull();

    private FigureNull() {
        super(null, null);
    }

    public static FigureNull getInstance() {
        return instance;
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
