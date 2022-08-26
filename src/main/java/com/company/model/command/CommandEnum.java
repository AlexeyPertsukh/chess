package com.company.model.command;

public enum CommandEnum {
    HELP("?", "помощь"),
    END("end", "выход"),
    SURRENDER("sur", "сдаться"),
    LEFT_CASTLING("lcast", "рокировка E-A"),
    RIGHT_CASTLING("rcast", "рокировка E-A"),
    ;

    private final String key;
    private final String description;

    CommandEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
