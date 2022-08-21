package com.company.model.attack_direction;

public enum AttackDirection {
    PAWN_WHITE(whitePawnAttack(), Limit.ONE, Obstruction.ACCEPT),
    PAWN_BLACK(blackPawnAttack(), Limit.ONE, Obstruction.ACCEPT),
    ROCK(rockAttack(), Limit.UNLIM, Obstruction.ACCEPT),
    KNIGHT(knightAttack(), Limit.ONE, Obstruction.IGNORE),
    BISHOP(knightAttack(), Limit.UNLIM, Obstruction.ACCEPT),
    QUEEN(superAttack(), Limit.UNLIM, Obstruction.ACCEPT),
    KING(superAttack(), Limit.ONE, Obstruction.ACCEPT),
    ;

    private final Offset[] offsets;
    private final Limit limit;
    private final Obstruction obstruction;

    AttackDirection(Offset[] offsets, Limit limit, Obstruction obstruction) {
        this.offsets = offsets;
        this.limit = limit;
        this.obstruction = obstruction;
    }

    public Offset[] getOffsets() {
        return offsets;
    }

    public Limit getLimit() {
        return limit;
    }

    public Obstruction getObstruction() {
        return obstruction;
    }

    private static Offset[] whitePawnAttack() {
        return new Offset[]{
                new Offset(-1, -1),
                new Offset(1, -1)
        };
    }

    private static Offset[] blackPawnAttack() {
        return new Offset[]{
                new Offset(-1, 1),
                new Offset(1, 1)
        };
    }

    private static Offset[] rockAttack() {
        return new Offset[]{
                new Offset(0, 1),
                new Offset(0, -1),
                new Offset(1, 0),
                new Offset(-1, 0),
        };
    }

    private static Offset[] knightAttack() {
        return new Offset[]{
                new Offset(-2, -1),
                new Offset(-1, -2),
                new Offset(1, -2),
                new Offset(2, -1),
                new Offset(2, 1),
                new Offset(1, 2),
                new Offset(-1, 2),
                new Offset(-2, 1),
        };
    }

    private static Offset[] bishopAttack() {
        return new Offset[]{
                new Offset(-1, -1),
                new Offset(1, -1),
                new Offset(1, 1),
                new Offset(-1, 1),
        };
    }

    private static Offset[] superAttack() {
        Offset[] a = bishopAttack();
        Offset[] b = rockAttack();

        Offset[] out = new Offset[a.length + b.length];

        System.arraycopy(a, 0, out, 0, a.length);
        System.arraycopy(b, 0, out, a.length, b.length);

        return out;
    }

}
