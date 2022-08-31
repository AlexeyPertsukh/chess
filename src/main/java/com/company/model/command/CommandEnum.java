package com.company.model.command;

public enum CommandEnum {
    HELP("?", "help"),
    END("end", "exit"),
    SURRENDER("sur", "surrender"),
    LEFT_CASTLING("lcast", "castling E-A"),
    RIGHT_CASTLING("rcast", "castling E-H"),
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
