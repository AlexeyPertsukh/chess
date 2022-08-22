package com.company.model.unit;

public class UnitNull extends Unit {

    private static final UnitNull instance = new UnitNull();

    private UnitNull() {
        super(null);
    }

    public static UnitNull getInstance() {
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
    public boolean isRock() {
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
}
