package com.company.model.player;

import com.company.model.figure.FigureColor;

public class Player {
    private final String name;
    protected final FigureColor color;

    public Player(String name, FigureColor color) {
        this.name = name;
        this.color = color;
    }

    public Player(FigureColor color) {
        this(null, color);
    }

    public String getName() {
        if(name == null) {
            return color.name();
        }
        return String.format("%s [%s]", name, color.name());
    }

    public FigureColor getColor() {
        return color;
    }

}
