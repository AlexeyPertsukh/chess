package com.company.model.figure.direction;

public enum Direction {
    PAWN_WHITE(
            DirectionHelper.whitePawnMove(),
            DirectionHelper.whitePawnAttack(),
            Distance.ONE
            ),
    PAWN_BLACK(
            DirectionHelper.blackPawnMove(),
            DirectionHelper.blackPawnAttack(),
            Distance.ONE
            ),
    ROCK(
            DirectionHelper.rockMove(),
            DirectionHelper.rockAttack(),
            Distance.UNLIM
            ),
    KNIGHT(
            DirectionHelper.knightMove(),
            DirectionHelper.knightAttack(),
            Distance.ONE
            ),
    BISHOP(
            DirectionHelper.bishopMove(),
            DirectionHelper.bishopAttack(),
            Distance.UNLIM
            ),
    QUEEN(
            DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Distance.UNLIM
            ),
    KING(
            DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Distance.ONE
            ),
    ;

    private final Offset[] offsetsMove;
    private final Offset[] offsetsAttack;
    private final Distance distance;

    Direction(Offset[] offsetsMove, Offset[] offsetsAttack, Distance distance) {
        this.offsetsMove = offsetsMove;
        this.offsetsAttack = offsetsAttack;
        this.distance = distance;
    }

    public Offset[] getOffsetsMove() {
        return offsetsMove;
    }

    public Offset[] getOffsetsAttack() {
        return offsetsAttack;
    }

    public Distance getDistance() {
        return distance;
    }

}
