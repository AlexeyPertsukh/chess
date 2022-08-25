package com.company.model.help;

import com.company.model.command.Command;

public class Help {
    private Help() {
    }

    public static String[] info() {
        return new String[] {
                String.format("%-7s помощь", Command.HELP),
                String.format("%-7s выйти из игры", Command.END),
                String.format("%-7s сдаться", Command.DRAW),
                String.format("%-7s сделать ход", "e2-e4"),
                String.format("%-7s рокировка", "e1-h1"),
        };
    }
}
