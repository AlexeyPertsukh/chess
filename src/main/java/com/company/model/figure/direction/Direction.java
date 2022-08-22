package com.company.model.figure.direction;

public enum Direction {
    PAWN_WHITE(
            DirectionHelper.whitePawnMove(),
            DirectionHelper.whitePawnAttack(),
            Distance.ONE,
            Obstruction.STOP),
    PAWN_BLACK(
            DirectionHelper.blackPawnMove(),
            DirectionHelper.blackPawnAttack(),
            Distance.ONE,
            Obstruction.STOP),
    ROCK(
            DirectionHelper.rockMove(),
            DirectionHelper.rockAttack(),
            Distance.UNLIM,
            Obstruction.STOP),
    KNIGHT(
            DirectionHelper.knightMove(),
            DirectionHelper.knightAttack(),
            Distance.ONE,
            Obstruction.IGNORE),
    BISHOP(
            DirectionHelper.bishopMove(),
            DirectionHelper.bishopAttack(),
            Distance.UNLIM,
            Obstruction.STOP),
    QUEEN(
            DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Distance.UNLIM,
            Obstruction.STOP),
    KING(
            DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Distance.ONE,
            Obstruction.STOP),
    ;

    private final Offset[] offsetsMove;
    private final Offset[] offsetsAttack;
    private final Distance distance;
    private final Obstruction obstruction;

    Direction(Offset[] offsetsMove, Offset[] offsetsAttack, Distance distance, Obstruction obstruction) {
        this.offsetsMove = offsetsMove;
        this.offsetsAttack = offsetsAttack;
        this.distance = distance;
        this.obstruction = obstruction;
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

    public Obstruction getObstruction() {
        return obstruction;
    }
}
