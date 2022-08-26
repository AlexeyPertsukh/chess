package com.company.model.figure;

import com.company.model.figure.direction.Direction;

public enum Figure {
    PAWN_WHITE('♟',
            FigureRank.PAWN,
            FigureColor.WHITE,
            Direction.PAWN_WHITE
    ),
    ROCK_WHITE('♜',
            FigureRank.ROCK,
            FigureColor.WHITE,
            Direction.ROCK
    ),
    KNIGHT_WHITE('♞',
            FigureRank.KNIGHT,
            FigureColor.WHITE,
            Direction.KNIGHT
    ),
    BISHOP_WHITE('♝',
            FigureRank.BISHOP,
            FigureColor.WHITE,
            Direction.BISHOP
    ),
    QUEEN_WHITE('♛',
            FigureRank.QUEEN,
            FigureColor.WHITE,
            Direction.QUEEN
    ),
    KING_WHITE('♚',
            FigureRank.KING,
            FigureColor.WHITE,
            Direction.KING
    ),

    PAWN_BLACK('♙',
            FigureRank.PAWN,
            FigureColor.BLACK,
            Direction.PAWN_BLACK
    ),
    ROCK_BLACK('♖',
            FigureRank.ROCK,
            FigureColor.BLACK,
            Direction.ROCK
    ),
    KNIGHT_BLACK('♘',
            FigureRank.KNIGHT,
            FigureColor.BLACK,
            Direction.KNIGHT
    ),
    BISHOP_BLACK('♗',
            FigureRank.BISHOP,
            FigureColor.BLACK,
            Direction.BISHOP
    ),
    QUEEN_BLACK('♕',
            FigureRank.QUEEN,
            FigureColor.BLACK,
            Direction.QUEEN
    ),
    KING_BLACK('♔',
            FigureRank.KING,
            FigureColor.BLACK,
            Direction.KING
    ),
;

    private final char icon;
    private final FigureRank rank;
    private final FigureColor color;

    private final Direction direction;

    Figure(char icon, FigureRank rank, FigureColor color, Direction direction) {
        this.icon = icon;
        this.direction = direction;
        this.rank = rank;
        this.color = color;
    }

    public char getIcon() {
        return icon;
    }

    public char getLetter() {
        return rank.getLetter();
    }

    public FigureRank getRank() {
        return rank;
    }

    public FigureColor getColor() {
        return color;
    }

    public Direction getDirection() {
        return direction;
    }

}
