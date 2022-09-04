package com.company.model.piece;

public class PieceNull extends Piece {

    private static final PieceNull instance = new PieceNull();

    private PieceNull() {
        super(null);
    }

    public static PieceNull getInstance() {
        return instance;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isRook() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isBishop() {
        return super.isBishop();
    }

    @Override
    public boolean isQueen() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public PieceNull clone() throws CloneNotSupportedException {
        return instance;
    }


}
