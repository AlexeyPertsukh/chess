package com.company.model.piece.figure.direction;

public enum Direction {
    PAWN_WHITE(
            DirectionHelper.whitePawnMove(),
            DirectionHelper.whitePawnAttack(),
            Distance.SHORT
    ),
    PAWN_BLACK(
            DirectionHelper.blackPawnMove(),
            DirectionHelper.blackPawnAttack(),
            Distance.SHORT
    ),
    ROOK(
            DirectionHelper.rockMove(),
            DirectionHelper.rockAttack(),
            Distance.LONG
    ),
    KNIGHT(
            DirectionHelper.knightMove(),
            DirectionHelper.knightAttack(),
            Distance.SHORT
    ),
    BISHOP(
            DirectionHelper.bishopMove(),
            DirectionHelper.bishopAttack(),
            Distance.LONG
    ),
    QUEEN(
            DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Distance.LONG
    ),
    KING(
            DirectionHelper.superMove(),
            DirectionHelper.superAttack(),
            Distance.SHORT
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
