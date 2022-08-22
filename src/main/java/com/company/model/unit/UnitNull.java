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
}
