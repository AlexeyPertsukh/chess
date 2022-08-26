package com.company.model.help;

import com.company.model.command.Command;
import com.company.model.command.CommandEnum;

import java.util.ArrayList;
import java.util.List;

public class Help {
    private Help() {
    }

    public static List<String> info() {
        CommandEnum[] values = CommandEnum.values();
        List<String> list = new ArrayList<>();

        for (CommandEnum en : values) {
            list.add(String.format("%-7s %s", en.getKey(), en.getDescription()));
        }

        list.add(String.format("%-7s сделать ход", "e2-e4"));

        return list;
    }
}
