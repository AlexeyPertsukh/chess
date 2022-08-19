package com.company.figure;

public enum Figure {

    NULL(' ', null, null, null, null),

    PAWN_WHITE('♟', FigureColor.WHITE,  pawnSteps(1), pawnAttacks(1), Limit.ONE),
    KNIGHT_WHITE('♞', FigureColor.WHITE, knightSteps(), knightSteps(), Limit.ONE),
    BISHOP_WHITE('♝', FigureColor.WHITE, bishopSteps(), bishopSteps(), Limit.UNLIM),
    ROCK_WHITE('♜', FigureColor.WHITE, rockSteps(), rockSteps(), Limit.UNLIM),
    QUEEN_WHITE('♛', FigureColor.WHITE, superSteps(), superSteps(), Limit.UNLIM),
    KING_WHITE('♚', FigureColor.WHITE, superSteps(), superSteps(),Limit.ONE),

    PAWN_BLACK('♙', FigureColor.BLACK, pawnSteps(-1), pawnAttacks(-1), Limit.ONE),
    KNIGHT_BLACK('♘', FigureColor.BLACK, knightSteps(), knightSteps(), Limit.ONE),
    BISHOP_BLACK('♗', FigureColor.BLACK, bishopSteps(), bishopSteps(), Limit.UNLIM),
    ROCK_BLACK('♖', FigureColor.BLACK, rockSteps(), rockSteps(), Limit.UNLIM),
    QUEEN_BLACK('♕', FigureColor.BLACK, superSteps(), superSteps(), Limit.UNLIM),
    KING_BLACK('♔', FigureColor.BLACK, superSteps(), superSteps(), Limit.ONE),
    ;


    private final char coat;
    private final Step[] steps;
    private final Step[] attacks;
    private final Limit limit;
    private final FigureColor color;


    Figure(char coat, FigureColor color, Step[] steps, Step[] attacks, Limit limit) {
        this.coat = coat;
        this.steps = steps;
        this.attacks = attacks;
        this.color = color;
        this.limit = limit;

    }

    public char getCoat() {
        return coat;
    }

    public Step[] getSteps() {
        return steps;
    }

    public Step[] getAttacks() {
        return attacks;
    }

    public boolean isNull() {
        return this == NULL;
    }

    public FigureColor getColor() {
        return color;
    }

    public Limit getLimit() {
        return limit;
    }

    private static Step[] pawnSteps(int direction) {
        return new Step[]{Step.of(0, direction)};
    }

    private static Step[] pawnAttacks(int direction) {
        return new Step[]{Step.of(1, direction), Step.of(-1, direction)};
    }

    private static Step[] knightSteps() {
        return new Step[]{
                Step.of(1, 2),
                Step.of(-1, 2),
                Step.of(-2, 1),
                Step.of(2, 1),
                Step.of(-2, -1),
                Step.of(-1, -2),
                Step.of(1, -2),
                Step.of(2, -1)
        };
    }

    private static Step[] bishopSteps() {
        return new Step[]{
                Step.of(1, 1),
                Step.of(1, -1),
                Step.of(-1, 1),
                Step.of(-1, -1)
        };
    }

    private static Step[] rockSteps() {
        return new Step[]{
                Step.of(1, 0),
                Step.of(0, -1),
                Step.of(-1, 0),
                Step.of(0, 1)
        };
    }

    private static Step[] superSteps() {
        Step[] a = bishopSteps();
        Step[] b = rockSteps();

        Step[] steps = new Step[a.length + b.length];
        System.arraycopy(a, 0, steps, 0, a.length);
        System.arraycopy(b, 0, steps, a.length, b.length);
        return steps;
    }
}
