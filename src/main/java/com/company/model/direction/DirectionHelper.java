package com.company.model.direction;

public class DirectionHelper {
    private DirectionHelper() {
    }

    public static Offset[] whitePawnMove() {
        return new Offset[]{
                new Offset(0, -1),
        };
    }

    public static Offset[] blackPawnMove() {
        return new Offset[]{
                new Offset(0, 1),
        };
    }

    public static Offset[] rockMove() {
        return new Offset[]{
                new Offset(0, 1),
                new Offset(0, -1),
                new Offset(1, 0),
                new Offset(-1, 0),
        };
    }

    public static Offset[] knightMove() {
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

    public static Offset[] bishopMove() {
        return new Offset[]{
                new Offset(-1, -1),
                new Offset(1, -1),
                new Offset(1, 1),
                new Offset(-1, 1),
        };
    }

    public static Offset[] superMove() {
        Offset[] a = bishopMove();
        Offset[] b = rockMove();

        Offset[] out = new Offset[a.length + b.length];

        System.arraycopy(a, 0, out, 0, a.length);
        System.arraycopy(b, 0, out, a.length, b.length);

        return out;
    }

    //
    public static Offset[] whitePawnAttack() {
        return new Offset[]{
                new Offset(-1, -1),
                new Offset(1, -1)
        };
    }

    public static Offset[] blackPawnAttack() {
        return new Offset[]{
                new Offset(-1, 1),
                new Offset(1, 1)
        };
    }

    public static Offset[] rockAttack() {
        return rockMove();
    }

    public static Offset[] knightAttack() {
        return knightMove();
    }

    public static Offset[] bishopAttack() {
        return bishopMove();
    }

    public static Offset[] superAttack() {
        return superMove();
    }
}
