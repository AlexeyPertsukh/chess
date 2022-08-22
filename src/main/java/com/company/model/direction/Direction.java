package com.company.model.direction;

public enum Direction {
    PAWN_WHITE(DirectionHelper.whitePawnMove(),
            DirectionHelper.whitePawnAttack(),
            Limit.ONE,
            Obstruction.STOP),
    PAWN_BLACK(DirectionHelper.blackPawnMove(),
            DirectionHelper.blackPawnAttack(),
            Limit.ONE,
            Obstruction.STOP),
    ROCK(DirectionHelper.rockMove(),
            DirectionHelper.rockAttack(),
            Limit.UNLIM,
            Obstruction.STOP),
    KNIGHT(DirectionHelper.knightMove(),
            DirectionHelper.knightAttack(),
            Limit.ONE,
            Obstruction.IGNORE),
    BISHOP(DirectionHelper.knightMove(),
            DirectionHelper.knightAttack(),
            Limit.UNLIM,
            Obstruction.STOP),
    QUEEN(DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Limit.UNLIM,
            Obstruction.STOP),
    KING(DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Limit.ONE,
            Obstruction.STOP),
    ;

    private final Offset[] offsetsMove;
    private final Offset[] offsetsAttack;
    private final Limit limit;
    private final Obstruction obstruction;

    Direction(Offset[] offsetsMove, Offset[] offsetsAttack, Limit limit, Obstruction obstruction) {
        this.offsetsMove = offsetsMove;
        this.offsetsAttack = offsetsAttack;
        this.limit = limit;
        this.obstruction = obstruction;
    }

    public Offset[] getOffsetsMove() {
        return offsetsMove;
    }

    public Offset[] getOffsetsAttack() {
        return offsetsAttack;
    }

    public Limit getLimit() {
        return limit;
    }

    public Obstruction getObstruction() {
        return obstruction;
    }
}
