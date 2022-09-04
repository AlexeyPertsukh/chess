package com.company.model.piece.figure;

import com.company.model.piece.figure.direction.Direction;

public enum Figure {
    PAWN_WHITE('♟',
            Rank.PAWN,
            Team.WHITE,
            Direction.PAWN_WHITE
    ),
    ROCK_WHITE('♜',
            Rank.ROOK,
            Team.WHITE,
            Direction.ROOK
    ),
    KNIGHT_WHITE('♞',
            Rank.KNIGHT,
            Team.WHITE,
            Direction.KNIGHT
    ),
    BISHOP_WHITE('♝',
            Rank.BISHOP,
            Team.WHITE,
            Direction.BISHOP
    ),
    QUEEN_WHITE('♛',
            Rank.QUEEN,
            Team.WHITE,
            Direction.QUEEN
    ),
    KING_WHITE('♚',
            Rank.KING,
            Team.WHITE,
            Direction.KING
    ),

    PAWN_BLACK('♙',
            Rank.PAWN,
            Team.BLACK,
            Direction.PAWN_BLACK
    ),
    ROCK_BLACK('♖',
            Rank.ROOK,
            Team.BLACK,
            Direction.ROOK
    ),
    KNIGHT_BLACK('♘',
            Rank.KNIGHT,
            Team.BLACK,
            Direction.KNIGHT
    ),
    BISHOP_BLACK('♗',
            Rank.BISHOP,
            Team.BLACK,
            Direction.BISHOP
    ),
    QUEEN_BLACK('♕',
            Rank.QUEEN,
            Team.BLACK,
            Direction.QUEEN
    ),
    KING_BLACK('♔',
            Rank.KING,
            Team.BLACK,
            Direction.KING
    ),
;

    private final char icon;
    private final Rank rank;
    private final Team team;

    private final Direction direction;

    Figure(char icon, Rank rank, Team team, Direction direction) {
        this.icon = icon;
        this.direction = direction;
        this.rank = rank;
        this.team = team;
    }

    public char getIcon() {
        return icon;
    }

    public char getLetter() {
        return rank.getLetter();
    }

    public Rank getRank() {
        return rank;
    }

    public Team getTeam() {
        return team;
    }

    public Direction getDirection() {
        return direction;
    }

}
