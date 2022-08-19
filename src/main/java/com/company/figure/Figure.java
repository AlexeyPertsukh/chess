package com.company.figure;

public enum Figure {

    NULL(' ', null, null, null, false),

    PAWN_WHITE('♙', Color.WHITE,  pawnSteps(1), pawnAttacks(1), limit()),
    KNIGHT_WHITE('♘', Color.WHITE, knightSteps(), knightSteps(), limit()),
    BISHOP_WHITE('♗', Color.WHITE, bishopSteps(), bishopSteps(), unlimit()),
    ROCK_WHITE('♖', Color.WHITE, rockSteps(), rockSteps(), unlimit()),
    QUEEN_WHITE('♕', Color.WHITE, superSteps(), superSteps(), unlimit()),
    KING_WHITE('♔', Color.WHITE, superSteps(), superSteps(), limit()),

    PAWN_BLACK('♟', Color.BLACK, pawnSteps(-1), pawnAttacks(-1), limit()),
    KNIGHT_BLACK('♞', Color.BLACK, knightSteps(), knightSteps(), limit()),
    BISHOP_BLACK('♝', Color.BLACK, bishopSteps(), bishopSteps(), unlimit()),
    ROCK_BLACK('♜', Color.BLACK, rockSteps(), rockSteps(), unlimit()),
    QUEEN_BLACK('♛', Color.BLACK, superSteps(), superSteps(), unlimit()),
    KING_BLACK('♚', Color.BLACK, superSteps(), superSteps(), limit()),
    ;

    private static final boolean  LIMIT = true;
    private static final boolean  UNLIMIT = false;

    private final char coat;
    private final Step[] steps;
    private final Step[] attacks;
    private final boolean limited;
    private final Color color;


    Figure(char coat, Color color, Step[] steps, Step[] attacks, boolean limited) {
        this.coat = coat;
        this.steps = steps;
        this.attacks = attacks;
        this.color = color;
        this.limited = limited;

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

    private static boolean limit() {
        return LIMIT;
    }

    private static boolean unlimit() {
        return UNLIMIT;
    }

    public boolean isLimited() {
        return limited;
    }

    public Color getColor() {
        return color;
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
