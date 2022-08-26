package com.company.model.command;

public class Command {
    public static final String END = "end";
    public static final String HELP = "?";
    public static final String DRAW = "draw";
    public static final String L_CASTLING = "lcast";
    public static final String R_CASTLING = "rcast";

    public static final String MOVE_SEPARATOR = "-";

    private final String string;

    public Command(String string) {
        this.string = string.replaceAll(" ", "");
    }

    public String getString() {
        return string.toLowerCase();
    }

    public boolean isEnd() {
        return equals(string, END);
    }


    public boolean isHelp() {
        return equals(string, HELP);
    }

    public boolean isCastling() {
        return equals(string, L_CASTLING) || equals(string, R_CASTLING);
    }

    private static boolean equals(String s1, String s2) {
        return s1.equalsIgnoreCase(s2);
    }

    public boolean isMove() {
        String[] array = string.split(MOVE_SEPARATOR);

        if (array.length != 2) {
            return false;
        }

        for (String s : array) {
            if(s.length() != 2 || !isLetter(s.charAt(0)) || !isInteger(s.charAt(1))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(char c) {
        return isInteger(String.valueOf(c));
    }


}
