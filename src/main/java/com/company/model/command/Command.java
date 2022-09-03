package com.company.model.command;

public class Command {

    public static final String MOVE_SEPARATOR = "-";

    private final String string;

    public Command(String string) {
        this.string = string.replace(" ", "");
        System.out.println(this.string);
    }

    public String getString() {
        return string.toLowerCase();
    }

    public boolean isEnd() {
        return equals(string, CommandEnum.END.getKey());
    }

    public boolean isSurrender() {
        return equals(string, CommandEnum.SURRENDER.getKey());
    }

    public boolean isHelp() {
        return equals(string, CommandEnum.HELP.getKey());
    }

    public boolean isCastling() {
        return isRightCastling() || isLeftCastling();
    }

    public boolean isRightCastling() {
        return equals(string, CommandEnum.RIGHT_CASTLING.getKey());
    }

    public boolean isLeftCastling() {
        return equals(string, CommandEnum.LEFT_CASTLING.getKey());
    }


    private static boolean equals(String s1, String s2) {
        return s1.equalsIgnoreCase(s2);
    }

    public boolean isStep() {
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
